package ligne;

import graphe.EtapeEnBus;

import java.util.ArrayList;

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
	private ArrayList<EtapeEnBus> tronçons;
	
	/**
	 * Construit une ligne de bus vide avec un nom et un tarif.
	 * @param nom le nom de la ligne.
	 * @param tarif le tarif d'un voyage.
	 */
	public LigneBus(String nom)
	{
		super(nom);
		tronçons = new ArrayList<EtapeEnBus>();
	}
	
	/**
	 * Ajoute un arret de bus à la ligne. L'ajout ce fait en fin de liste.
	 * @param arret l'arret de bus à ajouter.
	 * @param temps le temps pour aller de l'arret précédent à celui-ci.
	 */
	public void ajouterTronçon(EtapeEnBus arret)
	{
		tronçons.add(arret);
	}

	/**
	 * Vérifie que deux lieux sont reliés par la ligne.
	 * @param depart le lieu de départ.
	 * @param arrivee le lieu d'arrivée.
	 * @return true si les deux lieux sont reliés par la ligne, false sinon.
	 *
	public boolean contientArrets(Lieu depart, Lieu arrivee)
	{
		return (arrets.contains(depart) && arrets.contains(arrivee));
	}
	
	/**
	 * Vérifie qu'un lieux appartient à la ligne.
	 * @param le lieu.
	 * @return true si le lieu appartient à la ligne, false sinon.
	 *
	public boolean contientArret(Lieu l)
	{
		return arrets.contains(l);
	}
	
	/**
	 * Calcule le coût du trajet entre deux arrets sur la ligne.
	 * @param depart le départ du trajet.
	 * @param arrivee l'arrivée du trajet.
	 * @return le coût du trajet entre depart et arrivee.
	 *
	public double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee) 
	{
		return tarif;
	}
	*/
}
