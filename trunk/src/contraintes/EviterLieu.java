package contraintes;

import graphe.Lieu;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe impl�mentant une contrainte �l�mentaire visant � s�lectionner uniquement
 * les trajets ne passant pas par un certain lieu.
 */
public class EviterLieu extends ContrainteElementaire
{
	/** Le lieu � �viter */
	private Lieu lieuAEviter;
	
	/**
	 * Construit une contrainte visant � �viter le lieu fourni en param�tre.
	 * @param lieuAEviter le lieu � �viter.
	 */
	public EviterLieu(Lieu lieuAEviter)
	{
		this.lieuAEviter = lieuAEviter;
	}
	
	/**
	 * Permet d'�valuer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (�ventuellement vide) des meilleurs trajets au sens de la contrainte.
	 */
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (!trajet.passePar(lieuAEviter))
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	/**
	 * Retourne une repr�sentation textuelle de la contrainte.
	 * @return une chaine de caract�res repr�sentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Eviter le lieu \""+lieuAEviter+"\"";
	}
}
