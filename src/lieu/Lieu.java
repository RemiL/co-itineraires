package lieu;

import java.util.ArrayList;

public abstract class Lieu
{
	protected String nom;
	private ArrayList<Lieu> voisinsAPied;
	
	public Lieu(String nom)
	{
		voisinsAPied = new ArrayList<Lieu>();
		this.nom = nom;
	}
	
	public void ajouterVoisinAPied(Lieu voisin)
	{
		voisinsAPied.add(voisin);
	}
	
	public boolean estAccessibleAPied(Lieu lieu)
	{
		return voisinsAPied.contains(lieu);
	}
}
