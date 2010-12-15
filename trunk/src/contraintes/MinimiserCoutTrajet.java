package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe implémentant une contrainte élémentaire visant à sélectionner uniquement
 * les trajets ayant le coût global minimum.
 */
public class MinimiserCoutTrajet extends ContrainteElementaire
{
	/**
	 * Construit une contrainte visant à minimiser le coût global des trajets.
	 */
	public MinimiserCoutTrajet()
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
		double cout = Double.MAX_VALUE;
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (cout > trajet.getCout()) // On a un trajet moins couteux
			{ 
				cout = trajet.getCout();
				meilleursTrajets.clear(); // On vide l'ancienne liste des trajets sélectionnés
				meilleursTrajets.add(trajet);
			}
			else if (cout == trajet.getCout())
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
		return super.toString()+" : Minimiser le coût global du trajet";
	}
}
