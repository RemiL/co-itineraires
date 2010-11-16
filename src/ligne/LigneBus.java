package ligne;

import java.util.ArrayList;
import java.util.Hashtable;

import lieu.ArretBus;
import lieu.Lieu;
import lieu.LieuAccessibleEnTransport;

/**
 * Une ligne de bus est un ensemble d'arrêts de bus dont le coût du trajet est fixe.
 * @author Nicolas
 */
public class LigneBus extends Ligne
{
	/**
	 * La liste des arrêts de bus de la ligne. On peut en ajouter de nouveaux.
	 * @see LigneBus#ajouterArret(ArretBus arret, int temps)
	 */
	private ArrayList<ArretBus> arrets;
	
	/**
	 * Le temps de trajet entre chaque arrêts de la ligne. 
	 * La valeur correspond au temps du trajet entre l'arrêt précédant l'arrêt clé et la clé.
	 * @see LigneBus#ajouterArret(ArretBus arret, int temps)
	 */
	private Hashtable<ArretBus, Integer> tempsTrajet;
	
	/**
	 * La liste contenant la position des bus de la ligne.
	 */
	private ArrayList<ArretBus> positionsBus;
	
	/**
	 * Le tarif d'un trajet sur la ligne.
	 * @see LigneBus#calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee) 
	 */
	private double tarif;
	
	/**
	 * Construit une ligne de bus vide avec un nom et un tarif.
	 * @param nom le nom de la ligne.
	 * @param tarif le tarif d'un voyage.
	 */
	public LigneBus(String nom, double tarif)
	{
		super(nom);
		this.tarif = tarif;
		arrets = new ArrayList<ArretBus>();
		tempsTrajet = new Hashtable<ArretBus, Integer>();
		positionsBus = new ArrayList<ArretBus>();
	}
	
	/**
	 * Ajoute un arret de bus à la ligne. L'ajout ce fait en fin de liste.
	 * @param arret l'arret de bus à ajouter.
	 * @param temps le temps pour aller de l'arret précédent à celui-ci.
	 */
	public void ajouterArret(ArretBus arret, int temps)
	{
		arrets.add(arret);
		
		tempsTrajet.put(arret, temps);
	}

	/**
	 * Vérifie que deux lieux sont reliés par la ligne.
	 * @param depart le lieu de départ.
	 * @param arrivee le lieu d'arrivée.
	 * @return true si les deux lieux sont reliés par la ligne, false sinon.
	 */
	public boolean contientArrets(Lieu depart, Lieu arrivee)
	{
		return (arrets.contains(depart) && arrets.contains(arrivee));
	}
	
	/**
	 * Calcule le coût du trajet entre deux arrets sur la ligne.
	 * @param depart le départ du trajet.
	 * @param arrivee l'arrivée du trajet.
	 * @return le coût du trajet entre depart et arrivee.
	 */
	public double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee) 
	{
		return tarif;
	}
}
