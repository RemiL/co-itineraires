package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public class ContrainteComposee implements Contrainte
{
	private ArrayList<Contrainte> contraintes;
	
	public ContrainteComposee()
	{
		contraintes = new ArrayList<Contrainte>();
	}
	
	public void ajouterContrainte(Contrainte contrainte)
	{
		contraintes.add(contrainte);
	}

	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats)
	{
		ArrayList<Trajet> trajets = new ArrayList<Trajet>(trajetsCandidats);
		
		for (Contrainte c : contraintes)
		{
			trajets = c.evaluerTrajets(trajets);
			if (trajets.size() == 1)
				break;
		}
		
		return trajets;
	}
}
