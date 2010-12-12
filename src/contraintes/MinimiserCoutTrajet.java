package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public class MinimiserCoutTrajet extends ContrainteElementaire{

	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> trajets = new ArrayList<Trajet>();
		double cout = -1;
		for( Trajet trajet : trajetsCandidats)
		{
			if (cout > trajet.getCout() || cout == -1)
			{
				cout = trajet.getCout();
				trajets.clear();
				trajets.add(trajet);
			}
			else if (cout == trajet.getCout())
			{
				trajets.add(trajet);
			}
		}
		return trajets;
	}

}
