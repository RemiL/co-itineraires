package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe impl�mentant une contrainte �l�mentaire visant � s�lectionner uniquement
 * les trajets ayant le co�t global minimum.
 */
public class MinimiserCoutTrajet extends ContrainteElementaire
{
	/**
	 * Construit une contrainte visant � minimiser le co�t global des trajets.
	 */
	public MinimiserCoutTrajet()
	{
		
	}
	
	/**
	 * Permet d'�valuer la liste de trajets fournie au sens de la contrainte.
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
				meilleursTrajets.clear(); // On vide l'ancienne liste des trajets s�lectionn�s
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
	 * Retourne une repr�sentation textuelle de la contrainte.
	 * @return une chaine de caract�res repr�sentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Minimiser le co�t global du trajet";
	}
}
