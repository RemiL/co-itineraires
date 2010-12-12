package contraintes;

import java.util.ArrayList;

import moyenstransport.MoyenTransport;

import trajet.Trajet;

public class EviterMoyenTransport extends ContrainteElementaire
{
	private MoyenTransport moyenTransportAEviter;
	
	public EviterMoyenTransport(MoyenTransport moyenTransportAEviter)
	{
		this.moyenTransportAEviter = moyenTransportAEviter;
	}
	
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (!trajet.utilise(moyenTransportAEviter))
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	public String toString()
	{
		return super.toString()+" : Eviter le moyen de transport \""+moyenTransportAEviter+"\"";
	}
}
