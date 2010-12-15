package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import ligne.Ligne;

/**
 * Classe implémentant un observateur capable de surveiller des lignes 
 * et détectant le départ d'un véhicule d'une station avec possibilité
 * de filter les évènéments selon une ligne et/ou une station.
 */
public class VehiculeQuitteStation implements Observer
{
	/** Filtre selon une ligne */
	private Ligne ligne;
	/** Filtre selon une station */
	private Lieu station;
	
	/**
	 * Crée un observeur capable de surveiller des lignes 
	 * et détectant le départ d'un véhicule d'une station
	 * sans aucun filtrage des évènements reçus.
	 */
	public VehiculeQuitteStation()
	{
		this(null, null);
	}
	
	/**
	 * Crée un observeur capable de surveiller des lignes 
	 * et détectant le départ d'un véhicule d'une station
	 * en filtrant les événements relatifs à une ligne 
	 * particulière.
	 * @param ligne la ligne à laquelle on s'intéresse.
	 */
	public VehiculeQuitteStation(Ligne ligne)
	{
		this(ligne, null);
	}
	
	/**
	 * Crée un observeur capable de surveiller des lignes 
	 * et détectant le départ d'un véhicule d'une station
	 * en filtrant les événements relatifs à une station 
	 * particulière.
	 * @param station la station à laquelle on s'intéresse.
	 */
	public VehiculeQuitteStation(Lieu station)
	{
		this(null, station);
	}
	
	/**
	 * Crée un observeur capable de surveiller des lignes 
	 * et détectant le départ d'un véhicule d'une station
	 * en filtrant les événements relatifs à une ligne et
	 * une station particulière.
	 * @param ligne la ligne à laquelle on s'intéresse.
	 * @param station la station à laquelle on s'intéresse.
	 */
	public VehiculeQuitteStation(Ligne ligne, Lieu station)
	{
		this.ligne = ligne;
		this.station = station;
	}
	
	/**
	 * Méthode appellée automatiquement lorsqu'un objet surveillé est modifié.
	 * Affiche un message si un véhicule a quitté une station (et que l'évèvement
	 * n'a pas été filtré par les conditions sur la ligne et la station).
	 * @param obs l'observable pour lequel un évènement a été détecté par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument à l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend une ligne et un entier.
			Ligne l = (Ligne)obs;
			Integer t = (Integer)arg;
			
			if ((station == null || station == l.getDerniereStationQuittee()) && (ligne == null || ligne == l))
				System.out.println("Evènement à t = "+t+" : un "+l.getNomVehicule()+" vient de quitter la station "+l.getDerniereStationQuittee()+" ("+l+").");
		}
		catch (ClassCastException e) { /* on ignore l'événement */ }
	}
}
