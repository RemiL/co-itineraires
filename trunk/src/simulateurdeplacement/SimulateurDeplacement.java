package simulateurdeplacement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import trajet.Etape;
import trajet.EtapeAPied;
import trajet.Trajet;

import lieu.ArretBus;
import lieu.Batiment;
import lieu.Lieu;
import ligne.LigneBus;

public class SimulateurDeplacement 
{
	static private ArrayList<Lieu> lieux = new ArrayList<Lieu>();
	static private ArrayList<ArretBus> arretsBus = new ArrayList<ArretBus>();
	static private ArrayList<LigneBus> lignesBus = new ArrayList<LigneBus>();
	
	static private Trajet trajet;
	
	public static void main(String[] args)
	{
		boolean continuer = true;
		
		initialiserLieux();
		
		do
		{
			trajet = creerTrajet();
			continuer = false;
		} while(continuer);
	}
	
	/**
	 * Permet d'initialiser les lieux connus par le simulateur.
	 */
	private static void initialiserLieux()
	{
		// B�timents
		lieux.add(new Batiment("Batiment 102 - Institut de physique nucl�aire d'Orsay")); // 0
		lieux.add(new Batiment("Batiment 230 - Restaurant Universitaire de Bures"));
		lieux.add(new Batiment("Batiment 300 - Pr�sidence"));
		lieux.add(new Batiment("Batiment 333"));
		lieux.add(new Batiment("Batiment 336"));
		lieux.add(new Batiment("Batiment 337 - Salles d'examen"));
		lieux.add(new Batiment("Batiment 362 - Laboratoire Ecologie, Syst�matique et Evolution")); // 6
		lieux.add(new Batiment("Batiment 406 - Restaurant Universitaire d'Orsay"));
		lieux.add(new Batiment("Batiment 407 - Biblioth�que Universitaire"));
		lieux.add(new Batiment("Batiment 410 - Institut de Chimie Mol�culaire et des Mat�riaux d'Orsay"));
		lieux.add(new Batiment("Batiment 425 - Laboratoire de Math�matiques d'Orsay"));
		lieux.add(new Batiment("Batiment 470"));
		lieux.add(new Batiment("Batiment 490 - LRI")); // 12
		lieux.add(new Batiment("Batiment 505 - Laboratoire Aim� Cotton"));
		lieux.add(new Batiment("Batiment 508 - LIMSI"));
		lieux.add(new Batiment("Batiment 620 - Maison de l'Ing�nieur"));
		lieux.add(new Batiment("Batiment 640 - PUIO"));
		lieux.add(new Batiment("IUT d'Orsay"));
		lieux.add(new Batiment("SUPELEC")); // 18
		
		// Arr�ts de bus
		arretsBus.add(new ArretBus("L'Yvette (aller)")); // 19
		arretsBus.add(new ArretBus("L'Yvette (retour)"));
		arretsBus.add(new ArretBus("Ch�teau (aller)"));
		arretsBus.add(new ArretBus("Ch�teau (retour)"));
		arretsBus.add(new ArretBus("Bibliotheque (aller)"));
		arretsBus.add(new ArretBus("Bibliotheque (retour)"));
		arretsBus.add(new ArretBus("Georges Poitou (aller)"));
		arretsBus.add(new ArretBus("Georges Poitou (retour)")); // 26
		arretsBus.add(new ArretBus("Verger (aller)"));
		arretsBus.add(new ArretBus("Verger (retour)"));
		arretsBus.add(new ArretBus("Bures Amphi (aller)"));
		arretsBus.add(new ArretBus("Bures Amphi (retour)"));
		arretsBus.add(new ArretBus("L'Isles (aller)"));
		arretsBus.add(new ArretBus("L'Isles (retour)")); // 32
		arretsBus.add(new ArretBus("Jean Monnet (aller)"));
		arretsBus.add(new ArretBus("Jean Monnet (retour)"));
		arretsBus.add(new ArretBus("Launay (aller)"));
		arretsBus.add(new ArretBus("Launay (retour)"));
		arretsBus.add(new ArretBus("Bois des Rames (aller)"));
		arretsBus.add(new ArretBus("Bois des Rames (retour)"));
		arretsBus.add(new ArretBus("Belv�d�re (aller)"));
		arretsBus.add(new ArretBus("Belv�d�re (retour)")); // 40
		arretsBus.add(new ArretBus("De Broglie (aller)"));
		arretsBus.add(new ArretBus("De Broglie (retour)"));
		arretsBus.add(new ArretBus("IUT Maison de l'ing�nieur (aller)"));
		arretsBus.add(new ArretBus("IUT Maison de l'ing�nieur (retour)"));
		arretsBus.add(new ArretBus("Moulon (aller)"));
		arretsBus.add(new ArretBus("Moulon (retour)"));
		arretsBus.add(new ArretBus("IBP (aller)"));
		arretsBus.add(new ArretBus("IBP (retour)")); // 48
		arretsBus.add(new ArretBus("IUT P�le d'ingenerie (aller)"));
		arretsBus.add(new ArretBus("IUT P�le d'ingenerie (retour)"));
		arretsBus.add(new ArretBus("Corbeville (aller)"));
		arretsBus.add(new ArretBus("Corbeville (retour)")); // 52
		
		lieux.addAll(arretsBus);
		
		// Voisins � pied
		Lieu.rendreVoisinsAPied(lieux.get(0), lieux.get(19), 8);
		Lieu.rendreVoisinsAPied(lieux.get(0), lieux.get(20), 8);
		Lieu.rendreVoisinsAPied(lieux.get(1), lieux.get(3), 5);
		Lieu.rendreVoisinsAPied(lieux.get(1), lieux.get(33), 1);
		Lieu.rendreVoisinsAPied(lieux.get(1), lieux.get(34), 1);
		Lieu.rendreVoisinsAPied(lieux.get(2), lieux.get(7), 4);
		Lieu.rendreVoisinsAPied(lieux.get(2), lieux.get(12), 5);
		Lieu.rendreVoisinsAPied(lieux.get(2), lieux.get(21), 1);
		Lieu.rendreVoisinsAPied(lieux.get(2), lieux.get(22), 1);
		Lieu.rendreVoisinsAPied(lieux.get(3), lieux.get(4), 1);
		Lieu.rendreVoisinsAPied(lieux.get(4), lieux.get(5), 1);
		Lieu.rendreVoisinsAPied(lieux.get(5), lieux.get(6), 5);
		Lieu.rendreVoisinsAPied(lieux.get(6), lieux.get(8), 7);
		Lieu.rendreVoisinsAPied(lieux.get(6), lieux.get(9), 5);
		Lieu.rendreVoisinsAPied(lieux.get(6), lieux.get(27), 1);
		Lieu.rendreVoisinsAPied(lieux.get(6), lieux.get(28), 1);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(8), 3);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(10), 3);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(11), 5);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(14), 15);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(35), 2);
		Lieu.rendreVoisinsAPied(lieux.get(7), lieux.get(36), 2);
		Lieu.rendreVoisinsAPied(lieux.get(8), lieux.get(10), 2);
		Lieu.rendreVoisinsAPied(lieux.get(8), lieux.get(23), 1);
		Lieu.rendreVoisinsAPied(lieux.get(8), lieux.get(24), 1);
		Lieu.rendreVoisinsAPied(lieux.get(9), lieux.get(10), 2);
		Lieu.rendreVoisinsAPied(lieux.get(9), lieux.get(25), 1);
		Lieu.rendreVoisinsAPied(lieux.get(9), lieux.get(26), 1);
		Lieu.rendreVoisinsAPied(lieux.get(10), lieux.get(23), 1);
		Lieu.rendreVoisinsAPied(lieux.get(10), lieux.get(24), 1);
		Lieu.rendreVoisinsAPied(lieux.get(11), lieux.get(12), 3);
		Lieu.rendreVoisinsAPied(lieux.get(11), lieux.get(35), 1);
		Lieu.rendreVoisinsAPied(lieux.get(11), lieux.get(36), 1);
		Lieu.rendreVoisinsAPied(lieux.get(12), lieux.get(35), 1);
		Lieu.rendreVoisinsAPied(lieux.get(12), lieux.get(36), 1);
		Lieu.rendreVoisinsAPied(lieux.get(13), lieux.get(14), 4);
		Lieu.rendreVoisinsAPied(lieux.get(13), lieux.get(15), 5);
		Lieu.rendreVoisinsAPied(lieux.get(13), lieux.get(39), 4);
		Lieu.rendreVoisinsAPied(lieux.get(13), lieux.get(40), 4);
		Lieu.rendreVoisinsAPied(lieux.get(14), lieux.get(15), 5);
		Lieu.rendreVoisinsAPied(lieux.get(14), lieux.get(41), 1);
		Lieu.rendreVoisinsAPied(lieux.get(14), lieux.get(42), 1);
		Lieu.rendreVoisinsAPied(lieux.get(15), lieux.get(16), 3);
		Lieu.rendreVoisinsAPied(lieux.get(15), lieux.get(17), 3);
		Lieu.rendreVoisinsAPied(lieux.get(15), lieux.get(18), 9);
		Lieu.rendreVoisinsAPied(lieux.get(15), lieux.get(43), 2);
		Lieu.rendreVoisinsAPied(lieux.get(15), lieux.get(44), 2);
		Lieu.rendreVoisinsAPied(lieux.get(16), lieux.get(17), 2);
		Lieu.rendreVoisinsAPied(lieux.get(17), lieux.get(43), 2);
		Lieu.rendreVoisinsAPied(lieux.get(17), lieux.get(44), 2);
		Lieu.rendreVoisinsAPied(lieux.get(17), lieux.get(49), 2);
		Lieu.rendreVoisinsAPied(lieux.get(17), lieux.get(50), 2);
		Lieu.rendreVoisinsAPied(lieux.get(18), lieux.get(45), 3);
		Lieu.rendreVoisinsAPied(lieux.get(18), lieux.get(46), 3);
		
		// Ligne de bus 06-07 Vall�e
		lignesBus.add(new LigneBus("06-07 Vall�e", 1.80));
		lignesBus.get(0).ajouterArret(arretsBus.get(0), 12);
		lignesBus.get(0).ajouterArret(arretsBus.get(2), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(4), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(6), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(8), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(10), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(12), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(14), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(15), 12);
		lignesBus.get(0).ajouterArret(arretsBus.get(13), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(11), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(9), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(7), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(5), 1);
		lignesBus.get(0).ajouterArret(arretsBus.get(3), 2);
		lignesBus.get(0).ajouterArret(arretsBus.get(1), 1);
		
		// Ligne de bus 06-07 Plateau
		lignesBus.add(new LigneBus("06-07 Plateau", 1.80));
		lignesBus.get(1).ajouterArret(arretsBus.get(0), 12);
		lignesBus.get(1).ajouterArret(arretsBus.get(2), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(16), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(18), 4);
		lignesBus.get(1).ajouterArret(arretsBus.get(20), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(22), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(24), 2);
		lignesBus.get(1).ajouterArret(arretsBus.get(26), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(28), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(30), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(32), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(33), 12);
		lignesBus.get(1).ajouterArret(arretsBus.get(31), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(29), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(27), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(25), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(23), 2);
		lignesBus.get(1).ajouterArret(arretsBus.get(21), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(19), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(17), 4);
		lignesBus.get(1).ajouterArret(arretsBus.get(3), 1);
		lignesBus.get(1).ajouterArret(arretsBus.get(1), 1);
	}
	
	/**
	 * Affiche les lieux existant
	 */
	private static void afficherLieux()
	{
		for(int i=0; i<lieux.size(); i++)
			System.out.println(i+1 + ". " + lieux.get(i));
	}
	
	/**
	 * Cr�e un trajet
	 * @return le trajet cr��
	 */
	private static Trajet creerTrajet()
	{
		Trajet trajet = new Trajet();
		
		afficherLieux();
		System.out.println();
		
		Etape etape = creerEtape();
		trajet.ajouterEtape(etape);
		
		while(choixNouvelleEtape())
		{
			etape = creerEtape(etape.getLieuArrivee());
			trajet.ajouterEtape(etape);
		}
		
		return trajet;
	}
	
	/**
	 * Cr�e une �tape � partir de deux lieux choisis par 
	 * l'utilisateur.
	 * @return l'�tape construite.
	 */
	private static Etape creerEtape()
	{
		Lieu depart = lireChoixTrajet("Indiquez votre lieu de d�part : ");
		Lieu arrivee = lireChoixTrajet("Indiquez votre prochaine �tape : ");
		
		return new EtapeAPied(depart, arrivee);
	}
	
	/**
	 * Cr�e une �tape � partir du lieu de d�part fourni et 
	 * d'un lieu d'arriv�e choisi par l'utilisateur.
	 * @param depart le lieu de d�part de l'�tape � construire.
	 * @return l'�tape construite.
	 */
	private static Etape creerEtape(Lieu depart)
	{
		Lieu arrivee = lireChoixTrajet("Indiquez votre prochaine �tape : ");
	
		return new EtapeAPied(depart, arrivee);
	}
	
	/**
	 * Demande � l'utilisateur s'il souhaite ajouter une nouvelle �tape � son trajet.
	 * @return vrai ou faux selon que le choix de l'utilisateur.
	 */
	private static boolean choixNouvelleEtape()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Voulez vous ajouter une nouvelle �tape (utilisez o / n) ? ");
		String rep;
		boolean saisieValide;
		
	    do
	    {
	    	rep = sc.next();
	    	saisieValide = (rep.equals("o") || rep.equals("n")) ;
	    	if (!saisieValide)
	    		System.out.print("Saisie non valide, veuillez r�essayer : ");
	    } while (!saisieValide);
	    
	    return rep.equals("o");
	}
	
	/**
	 * Lit l'identifiant d'un lieu entr� au clavier et retourne le lieu correspondant.
	 * @param msg le message � afficher avant de lire l'entr�e standard.
	 * @return le lieu choisi.
	 */
	private static Lieu lireChoixTrajet(String msg)
	{
		Scanner sc = new Scanner(System.in);
	    Lieu lieu = null;
	    boolean saisieValide;
	    
	    System.out.print(msg);
	    
	    do
	    {
	    	try
		    {
		    	lieu = lieux.get(sc.nextInt()-1);
		    	saisieValide = true;
		    }
	    	catch (InputMismatchException e)
		    {
		    	saisieValide = false;
		    	sc = new Scanner(System.in);
		    	System.out.print("Saisie non valide, veuillez r�essayer : ");
		    }
		    catch (IndexOutOfBoundsException e)
		    {
		    	saisieValide = false;
		    	System.out.print("Saisie hors borne, veuillez r�essayer : ");
		    }
	    } while (!saisieValide);
	    
	    return lieu;
	}
}
