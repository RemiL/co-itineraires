package ligne;

import lieu.Lieu;
import lieu.LieuAccessibleEnTransport;

public abstract class Ligne
{
	protected String nom;
	
	public Ligne(String nom)
	{
		this.nom = nom;
	}
	
	public abstract double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee);
	public abstract boolean contientArrets(Lieu depart, Lieu arrivee);
}
