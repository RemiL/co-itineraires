package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe implémentant une contrainte élémentaire visant à sélectionner uniquement
 * les trajets ayant la durée globale minimale.
 */
public class MinimiserTempsTrajet extends ContrainteElementaire
{
	/**
	 * Construit une contrainte visant à minimiser la durée globale des trajets.
	 */
	public MinimiserTempsTrajet()
	{
		
	}
	
	/**
	 * Permet d'évaluer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste des meilleurs trajets au sens de la contrainte.
	 */
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		int duree = Integer.MAX_VALUE;
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (duree > trajet.getDuree()) // On a un trajet moins long
			{
				duree = trajet.getDuree();
				meilleursTrajets.clear(); // On vide l'ancienne liste des trajets sélectionnés
				meilleursTrajets.add(trajet);
			}
			else if (duree == trajet.getDuree())
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
		return super.toString()+" : Minimiser le temps total de trajet";
	}
}
