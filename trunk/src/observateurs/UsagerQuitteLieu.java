package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

public class UsagerQuitteLieu implements Observer
{
	private Lieu lieu;
	
	public UsagerQuitteLieu()
	{
		lieu = null;
	}
	
	public UsagerQuitteLieu(Lieu lieu)
	{
		this.lieu = lieu;
	}
	
	public void update(Observable obs, Object arg)
	{
		try
		{
			Usager usager = (Usager)obs;
			
			if ((lieu == null || lieu == usager.getEtapeActuelle().getLieuDepart()) && !usager.estArrive())
				System.out.println("Evènement à t = "+arg+" : l'usager "+usager+" vient de quitter le lieu "+usager.getEtapeActuelle().getLieuDepart()+".");
		}
		catch (ClassCastException e) { /* on ignore l'événement */ }
	}
}