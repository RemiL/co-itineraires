package lieu;

import java.util.ArrayList;

/**
 * Un lieu abstrait représenté par un nom et une liste de voisins à pied.
 * @author Nicolas
 */
public abstract class Lieu
{
	/**
	 * Le nom de la ligne.
	 * @see Lieu#public Lieu(String nom)
	 */
	protected String nom;
	
	/**
	 * La liste des voisins à pied.
	 * On peut ajouter un nouveau voisin à pied.
	 * @see Lieu#ajouterVoisinAPied(Lieu voisin)
	 */
	private ArrayList<Lieu> voisinsAPied;
	
	/**
	 * Construit un lieu avec un nom.
	 * @param nom le nom du lieu
	 */
	public Lieu(String nom)
	{
		voisinsAPied = new ArrayList<Lieu>();
		this.nom = nom;
	}
	
	/**
	 * Ajoute au lieu un nouveau voisin à pied.
	 * @param voisin le nouveau lieu voisin.
	 */
	public void ajouterVoisinAPied(Lieu voisin)
	{
		voisinsAPied.add(voisin);
	}
	
	/**
	 * Teste si un lieu est accessible à pied depuis le lieu courant.
	 * @param lieu le lieu à tester.
	 * @return true si le lieu est accessible à pied depuis le lieu courant, false sinon.
	 */
	public boolean estAccessibleAPied(Lieu lieu)
	{
		return voisinsAPied.contains(lieu);
	}
}
