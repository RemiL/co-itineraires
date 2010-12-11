package ligne;

/**
 * Une ligne abstraite représentée par un nom.
 * @author Nicolas
 */
public abstract class Ligne
{
	private String nom;
	/*
	private Lieu departAller;
	private Lieu departRetour;
	private ArrayList<Horaire> horairesAller;
	private ArrayList<Horaire> horairesRetour;
	*/
	
	/**
	 * Construit une ligne avec un nom.
	 * @param nom le nom de la ligne.
	 */
	public Ligne(String nom)
	{
		this.nom = nom;
	}
	
	public String getNom()
	{
		return nom;
	}
}
