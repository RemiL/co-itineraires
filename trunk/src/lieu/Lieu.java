package lieu;

import java.util.ArrayList;

/**
 * Un lieu abstrait repr�sent� par un nom et une liste de voisins � pied.
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
	 * La liste des voisins � pied.
	 * On peut ajouter un nouveau voisin � pied.
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
	 * Ajoute au lieu un nouveau voisin � pied.
	 * @param voisin le nouveau lieu voisin.
	 */
	public void ajouterVoisinAPied(Lieu voisin)
	{
		voisinsAPied.add(voisin);
	}
	
	/**
	 * Teste si un lieu est accessible � pied depuis le lieu courant.
	 * @param lieu le lieu � tester.
	 * @return true si le lieu est accessible � pied depuis le lieu courant, false sinon.
	 */
	public boolean estAccessibleAPied(Lieu lieu)
	{
		return voisinsAPied.contains(lieu);
	}
}
