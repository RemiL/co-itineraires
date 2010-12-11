package simulateurdeplacement;

import graphe.EtapeAPied;
import graphe.EtapeEnBus;
import graphe.Lieu;

import java.util.HashMap;

import ligne.LigneBus;

import trajet.Trajet;

public class SimulateurDeplacement 
{
	static private HashMap<String, Lieu> lieux = new HashMap<String, Lieu>();
	static private HashMap<String, LigneBus> lignesBus = new HashMap<String, LigneBus>();
		
	public static void main(String[] args)
	{
		initialiserLieux();
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
		new EtapeAPied(lieux.get("Batiment 102 - Institut de physique nucl�aire d'Orsay"), lieux.get("Arr�t L'Yvette (aller)"), 8);
		new EtapeAPied(lieux.get("Batiment 102 - Institut de physique nucl�aire d'Orsay"), lieux.get("Arr�t L'Yvette (retour)"), 8);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Batiment 333"), 5);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arr�t Jean Monnet (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arr�t Jean Monnet (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), 4);
		new EtapeAPied(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Batiment 490 - LRI"), 5);
		new EtapeAPied(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t Ch�teau (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 300 - Pr�sidence"), lieux.get("Arr�t Ch�teau (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 333"), lieux.get("Batiment 336"), 1);
		new EtapeAPied(lieux.get("Batiment 336"), lieux.get("Batiment 337 - Salles d'examen"), 1);
		new EtapeAPied(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), 5);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Batiment 407 - Biblioth�que Universitaire"), 7);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), 5);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Arr�t Verger (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution"), lieux.get("Arr�t Verger (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 407 - Biblioth�que Universitaire"), 3);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), 3);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), 5);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 508 - LIMSI"), 15);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arr�t Launay (aller)"), 2);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arr�t Launay (retour)"), 2);
		new EtapeAPied(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), 2);
		new EtapeAPied(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get("Arr�t Bibliotheque (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 407 - Biblioth�que Universitaire"), lieux.get(24), 1);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), 2);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Arr�t Georges Poitou (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"), lieux.get("Arr�t Georges Poitou (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), lieux.get("Arr�t Bibliotheque (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 425 - Laboratoire de Math�matiques d'Orsay"), lieux.get("Arr�t Bibliotheque (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), 3);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Arr�t Launay (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Arr�t Launay (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 490 - LRI"), lieux.get("Arr�t Launay (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 490 - LRI"), lieux.get("Arr�t Launay (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Batiment 508 - LIMSI"), 4);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Batiment 620 - Maison de l'Ing�nieur"), 5);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Arr�t Belv�d�re (aller)"), 4);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aim� Cotton"), lieux.get("Arr�t Belv�d�re (retour)"), 4);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Batiment 620 - Maison de l'Ing�nieur"), 5);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arr�t De Broglie (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arr�t De Broglie (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Batiment 640 - PUIO"), 3);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("IUT d'Orsay"), 3);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("SUPELEC"), 9);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), 2);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ing�nieur"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), 2);
		new EtapeAPied(lieux.get("Batiment 640 - PUIO"), lieux.get("IUT d'Orsay"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arr�t Corbeville (aller)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arr�t Corbeville (retour)"), 2);
		new EtapeAPied(lieux.get("SUPELEC"), lieux.get("Arr�t Moulon (aller)"), 3);
		new EtapeAPied(lieux.get("SUPELEC"), lieux.get("Arr�t Moulon (retour)"), 3);
		
		// Ligne de bus 06-07 Vall�e
		lignesBus.put("06-07 Vall�e", new LigneBus("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t L'Yvette (aller)"), lieux.get("Arr�t Ch�teau (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Ch�teau (aller)"), lieux.get("Arr�t Bibliotheque (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Bibliotheque (aller)"), lieux.get("Arr�t Georges Poitou (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Georges Poitou (aller)"), lieux.get("Arr�t Verger (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Verger (aller)"), lieux.get("Arr�t Bures Amphi (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Bures Amphi (aller)"), lieux.get("Arr�t L'Isles (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t L'Isles (aller)"), lieux.get("Arr�t Jean Monnet (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Jean Monnet (retour)"), lieux.get("Arr�t L'Isles (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t L'Isles (retour)"), lieux.get("Arr�t Bures Amphi (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Bures Amphi (retour)"), lieux.get("Arr�t Verger (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Verger (retour)"), lieux.get("Arr�t Georges Poitou (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Georges Poitou (retour)"), lieux.get("Arr�t Bibliotheque (retour)"), 1.8/8, 1, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Bibliotheque (retour)"), lieux.get("Arr�t Ch�teau (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vall�e"));
		new EtapeEnBus(lieux.get("Arr�t Ch�teau (retour)"), lieux.get("Arr�t L'Yvette (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vall�e"));
		
		// Ligne de bus 06-07 Plateau
		lignesBus.put("06-07 Plateau", new LigneBus("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t L'Yvette (aller)"), lieux.get("Arr�t Ch�teau (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Ch�teau (aller)"), lieux.get("Arr�t Launay (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Launay (aller)"), lieux.get("Arr�t Bois des Rames (aller)"), 1.8/10, 4, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Bois des Rames (aller)"), lieux.get("Arr�t Belv�d�re (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Belv�d�re (aller)"), lieux.get("Arr�t De Broglie (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t De Broglie (aller)"), lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), 1.8/10, 2, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IUT Maison de l'ing�nieur (aller)"), lieux.get("Arr�t Moulon (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Moulon (aller)"), lieux.get("Arr�t IBP (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IBP (aller)"), lieux.get("Arr�t IUT P�le d'ingenerie (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IUT P�le d'ingenerie (aller)"), lieux.get("Arr�t Corbeville (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Corbeville (retour)"), lieux.get("Arr�t IUT P�le d'ingenerie (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IUT P�le d'ingenerie (retour)"), lieux.get("Arr�t IBP (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IBP (retour)"), lieux.get("Arr�t Moulon (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Moulon (retour)"), lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t IUT Maison de l'ing�nieur (retour)"), lieux.get("Arr�t Arr�t De Broglie (retour)"), 1.8/10, 2, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t De Broglie (retour)"), lieux.get("Arr�t Belv�d�re (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Belv�d�re (retour)"), lieux.get("Arr�t Bois des Rames (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Bois des Rames (retour)"), lieux.get("Arr�t Launay (retour)"), 1.8/10, 4, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Launay (retour)"), lieux.get("Arr�t Ch�teau (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arr�t Ch�teau (retour)"), lieux.get("Arr�t L'Yvette (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
	}
}
