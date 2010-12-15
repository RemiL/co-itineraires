package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe impl�mentant une contrainte �l�mentaire visant � s�lectionner uniquement
 * les trajets ayant la dur�e globale minimale.
 */
public class MinimiserTempsTrajet extends ContrainteElementaire
{
	/**
	 * Construit une contrainte visant � minimiser la dur�e globale des trajets.
	 */
	public MinimiserTempsTrajet()
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
		int duree = Integer.MAX_VALUE;
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (duree > trajet.getDuree()) // On a un trajet moins long
			{
				duree = trajet.getDuree();
				meilleursTrajets.clear(); // On vide l'ancienne liste des trajets s�lectionn�s
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
	 * Retourne une repr�sentation textuelle de la contrainte.
	 * @return une chaine de caract�res repr�sentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Minimiser le temps total de trajet";
	}
}
