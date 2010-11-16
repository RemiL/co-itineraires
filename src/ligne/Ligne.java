package ligne;

import lieu.Lieu;
import lieu.LieuAccessibleEnTransport;

/**
 * Une ligne abstraite représentée par un nom.
 * @author Nicolas
 */
public abstract class Ligne
{
	protected String nom;
	
	/**
	 * Construit une ligne avacun nom.
	 * @param nom le nom de la ligne.
	 */
	public Ligne(String nom)
	{
		this.nom = nom;
	}
	
	public abstract double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee);
	public abstract boolean contientArrets(Lieu depart, Lieu arrivee);
}
