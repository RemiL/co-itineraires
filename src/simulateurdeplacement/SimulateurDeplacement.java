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
		new EtapeAPied(lieux.get("Batiment 102 - Institut de physique nucléaire d'Orsay"), lieux.get("Arrêt L'Yvette (aller)"), 8);
		new EtapeAPied(lieux.get("Batiment 102 - Institut de physique nucléaire d'Orsay"), lieux.get("Arrêt L'Yvette (retour)"), 8);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Batiment 333"), 5);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arrêt Jean Monnet (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 230 - Restaurant Universitaire de Bures"), lieux.get("Arrêt Jean Monnet (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 300 - Présidence"), lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), 4);
		new EtapeAPied(lieux.get("Batiment 300 - Présidence"), lieux.get("Batiment 490 - LRI"), 5);
		new EtapeAPied(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt Château (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 300 - Présidence"), lieux.get("Arrêt Château (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 333"), lieux.get("Batiment 336"), 1);
		new EtapeAPied(lieux.get("Batiment 336"), lieux.get("Batiment 337 - Salles d'examen"), 1);
		new EtapeAPied(lieux.get("Batiment 337 - Salles d'examen"), lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), 5);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Batiment 407 - Bibliothèque Universitaire"), 7);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), 5);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Arrêt Verger (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 362 - Laboratoire Ecologie, Systématique et Evolution"), lieux.get("Arrêt Verger (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 407 - Bibliothèque Universitaire"), 3);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), 3);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 470"), 5);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Batiment 508 - LIMSI"), 15);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arrêt Launay (aller)"), 2);
		new EtapeAPied(lieux.get("Batiment 406 - Restaurant Universitaire d'Orsay"), lieux.get("Arrêt Launay (retour)"), 2);
		new EtapeAPied(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), 2);
		new EtapeAPied(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get("Arrêt Bibliotheque (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 407 - Bibliothèque Universitaire"), lieux.get(24), 1);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), 2);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Arrêt Georges Poitou (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 410 - Institut de Chimie Moléculaire et des Matériaux d'Orsay"), lieux.get("Arrêt Georges Poitou (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), lieux.get("Arrêt Bibliotheque (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 425 - Laboratoire de Mathématiques d'Orsay"), lieux.get("Arrêt Bibliotheque (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Batiment 490 - LRI"), 3);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Arrêt Launay (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 470"), lieux.get("Arrêt Launay (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 490 - LRI"), lieux.get("Arrêt Launay (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 490 - LRI"), lieux.get("Arrêt Launay (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Batiment 508 - LIMSI"), 4);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Batiment 620 - Maison de l'Ingénieur"), 5);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Arrêt Belvédère (aller)"), 4);
		new EtapeAPied(lieux.get("Batiment 505 - Laboratoire Aimé Cotton"), lieux.get("Arrêt Belvédère (retour)"), 4);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Batiment 620 - Maison de l'Ingénieur"), 5);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arrêt De Broglie (aller)"), 1);
		new EtapeAPied(lieux.get("Batiment 508 - LIMSI"), lieux.get("Arrêt De Broglie (retour)"), 1);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Batiment 640 - PUIO"), 3);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("IUT d'Orsay"), 3);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("SUPELEC"), 9);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), 2);
		new EtapeAPied(lieux.get("Batiment 620 - Maison de l'Ingénieur"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), 2);
		new EtapeAPied(lieux.get("Batiment 640 - PUIO"), lieux.get("IUT d'Orsay"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arrêt Corbeville (aller)"), 2);
		new EtapeAPied(lieux.get("IUT d'Orsay"), lieux.get("Arrêt Corbeville (retour)"), 2);
		new EtapeAPied(lieux.get("SUPELEC"), lieux.get("Arrêt Moulon (aller)"), 3);
		new EtapeAPied(lieux.get("SUPELEC"), lieux.get("Arrêt Moulon (retour)"), 3);
		
		// Ligne de bus 06-07 Vallée
		lignesBus.put("06-07 Vallée", new LigneBus("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt L'Yvette (aller)"), lieux.get("Arrêt Château (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Château (aller)"), lieux.get("Arrêt Bibliotheque (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Bibliotheque (aller)"), lieux.get("Arrêt Georges Poitou (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Georges Poitou (aller)"), lieux.get("Arrêt Verger (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Verger (aller)"), lieux.get("Arrêt Bures Amphi (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Bures Amphi (aller)"), lieux.get("Arrêt L'Isles (aller)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt L'Isles (aller)"), lieux.get("Arrêt Jean Monnet (aller)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Jean Monnet (retour)"), lieux.get("Arrêt L'Isles (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt L'Isles (retour)"), lieux.get("Arrêt Bures Amphi (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Bures Amphi (retour)"), lieux.get("Arrêt Verger (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Verger (retour)"), lieux.get("Arrêt Georges Poitou (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Georges Poitou (retour)"), lieux.get("Arrêt Bibliotheque (retour)"), 1.8/8, 1, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Bibliotheque (retour)"), lieux.get("Arrêt Château (retour)"), 1.8/7, 2, lignesBus.get("06-07 Vallée"));
		new EtapeEnBus(lieux.get("Arrêt Château (retour)"), lieux.get("Arrêt L'Yvette (retour)"), 1.8/7, 1, lignesBus.get("06-07 Vallée"));
		
		// Ligne de bus 06-07 Plateau
		lignesBus.put("06-07 Plateau", new LigneBus("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt L'Yvette (aller)"), lieux.get("Arrêt Château (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Château (aller)"), lieux.get("Arrêt Launay (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Launay (aller)"), lieux.get("Arrêt Bois des Rames (aller)"), 1.8/10, 4, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Bois des Rames (aller)"), lieux.get("Arrêt Belvédère (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Belvédère (aller)"), lieux.get("Arrêt De Broglie (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt De Broglie (aller)"), lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), 1.8/10, 2, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IUT Maison de l'ingénieur (aller)"), lieux.get("Arrêt Moulon (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Moulon (aller)"), lieux.get("Arrêt IBP (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IBP (aller)"), lieux.get("Arrêt IUT Pôle d'ingenerie (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IUT Pôle d'ingenerie (aller)"), lieux.get("Arrêt Corbeville (aller)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Corbeville (retour)"), lieux.get("Arrêt IUT Pôle d'ingenerie (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IUT Pôle d'ingenerie (retour)"), lieux.get("Arrêt IBP (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IBP (retour)"), lieux.get("Arrêt Moulon (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Moulon (retour)"), lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt IUT Maison de l'ingénieur (retour)"), lieux.get("Arrêt Arrêt De Broglie (retour)"), 1.8/10, 2, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt De Broglie (retour)"), lieux.get("Arrêt Belvédère (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Belvédère (retour)"), lieux.get("Arrêt Bois des Rames (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Bois des Rames (retour)"), lieux.get("Arrêt Launay (retour)"), 1.8/10, 4, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Launay (retour)"), lieux.get("Arrêt Château (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
		new EtapeEnBus(lieux.get("Arrêt Château (retour)"), lieux.get("Arrêt L'Yvette (retour)"), 1.8/10, 1, lignesBus.get("06-07 Plateau"));
	}
}
