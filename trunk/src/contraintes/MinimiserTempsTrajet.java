package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public class MinimiserTempsTrajet extends ContrainteElementaire{

	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> trajets = new ArrayList<Trajet>();
		int duree = -1;
		for( Trajet trajet : trajetsCandidats)
		{
			if (duree > trajet.getDuree() || duree == -1)
			{
				duree = trajet.getDuree();
				trajets.clear();
				trajets.add(trajet);
			}
			else if (duree == trajet.getDuree())
			{
				trajets.add(trajet);
			}
		}
		return trajets;
	}

}
