package graphe;

import java.util.ArrayList;

/**
 * Un lieu abstrait représenté par un nom et une liste d'étapes vers les lieux définis comme voisins.
 */
public class Lieu
{
	/**
	 * Le nom du lieu.
	 * @see Lieu#public Lieu(String nom)
	 */
	private String nom;
	
	/**
	 * La liste des étapes vers les lieux définis comme voisins.
	 * Il est possible d'ajouter une étape partant du lieu considéré.
	 * @see Lieu#ajouterVoisinAPied(Lieu voisin)
	 */
	private ArrayList<Etape> etapesVersVoisins;
	
	/**
	 * Construit un lieu identifié par son nom.
	 * @param nom le nom du lieu.
	 */
	public Lieu(String nom)
	{
		this.nom = nom;
		etapesVersVoisins = new ArrayList<Etape>();
	}
	
	/**
	 * Retourne le nom du lieu considéré.
	 * @return le nom du lieu.
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Ajoute une nouvelle étape partant du lieu et allant vers un voisin.
	 * @param etapeVersVoisin la nouvelle étape à ajouter.
	 */
	public void ajouterEtapeVersVoisin(Etape etapeVersVoisin)
	{
		etapesVersVoisins.add(etapeVersVoisin);
	}
	
	/**
	 * Essaie de mettre à jour le coût et la durée d'une étape désirée en utilisant
	 * les étapes vers les lieux voisins au lieu considéré comme base. La fonction
	 * retourne vrai ou faux selon qu'il ait été possible de trouver une étape compatible
	 * avec l'étape désirée.
	 * @param etape l'étape dont on désire mettre à jour les champs coût et durée.
	 * @return vrai si et seulement s'il a été possible de trouver une étape 
	 * 		   partant de ce lieu qui soit compatible avec l'étape voulue.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		for (Etape e : etapesVersVoisins) // On considère toutes les étapes disponibles
		{
			// On essaie de trouver une étape permettant de faire la màj
			if (e.mettreAJourCoutEtDuree(etape)) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Convertit un lieu en chaîne de caractères en retournant son nom.
	 * @return le nom du lieu.
	 */
	public String toString()
	{
		return nom;
	}
}
