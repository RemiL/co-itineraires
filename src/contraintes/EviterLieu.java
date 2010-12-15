package contraintes;

import graphe.Lieu;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe implémentant une contrainte élémentaire visant à sélectionner uniquement
 * les trajets ne passant pas par un certain lieu.
 */
public class EviterLieu extends ContrainteElementaire
{
	/** Le lieu à éviter */
	private Lieu lieuAEviter;
	
	/**
	 * Construit une contrainte visant à éviter le lieu fourni en paramètre.
	 * @param lieuAEviter le lieu à éviter.
	 */
	public EviterLieu(Lieu lieuAEviter)
	{
		this.lieuAEviter = lieuAEviter;
	}
	
	/**
	 * Permet d'évaluer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (éventuellement vide) des meilleurs trajets au sens de la contrainte.
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
	 * Retourne une représentation textuelle de la contrainte.
	 * @return une chaine de caractères représentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Eviter le lieu \""+lieuAEviter+"\"";
	}
}
