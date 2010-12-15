package graphe;

import java.util.ArrayList;

/**
 * Un lieu abstrait repr�sent� par un nom et une liste d'�tapes vers les lieux d�finis comme voisins.
 */
public class Lieu
{
	/**
	 * Le nom du lieu.
	 * @see Lieu#public Lieu(String nom)
	 */
	private String nom;
	
	/**
	 * La liste des �tapes vers les lieux d�finis comme voisins.
	 * Il est possible d'ajouter une �tape partant du lieu consid�r�.
	 * @see Lieu#ajouterVoisinAPied(Lieu voisin)
	 */
	private ArrayList<Etape> etapesVersVoisins;
	
	/**
	 * Construit un lieu identifi� par son nom.
	 * @param nom le nom du lieu.
	 */
	public Lieu(String nom)
	{
		this.nom = nom;
		etapesVersVoisins = new ArrayList<Etape>();
	}
	
	/**
	 * Retourne le nom du lieu consid�r�.
	 * @return le nom du lieu.
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Ajoute une nouvelle �tape partant du lieu et allant vers un voisin.
	 * @param etapeVersVoisin la nouvelle �tape � ajouter.
	 */
	public void ajouterEtapeVersVoisin(Etape etapeVersVoisin)
	{
		etapesVersVoisins.add(etapeVersVoisin);
	}
	
	/**
	 * Essaie de mettre � jour le co�t et la dur�e d'une �tape d�sir�e en utilisant
	 * les �tapes vers les lieux voisins au lieu consid�r� comme base. La fonction
	 * retourne vrai ou faux selon qu'il ait �t� possible de trouver une �tape compatible
	 * avec l'�tape d�sir�e.
	 * @param etape l'�tape dont on d�sire mettre � jour les champs co�t et dur�e.
	 * @return vrai si et seulement s'il a �t� possible de trouver une �tape 
	 * 		   partant de ce lieu qui soit compatible avec l'�tape voulue.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		for (Etape e : etapesVersVoisins) // On consid�re toutes les �tapes disponibles
		{
			// On essaie de trouver une �tape permettant de faire la m�j
			if (e.mettreAJourCoutEtDuree(etape)) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Convertit un lieu en cha�ne de caract�res en retournant son nom.
	 * @return le nom du lieu.
	 */
	public String toString()
	{
		return nom;
	}
}
