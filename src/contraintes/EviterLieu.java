package contraintes;

import graphe.Lieu;

import java.util.ArrayList;

import trajet.Trajet;

public class EviterLieu extends ContrainteElementaire
{
	private Lieu lieuAEviter;
	
	public EviterLieu(Lieu lieuAEviter)
	{
		this.lieuAEviter = lieuAEviter;
	}
	
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (!trajet.passePar(lieuAEviter))
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	public String toString()
	{
		return super.toString()+" : Eviter le lieu \""+lieuAEviter+"\"";
	}
}
