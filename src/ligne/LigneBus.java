package ligne;

import graphe.EtapeEnBus;

import java.util.ArrayList;

/**
 * Une ligne de bus est un ensemble d'arr�ts de bus dont le co�t du trajet est fixe.
 * @author Nicolas
 */
public class LigneBus extends Ligne
{
	/**
	 * La liste des arr�ts de bus de la ligne. On peut en ajouter de nouveaux.
	 * @see LigneBus#ajouterArret(ArretBus arret, int temps)
	 */
	private ArrayList<EtapeEnBus> tron�ons;
	
	/**
	 * Construit une ligne de bus vide avec un nom et un tarif.
	 * @param nom le nom de la ligne.
	 * @param tarif le tarif d'un voyage.
	 */
	public LigneBus(String nom)
	{
		super(nom);
		tron�ons = new ArrayList<EtapeEnBus>();
	}
	
	/**
	 * Ajoute un arret de bus � la ligne. L'ajout ce fait en fin de liste.
	 * @param arret l'arret de bus � ajouter.
	 * @param temps le temps pour aller de l'arret pr�c�dent � celui-ci.
	 */
	public void ajouterTron�on(EtapeEnBus arret)
	{
		tron�ons.add(arret);
	}

	/**
	 * V�rifie que deux lieux sont reli�s par la ligne.
	 * @param depart le lieu de d�part.
	 * @param arrivee le lieu d'arriv�e.
	 * @return true si les deux lieux sont reli�s par la ligne, false sinon.
	 *
	public boolean contientArrets(Lieu depart, Lieu arrivee)
	{
		return (arrets.contains(depart) && arrets.contains(arrivee));
	}
	
	/**
	 * V�rifie qu'un lieux appartient � la ligne.
	 * @param le lieu.
	 * @return true si le lieu appartient � la ligne, false sinon.
	 *
	public boolean contientArret(Lieu l)
	{
		return arrets.contains(l);
	}
	
	/**
	 * Calcule le co�t du trajet entre deux arrets sur la ligne.
	 * @param depart le d�part du trajet.
	 * @param arrivee l'arriv�e du trajet.
	 * @return le co�t du trajet entre depart et arrivee.
	 *
	public double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee) 
	{
		return tarif;
	}
	*/
}
