package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import ligne.Ligne;

public class VehiculeQuitteStation implements Observer
{
	private Ligne ligne;
	private Lieu station;
	
	public VehiculeQuitteStation()
	{
		this(null, null);
	}
	
	public VehiculeQuitteStation(Ligne ligne)
	{
		this(ligne, null);
	}
	
	public VehiculeQuitteStation(Lieu station)
	{
		this(null, station);
	}
	
	public VehiculeQuitteStation(Ligne ligne, Lieu station)
	{
		this.ligne = ligne;
		this.station = station;
	}
	
	public void update(Observable obs, Object arg)
	{
		try
		{
			Ligne l = (Ligne)obs;
			
			if ((station == null || station == l.getDerniereStationQuittee()) && (ligne == null || ligne == l))
				System.out.println("Evènement à t = "+arg+" : un "+l.getNomVehicule()+" vient de quitter la station "+l.getDerniereStationQuittee()+" ("+l+").");
		}
		catch (ClassCastException e) { /* on ignore l'événement */ }
	}
}
