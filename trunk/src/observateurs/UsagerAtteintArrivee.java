package observateurs;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

public class UsagerAtteintArrivee implements Observer
{
	public UsagerAtteintArrivee()
	{
		
	}
	
	public void update(Observable obs, Object arg)
	{
		try
		{
			Usager usager = (Usager)obs;
			
			if (usager.estArrive())
				System.out.println("Ev�nement � t = "+arg+" : l'usager "+usager+" vient d'atteindre sa destination ("+usager.getLieuArrivee()+") (temps pass� � attendre les transports = "+usager.getTempsAttenduTransport()+").");
		}
		catch (ClassCastException e) { /* on ignore l'�v�nement */ }
	}
}