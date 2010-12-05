package lieu;

import java.util.HashMap;

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
	 * La liste des voisins à pied associée avec la durée pour les atteindre.
	 * On peut ajouter un nouveau voisin à pied.
	 * @see Lieu#ajouterVoisinAPied(Lieu voisin)
	 */
	private HashMap<Lieu, Integer> voisinsAPied;
	
	/**
	 * Construit un lieu avec un nom.
	 * @param nom le nom du lieu
	 */
	public Lieu(String nom)
	{
		voisinsAPied = new HashMap<Lieu, Integer>();
		this.nom = nom;
	}
	
	/**
	 * Ajoute au lieu un nouveau voisin à pied.
	 * @param voisin le nouveau lieu voisin.
	 * @param temps le temps pour atteindre le lieu.
	 */
	public void ajouterVoisinAPied(Lieu voisin, int temps)
	{
		voisinsAPied.put(voisin, temps);
	}
	
	/**
	 * Rend les deux lieux passés en paramètre voisins l'un de l'autre à pieds.
	 * L'ordre des voisins n'a pas d'importance, la liaison de voisinage créée
	 * est symétrique.
	 * @param lieu1 le premier lieu du couple de voisin.
	 * @param lieu2 le deuxième lieu du couple de voisin.
	 * @param temps le temps séparant les deux liens.
	 */
	static public void rendreVoisinsAPied(Lieu lieu1, Lieu lieu2, int temps)
	{
		lieu1.ajouterVoisinAPied(lieu2, temps);
		lieu2.ajouterVoisinAPied(lieu1, temps);
	}
	
	/**
	 * Teste si un lieu est accessible à pied depuis le lieu courant.
	 * @param lieu le lieu à tester.
	 * @return true si le lieu est accessible à pied depuis le lieu courant, false sinon.
	 */
	public boolean estAccessibleAPied(Lieu lieu)
	{
		return voisinsAPied.containsKey(lieu);
	}
	
	public String toString()
	{
		return nom;
	}
}
