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
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>(trajetsCandidats);
		
		for (Contrainte c : contraintes)
		{
			meilleursTrajets = c.evaluerTrajets(meilleursTrajets);
			if (meilleursTrajets.size() == 1)
				break;
		}
		
		return meilleursTrajets;
	}
}
