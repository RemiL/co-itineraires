package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public class MinimiserCoutTrajet extends ContrainteElementaire
{
	public MinimiserCoutTrajet()
	{
		
	}
	
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		double cout = Double.MAX_VALUE;
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (cout > trajet.getCout())
			{
				cout = trajet.getCout();
				meilleursTrajets.clear();
				meilleursTrajets.add(trajet);
			}
			else if (cout == trajet.getCout())
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	public String toString()
	{
		return super.toString()+" : Minimiser le co�t global du trajet";
	}
}
