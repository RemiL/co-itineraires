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
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Batiment 333"), lieux.get("Arrêt Bures Amphi (retour)"), MarcheAPied.getInstance()));
			trajets.get(0).ajouterEtape(new Etape(lieux.get("Arrêt Bures Amphi (retour)"), lieux.get("Arrêt L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée")));
			
			System.out.println(trajets.get(0));
			System.out.println("Coût : "+trajets.get(0).getCout());
			System.out.println("Durée : "+trajets.get(0).getDuree()+"\n");
			
			trajets.add(new Trajet());
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Batiment 407 - Bibliothèque Universitaire"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Batiment 300 - Présidence"), MarcheAPied.getInstance()));
			trajets.get(1).ajouterEtape(new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt L'Yvette (retour)"), MarcheAPied.getInstance()));
			
			System.out.println(trajets.get(1));
			System.out.println("Coût : "+trajets.get(1).getCout());
			System.out.println("Durée : "+trajets.get(1).getDuree()+"\n");
			
			contraintes.add(new MinimiserCoutTrajet());
			usagers.add(new Usager("John Doe", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arrêt L'Yvette (retour)"), trajets, contraintes.get(0), 0));
			contraintes.add(new MinimiserTempsTrajet());
			usagers.add(new Usager("Jane Doe", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arrêt L'Yvette (retour)"), trajets, contraintes.get(1), 0));
			usagers.add(new Usager("John Smith", lieux.get("Batiment 336"), lieux.get("Arrêt L'Yvette (retour)"), trajets, contraintes.get(1), 0));
			contraintes.add(new EviterLieu(lieux.get("Batiment 300 - Présidence")));
			usagers.add(new Usager("Jane Smith", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arrêt L'Yvette (retour)"), trajets, contraintes.get(2), 0));
			contraintes.add(new EviterMoyenTransport(PrendreBus.getInstance()));
			usagers.add(new Usager("John Martin", lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Arrêt L'Yvette (retour)"), trajets, contraintes.get(3), 0));
			
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
		// Bâtiments
		lieux.put("Batiment 102 - Institut de physique nucléaire d'Orsay", new Lieu("Batiment 102 - Institut de physique nucléaire d'Orsay"));
		lieux.put("Batiment 230 - Restaurant Universitaire de Bures", new Lieu("Batiment 230 - Restaurant Universitaire de Bures"));
		lieux.put("Batiment 300 - Présidence", new Lieu("Batiment 300 - Présidence"));
		lieux.put("Batiment 333", new Lieu("Batiment 333"));
		lieux.put("Batiment 336", new Lieu("Batiment 336"));
		lieux.put("Batiment 337 - Salles d'examen", new Lieu("Batiment 337 - Salles d'examen"));
		lieux.put("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution", new Lieu("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"));
		lieux.put("Batiment 406 - Restaurant Universitaire d'Orsay", new Lieu("Batiment 406 - Restaurant Universitaire d'Orsay"));
		lieux.put("Batiment 407 - Bibliothèque Universitaire", new Lieu("Batiment 407 - Bibliothèque Universitaire"));
		lieux.put("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay", new Lieu("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"));
		lieux.put("Batiment 425 - Laboratoire de Mathématiques d'Orsay", new Lieu("Batiment 425 - Laboratoire de Mathématiques d'Orsay"));
		lieux.put("Batiment 470", new Lieu("Batiment 470"));
		lieux.put("Batiment 490 - LRI", new Lieu("Batiment 490 - LRI"));
		lieux.put("Batiment 505 - Laboratoire Aimé Cotton", new Lieu("Batiment 505 - Laboratoire Aimé Cotton"));
		lieux.put("Batiment 508 - LIMSI", new Lieu("Batiment 508 - LIMSI"));
		lieux.put("Batiment 620 - Maison de l'Ingénieur", new Lieu("Batiment 620 - Maison de l'Ingénieur"));
		lieux.put("Batiment 640 - PUIO", new Lieu("Batiment 640 - PUIO"));
		lieux.put("IUT d'Orsay", new Lieu("IUT d'Orsay"));
		lieux.put("SUPELEC", new Lieu("SUPELEC"));
		
		// Arrêts de bus
		lieux.put("Arrêt L'Yvette (aller)", new Lieu("Arrêt L'Yvette (aller)"));
		lieux.put("Arrêt L'Yvette (retour)", new Lieu("Arrêt L'Yvette (retour)"));
		lieux.put("Arrêt Château (aller)", new Lieu("Arrêt Château (aller)"));
		lieux.put("Arrêt Château (retour)", new Lieu("Arrêt Château (retour)"));
		lieux.put("Arrêt Bibliotheque (aller)", new Lieu("Arrêt Bibliotheque (aller)"));
		lieux.put("Arrêt Bibliotheque (retour)", new Lieu("Arrêt Bibliotheque (retour)"));
		lieux.put("Arrêt Georges Poitou (aller)", new Lieu("Arrêt Georges Poitou (aller)"));
		lieux.put("Arrêt Georges Poitou (retour)", new Lieu("Arrêt Georges Poitou (retour)"));
		lieux.put("Arrêt Verger (aller)", new Lieu("Arrêt Verger (aller)"));
		lieux.put("Arrêt Verger (retour)", new Lieu("Arrêt Verger (retour)"));
		lieux.put("Arrêt Bures Amphi (aller)", new Lieu("Arrêt Bures Amphi (aller)"));
		lieux.put("Arrêt Bures Amphi (retour)", new Lieu("Arrêt Bures Amphi (retour)"));
		lieux.put("Arrêt L'Isles (aller)", new Lieu("Arrêt L'Isles (aller)"));
		lieux.put("Arrêt L'Isles (retour)", new Lieu("Arrêt L'Isles (retour)"));
		lieux.put("Arrêt Jean Monnet (aller)", new Lieu("Arrêt Jean Monnet (aller)"));
		lieux.put("Arrêt Jean Monnet (retour)", new Lieu("Arrêt Jean Monnet (retour)"));
		lieux.put("Arrêt Launay (aller)", new Lieu("Arrêt Launay (aller)"));
		lieux.put("Arrêt Launay (retour)", new Lieu("Arrêt Launay (retour)"));
		lieux.put("Arrêt Bois des Rames (aller)", new Lieu("Arrêt Bois des Rames (aller)"));
		lieux.put("Arrêt Bois des Rames (retour)", new Lieu("Arrêt Bois des Rames (retour)"));
		lieux.put("Arrêt Belvédère (aller)", new Lieu("Arrêt Belvédère (aller)"));
		lieux.put("Arrêt Belvédère (retour)", new Lieu("Arrêt Belvédère (retour)"));
		lieux.put("Arrêt De Broglie (aller)", new Lieu("Arrêt De Broglie (aller)"));
		lieux.put("Arrêt De Broglie (retour)", new Lieu("Arrêt De Broglie (retour)"));
		lieux.put("Arrêt IUT Maison de l'ingénieur (aller)", new Lieu("Arrêt IUT Maison de l'ingénieur (aller)"));
		lieux.put("Arrêt IUT Maison de l'ingénieur (retour)", new Lieu("Arrêt IUT Maison de l'ingénieur (retour)"));
		lieux.put("Arrêt Moulon (aller)", new Lieu("Arrêt Moulon (aller)"));
		lieux.put("Arrêt Moulon (retour)", new Lieu("Arrêt Moulon (retour)"));
		lieux.put("Arrêt IBP (aller)", new Lieu("Arrêt IBP (aller)"));
		lieux.put("Arrêt IBP (retour)", new Lieu("Arrêt IBP (retour)"));
		lieux.put("Arrêt IUT Pôle d'ingenerie (aller)", new Lieu("Arrêt IUT Pôle d'ingenerie (aller)"));
		lieux.put("Arrêt IUT Pôle d'ingenerie (retour)", new Lieu("Arrêt IUT Pôle d'ingenerie (retour)"));
		lieux.put("Arrêt Corbeville (aller)", new Lieu("Arrêt Corbeville (aller)"));
		lieux.put("Arrêt Corbeville (retour)", new Lieu("Arrêt Corbeville (retour)"));
		
		// Voisins à pied
		new Etape(lieux.get("Batiment 102 - Institut de physique nucléaire d'Orsay"), lieux.get("Arrêt L'Yvette (aller)"), MarcheAPied.getInstance(), 8, true);
		new Etape(lieux.get("Batiment 102 - Institut de physique nucléaire d'Orsay"), lieux.get("Arrêt L'Yvette (retour)"), MarcheAPied.getInstance(), 8, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Batiment 333"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arrêt Jean Monnet (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arrêt Jean Monnet (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt Château (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt Château (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt L'Yvette (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt L'Yvette (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Batiment 336"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Arrêt Bures Amphi (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 333"), lieux.get("Arrêt Bures Amphi (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 336"), lieux.get("Batiment 337 - Salles d'examen"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Batiment 407 - Bibliothèque Universitaire"), MarcheAPied.getInstance(), 7, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Arrêt Verger (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Arrêt Verger (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 407 - Bibliothèque Universitaire"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 508 - LIMSI"), MarcheAPied.getInstance(), 15, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arrêt Launay (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arrêt Launay (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Arrêt Bibliotheque (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Arrêt Bibliotheque (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Arrêt Georges Poitou (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Arrêt Georges Poitou (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), lieux.get("Arrêt Bibliotheque (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), lieux.get("Arrêt Bibliotheque (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Arrêt Launay (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 470"), lieux.get("Arrêt Launay (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Arrêt Launay (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 490 - LRI"), lieux.get("Arrêt Launay (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Batiment 508 - LIMSI"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Batiment 620 - Maison de l'Ingénieur"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Arrêt Belvédère (aller)"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Arrêt Belvédère (retour)"), MarcheAPied.getInstance(), 4, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Batiment 620 - Maison de l'Ingénieur"), MarcheAPied.getInstance(), 5, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arrêt De Broglie (aller)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arrêt De Broglie (retour)"), MarcheAPied.getInstance(), 1, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Batiment 640 - PUIO"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("IUT d'Orsay"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("SUPELEC"), MarcheAPied.getInstance(), 9, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("Batiment 640 - PUIO"), lieux.get("IUT d'Orsay"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arrêt Corbeville (aller)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("IUT d'Orsay"), lieux.get("Arrêt Corbeville (retour)"), MarcheAPied.getInstance(), 2, true);
		new Etape(lieux.get("SUPELEC"), lieux.get("Arrêt Moulon (aller)"), MarcheAPied.getInstance(), 3, true);
		new Etape(lieux.get("SUPELEC"), lieux.get("Arrêt Moulon (retour)"), MarcheAPied.getInstance(), 3, true);
		
		// Ligne de bus 06-07 Vallée
		lignesBus.put("06-07 Vallée", new Ligne("06-07 Vallée", PrendreBus.getInstance()));
		new Etape(lieux.get("Arrêt L'Yvette (aller)"), lieux.get("Arrêt Château (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Château (aller)"), lieux.get("Arrêt Bibliotheque (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt Bibliotheque (aller)"), lieux.get("Arrêt Georges Poitou (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Georges Poitou (aller)"), lieux.get("Arrêt Verger (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Verger (aller)"), lieux.get("Arrêt Bures Amphi (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt Bures Amphi (aller)"), lieux.get("Arrêt L'Isles (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt L'Isles (aller)"), lieux.get("Arrêt Jean Monnet (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt Jean Monnet (retour)"), lieux.get("Arrêt L'Isles (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt L'Isles (retour)"), lieux.get("Arrêt Bures Amphi (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Bures Amphi (retour)"), lieux.get("Arrêt Verger (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt Verger (retour)"), lieux.get("Arrêt Georges Poitou (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Georges Poitou (retour)"), lieux.get("Arrêt Bibliotheque (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		new Etape(lieux.get("Arrêt Bibliotheque (retour)"), lieux.get("Arrêt Château (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 2);
		new Etape(lieux.get("Arrêt Château (retour)"), lieux.get("Arrêt L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Vallée"), 1.8/7, 1);
		
		// Ligne de bus 06-07 Plateau
		lignesBus.put("06-07 Plateau", new Ligne("06-07 Plateau", PrendreBus.getInstance()));
		new Etape(lieux.get("Arrêt L'Yvette (aller)"), lieux.get("Arrêt Château (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Château (aller)"), lieux.get("Arrêt Launay (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Launay (aller)"), lieux.get("Arrêt Bois des Rames (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 4);
		new Etape(lieux.get("Arrêt Bois des Rames (aller)"), lieux.get("Arrêt Belvédère (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Belvédère (aller)"), lieux.get("Arrêt De Broglie (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt De Broglie (aller)"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 2);
		new Etape(lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), lieux.get("Arrêt Moulon (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Moulon (aller)"), lieux.get("Arrêt IBP (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt IBP (aller)"), lieux.get("Arrêt IUT Pôle d'ingenerie (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt IUT Pôle d'ingenerie (aller)"), lieux.get("Arrêt Corbeville (aller)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Corbeville (retour)"), lieux.get("Arrêt IUT Pôle d'ingenerie (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt IUT Pôle d'ingenerie (retour)"), lieux.get("Arrêt IBP (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt IBP (retour)"), lieux.get("Arrêt Moulon (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Moulon (retour)"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), lieux.get("Arrêt Arrêt De Broglie (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 2);
		new Etape(lieux.get("Arrêt De Broglie (retour)"), lieux.get("Arrêt Belvédère (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Belvédère (retour)"), lieux.get("Arrêt Bois des Rames (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Bois des Rames (retour)"), lieux.get("Arrêt Launay (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 4);
		new Etape(lieux.get("Arrêt Launay (retour)"), lieux.get("Arrêt Château (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
		new Etape(lieux.get("Arrêt Château (retour)"), lieux.get("Arrêt L'Yvette (retour)"), PrendreBus.getInstance(), lignesBus.get("06-07 Plateau"), 1.8/10, 1);
	}
}
