package graphe;

import java.util.ArrayList;

/**
 * Un lieu abstrait représenté par un nom et une liste de voisins.
 * @author Nicolas
 */
public class Lieu
{
	/**
	 * Le nom de la ligne.
	 * @see Lieu#public Lieu(String nom)
	 */
	private String nom;
	
	/**
	 * La liste des voisins à pied associée avec la durée pour les atteindre.
	 * On peut ajouter un nouveau voisin à pied.
	 * @see Lieu#ajouterVoisinAPied(Lieu voisin)
	 */
	private ArrayList<Etape> etapesVersVoisins;
	
	/**
	 * Construit un lieu avec un nom.
	 * @param nom le nom du lieu
	 */
	public Lieu(String nom)
	{
		this.nom = nom;
		etapesVersVoisins = new ArrayList<Etape>();
	}
	
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Ajoute au lieu un nouveau voisin à pied.
	 * @param voisin le nouveau lieu voisin.
	 * @param temps le temps pour atteindre le lieu.
	 */
	public void ajouterEtapeVersVoisin(Etape voisin)
	{
		etapesVersVoisins.add(voisin);
	}
	
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		for (Etape e : etapesVersVoisins)
		{
			if (e.mettreAJourCoutEtDuree(etape))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String toString()
	{
		return nom;
	}
}
