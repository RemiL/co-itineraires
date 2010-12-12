package simulateurdeplacement;

import graphe.Etape;
import graphe.Lieu;

import java.util.ArrayList;
import java.util.HashMap;

import contraintes.Contrainte;
import contraintes.EviterLieu;
import contraintes.EviterMoyenTransport;
import contraintes.MinimiserCoutTrajet;
import contraintes.MinimiserTempsTrajet;

import trajet.Trajet;
import trajet.TrajetException;
import usager.Usager;

import ligne.Ligne;
import moyenstransport.MarcheAPied;
import moyenstransport.PrendreBus;

public class SimulateurDeplacement 
{
	static private HashMap<String, Lieu> lieux = new HashMap<String, Lieu>();
	static private HashMap<String, Ligne> lignesBus = new HashMap<String, Ligne>();
		
	public static void main(String[] args)
	{
		initialiserLieux();
		
		ArrayList<Trajet> trajets = new ArrayList<Trajet>();
		ArrayList<Usager> usagers = new ArrayList<Usager>();
		ArrayList<Contrainte> contraintes = new ArrayList<Contrainte>();
		
		try {
			trajets.add(new Trajet());
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 336"), MarcheAPied.getInstance()));
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Batiment 336"), lieux.get("Batiment 333"), MarcheAPied.getInstance()));
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Batiment 333"), lieux.get("Arr�t Bures Amphi (retour)"), MarcheAPied.getInstance()));
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Arr�t Bures Amphi (retour)"), lieux.get("Arr�t L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e")));
			
			System.out.println(trajets.get(0));
			System.out.println("Co�t : "+trajets.get(0).getCout());
			System.out.println("Dur�e : "+trajets.get(0).getDuree()+"\n");
			
			trajets.add(new Trajet());
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Batiment 407 - Biblioth�que Universitaire"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Batiment 300 - Pr�sidence"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t L'Yvette (retour)"), MarcheAPied.getInstance()));
			
			System.out.println(trajets.get(1));
			System.out.println("Co�t : "+trajets.get(1).getCout());
			System.out.println("Dur�e : "+trajets.get(1).getDuree()+"\n");
			
			contraintes.add(new MinimiserCoutTrajet());
			usagers.add(new Usager("John Doe", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arr�t L'Yvette (retour)"), trajets, contraintes.get(0), 0));
			contraintes.add(new MinimiserTempsTrajet());
			usagers.add(new Usager("Jane Doe", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arr�t L'Yvette (retour)"), trajets, contraintes.get(1), 0));
			usagers.add(new Usager("John Smith", lieux.get("Batiment 336"), lieux.get("Arr�t L'Yvette (retour)"), trajets, contraintes.get(1), 0));
			contraintes.add(new EviterLieu(lieux.get("Batiment 300 - Pr�sidence")));
			usagers.add(new Usager("Jane Smith", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arr�t L'Yvette (retour)"), trajets, contraintes.get(2), 0));
			contraintes.add(new EviterMoyenTransport(PrendreBus.getInstance()));
			usagers.add(new Usager("John Martin", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arr�t L'Yvette (retour)"), trajets, contraintes.get(3), 0));
			
			System.out.println(contraintes.get(0)+" :\n"+usagers.get(0).choisirMeilleurTrajet()+"\n");
			System.out.println(contraintes.get(1)+" :\n"+usagers.get(1).choisirMeilleurTrajet()+"\n");
			System.out.println("Pas de trajet compatible :\n"+usagers.get(2).choisirMeilleurTrajet()+"\n");
			System.out.println(contraintes.get(2)+" :\n"+usagers.get(3).choisirMeilleurTrajet()+"\n");
			System.out.println(contraintes.get(3)+" :\n"+usagers.get(4).choisirMeilleurTrajet()+"\n");
		} catch (TrajetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet d'initialiser les lieux connus par le simulateur.
	 */
	private static void initialiserLieux()
	{
		// B�timents
		lieux.put("Batiment 102 - Institut de physique nucl�aire d'Orsay", new Lieu("Batiment 102 - Institut de physique nucl�aire d'Orsay"));
		lieux.put("Batiment 230 - Restaurant Universitaire de Bures", new Lieu("Batiment 230 - Restaurant Universitaire de Bures"));
		lieux.put("Batiment 300 - Pr�sidence", new Lieu("Batiment 300 - Pr�sidence"));
		lieux.put("Batiment 333", new Lieu("Batiment 333"));
		lieux.put("Batiment 336", new Lieu("Batiment 336"));
		lieux.put("Batiment 337 - Salles d'examen", new Lieu("Batiment 337 - Salles d'examen"));
		lieux.put("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution", new Lieu("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"));
		lieux.put("Batiment 406 - Restaurant Universitaire d'Orsay", new Lieu("Batiment 406 - Restaurant Universitaire d'Orsay"));
		lieux.put("Batiment 407 - Biblioth�que Universitaire", new Lieu("Batiment 407 - Biblioth�que Universitaire"));
		lieux.put("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay", new Lieu("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"));
		lieux.put("Batiment 425 - Laboratoire de Math�matiques d'Orsay", new Lieu("Batiment 425 - Laboratoire de Math�matiques d'Orsay"));
		lieux.put("Batiment 470", new Lieu("Batiment 470"));
		lieux.put("Batiment 490 - LRI", new Lieu("Batiment 490 - LRI"));
		lieux.put("Batiment 505 - Laboratoire Aim� Cotton", new Lieu("Batiment 505 - Laboratoire Aim� Cotton"));
		lieux.put("Batiment 508 - LIMSI", new Lieu("Batiment 508 - LIMSI"));
		lieux.put("Batiment 620 - Maison de l'Ing�nieur", new Lieu("Batiment 620 - Maison de l'Ing�nieur"));
		lieux.put("Batiment 640 - PUIO", new Lieu("Batiment 640 - PUIO"));
		lieux.put("IUT d'Orsay", new Lieu("IUT d'Orsay"));
		lieux.put("SUPELEC", new Lieu("SUPELEC"));
		
		// Arr�ts de bus
		lieux.put("Arr�t L'Yvette (aller)", new Lieu("Arr�t L'Yvette (aller)"));
		lieux.put("Arr�t L'Yvette (retour)", new Lieu("Arr�t L'Yvette (retour)"));
		lieux.put("Arr�t Ch�teau (aller)", new Lieu("Arr�t Ch�teau (aller)"));
		lieux.put("Arr�t Ch�teau (retour)", new Lieu("Arr�t Ch�teau (retour)"));
		lieux.put("Arr�t Bibliotheque (aller)", new Lieu("Arr�t Bibliotheque (aller)"));
		lieux.put("Arr�t Bibliotheque (retour)", new Lieu("Arr�t Bibliotheque (retour)"));
		lieux.put("Arr�t Georges Poitou (aller)", new Lieu("Arr�t Georges Poitou (aller)"));
		lieux.put("Arr�t Georges Poitou (retour)", new Lieu("Arr�t Georges Poitou (retour)"));
		lieux.put("Arr�t Verger (aller)", new Lieu("Arr�t Verger (aller)"));
		lieux.put("Arr�t Verger (retour)", new Lieu("Arr�t Verger (retour)"));
		lieux.put("Arr�t Bures Amphi (aller)", new Lieu("Arr�t Bures Amphi (aller)"));
		lieux.put("Arr�t Bures Amphi (retour)", new Lieu("Arr�t Bures Amphi (retour)"));
		lieux.put("Arr�t L'Isles (aller)", new Lieu("Arr�t L'Isles (aller)"));
		lieux.put("Arr�t L'Isles (retour)", new Lieu("Arr�t L'Isles (retour)"));
		lieux.put("Arr�t Jean Monnet (aller)", new Lieu("Arr�t Jean Monnet (aller)"));
		lieux.put("Arr�t Jean Monnet (retour)", new Lieu("Arr�t Jean Monnet (retour)"));
		lieux.put("Arr�t Launay (aller)", new Lieu("Arr�t Launay (aller)"));
		lieux.put("Arr�t Launay (retour)", new Lieu("Arr�t Launay (retour)"));
		lieux.put("Arr�t Bois des Rames (aller)", new Lieu("Arr�t Bois des Rames (aller)"));
		lieux.put("Arr�t Bois des Rames (retour)", new Lieu("Arr�t Bois des Rames (retour)"));
		lieux.put("Arr�t Belv�d�re (aller)", new Lieu("Arr�t Belv�d�re (aller)"));
		lieux.put("Arr�t Belv�d�re (retour)", new Lieu("Arr�t Belv�d�re (retour)"));
		lieux.put("Arr�t De Broglie (aller)", new Lieu("Arr�t De Broglie (aller)"));
		lieux.put("Arr�t De Broglie (retour)", new Lieu("Arr�t De Broglie (retour)"));
		lieux.put("Arr�t IUT Maison de l'ing�nieur (aller)", new Lieu("Arr�t IUT Maison de l'ing�nieur (aller)"));
		lieux.put("Arr�t IUT Maison de l'ing�nieur (retour)", new Lieu("Arr�t IUT Maison de l'ing�nieur (retour)"));
		lieux.put("Arr�t Moulon (aller)", new Lieu("Arr�t Moulon (aller)"));
		lieux.put("Arr�t Moulon (retour)", new Lieu("Arr�t Moulon (retour)"));
		lieux.put("Arr�t IBP (aller)", new Lieu("Arr�t IBP (aller)"));
		lieux.put("Arr�t IBP (retour)", new Lieu("Arr�t IBP (retour)"));
		lieux.put("Arr�t IUT P�le d'ingenerie (aller)", new Lieu("Arr�t IUT P�le d'ingenerie (aller)"));
		lieux.put("Arr�t IUT P�le d'ingenerie (retour)", new Lieu("Arr�t IUT P�le d'ingenerie (retour)"));
		lieux.put("Arr�t Corbeville (aller)", new Lieu("Arr�t Corbeville (aller)"));
		lieux.put("Arr�t Corbeville (retour)", new Lieu("Arr�t Corbeville (retour)"));
		
		// Voisins � pied
		new Etape(lieux.get("Batiment 102 - Institut de physique nucl�aire d'Orsay"), lieux.get("Arr�t L'Yvette (aller)"), MarcheAPied.getInstance(), 8, true);
		new Etape(lieux.get("Batiment 102 - Institut de physique nucl�aire d'Orsay"), lieux.get("Arr�t L'Yvette (retour)"), MarcheAPied.getInstance(), 8, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Batiment 333"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arr�t Jean Monnet (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arr�t Jean Monnet (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t Ch�teau (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t Ch�teau (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t L'Yvette (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t L'Yvette (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Batiment 336"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Arr�t Bures Amphi (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Arr�t Bures Amphi (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 336"), lieux.get("Batiment 337 - Salles d'examen"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Batiment 407 - Biblioth�que Universitaire"), MarcheAPied.getInstance(), 7, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Arr�t Verger (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Arr�t Verger (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 407 - Biblioth�que Universitaire"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 508 - LIMSI"), MarcheAPied.getInstance(), 15, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arr�t Launay (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arr�t Launay (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Arr�t Bibliotheque (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Arr�t Bibliotheque (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Arr�t Georges Poitou (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Arr�t Georges Poitou (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), lieux.get("Arr�t Bibliotheque (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), lieux.get("Arr�t Bibliotheque (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Arr�t Launay (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Arr�t Launay (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Arr�t Launay (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Arr�t Launay (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Batiment 508 - LIMSI"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Batiment 620 - Maison de l'Ing�nieur"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Arr�t Belv�d�re (aller)"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Arr�t Belv�d�re (retour)"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Batiment 620 - Maison de l'Ing�nieur"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arr�t De Broglie (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arr�t De Broglie (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Batiment 640 - PUIO"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("IUT d'Orsay"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("SUPELEC"), MarcheAPied.getInstance(), 9, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 640 - PUIO"), lieux.get("IUT d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arr�t Corbeville (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arr�t Corbeville (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("SUPELEC"), lieux.get("Arr�t Moulon (aller)"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("SUPELEC"), lieux.get("Arr�t Moulon (retour)"), MarcheAPied.getInstance(), 3, true);
		
		// Ligne de bus 06-07 Vall�e
		lignesBus.put("06-07 Vall�e", new Ligne("06-07 Vall�e", PrendreBus.getInstance()));
		new Etape(lieux.get("Arr�t L'Yvette (aller)"), lieux.get("Arr�t Ch�teau (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Ch�teau (aller)"), lieux.get("Arr�t Bibliotheque (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t Bibliotheque (aller)"), lieux.get("Arr�t Georges Poitou (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Georges Poitou (aller)"), lieux.get("Arr�t Verger (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Verger (aller)"), lieux.get("Arr�t Bures Amphi (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t Bures Amphi (aller)"), lieux.get("Arr�t L'Isles (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t L'Isles (aller)"), lieux.get("Arr�t Jean Monnet (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t Jean Monnet (retour)"), lieux.get("Arr�t L'Isles (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t L'Isles (retour)"), lieux.get("Arr�t Bures Amphi (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Bures Amphi (retour)"), lieux.get("Arr�t Verger (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t Verger (retour)"), lieux.get("Arr�t Georges Poitou (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Georges Poitou (retour)"), lieux.get("Arr�t Bibliotheque (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		new Etape(lieux.get("Arr�t Bibliotheque (retour)"), lieux.get("Arr�t Ch�teau (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 2);
		new Etape(lieux.get("Arr�t Ch�teau (retour)"), lieux.get("Arr�t L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vall�e"), 1.8/7, 1);
		
		// Ligne de bus 06-07 Plateau
		lignesBus.put("06-07 Plateau", new Ligne("06-07 Plateau", PrendreBus.getInstance()));
		new Etape(lieux.get("Arr�t L'Yvette (aller)"), lieux.get("Arr�t Ch�teau (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Ch�teau (aller)"), lieux.get("Arr�t Launay (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Launay (aller)"), lieux.get("Arr�t Bois des Rames (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 4);
		new Etape(lieux.get("Arr�t Bois des Rames (aller)"), lieux.get("Arr�t Belv�d�re (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Belv�d�re (aller)"), lieux.get("Arr�t De Broglie (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t De Broglie (aller)"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 2);
		new Etape(lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), lieux.get("Arr�t Moulon (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Moulon (aller)"), lieux.get("Arr�t IBP (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t IBP (aller)"), lieux.get("Arr�t IUT P�le d'ingenerie (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t IUT P�le d'ingenerie (aller)"), lieux.get("Arr�t Corbeville (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Corbeville (retour)"), lieux.get("Arr�t IUT P�le d'ingenerie (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t IUT P�le d'ingenerie (retour)"), lieux.get("Arr�t IBP (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t IBP (retour)"), lieux.get("Arr�t Moulon (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Moulon (retour)"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), lieux.get("Arr�t Arr�t De Broglie (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 2);
		new Etape(lieux.get("Arr�t De Broglie (retour)"), lieux.get("Arr�t Belv�d�re (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Belv�d�re (retour)"), lieux.get("Arr�t Bois des Rames (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Bois des Rames (retour)"), lieux.get("Arr�t Launay (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 4);
		new Etape(lieux.get("Arr�t Launay (retour)"), lieux.get("Arr�t Ch�teau (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arr�t Ch�teau (retour)"), lieux.get("Arr�t L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
	}
}
