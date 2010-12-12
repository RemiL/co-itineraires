package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public class MinimiserTempsTrajet extends ContrainteElementaire
{
	public MinimiserTempsTrajet()
	{
		
	}
	
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		int duree = Integer.MAX_VALUE;
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (duree > trajet.getDuree())
			{
				duree = trajet.getDuree();
				meilleursTrajets.clear();
				meilleursTrajets.add(trajet);
			}
			else if (duree == trajet.getDuree())
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	public String toString()
	{
		return super.toString()+" : Minimiser le temps total de trajet";
	}
}
