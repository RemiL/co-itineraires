package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import ligne.Ligne;

/**
 * Classe impl�mentant un observateur capable de surveiller des lignes 
 * et d�tectant le d�part d'un v�hicule d'une station avec possibilit�
 * de filter les �v�n�ments selon une ligne et/ou une station.
 */
public class VehiculeQuitteStation implements Observer
{
	/** Filtre selon une ligne */
	private Ligne ligne;
	/** Filtre selon une station */
	private Lieu station;
	
	/**
	 * Cr�e un observeur capable de surveiller des lignes 
	 * et d�tectant le d�part d'un v�hicule d'une station
	 * sans aucun filtrage des �v�nements re�us.
	 */
	public VehiculeQuitteStation()
	{
		this(null, null);
	}
	
	/**
	 * Cr�e un observeur capable de surveiller des lignes 
	 * et d�tectant le d�part d'un v�hicule d'une station
	 * en filtrant les �v�nements relatifs � une ligne 
	 * particuli�re.
	 * @param ligne la ligne � laquelle on s'int�resse.
	 */
	public VehiculeQuitteStation(Ligne ligne)
	{
		this(ligne, null);
	}
	
	/**
	 * Cr�e un observeur capable de surveiller des lignes 
	 * et d�tectant le d�part d'un v�hicule d'une station
	 * en filtrant les �v�nements relatifs � une station 
	 * particuli�re.
	 * @param station la station � laquelle on s'int�resse.
	 */
	public VehiculeQuitteStation(Lieu station)
	{
		this(null, station);
	}
	
	/**
	 * Cr�e un observeur capable de surveiller des lignes 
	 * et d�tectant le d�part d'un v�hicule d'une station
	 * en filtrant les �v�nements relatifs � une ligne et
	 * une station particuli�re.
	 * @param ligne la ligne � laquelle on s'int�resse.
	 * @param station la station � laquelle on s'int�resse.
	 */
	public VehiculeQuitteStation(Ligne ligne, Lieu station)
	{
		this.ligne = ligne;
		this.station = station;
	}
	
	/**
	 * M�thode appell�e automatiquement lorsqu'un objet surveill� est modifi�.
	 * Affiche un message si un v�hicule a quitt� une station (et que l'�v�vement
	 * n'a pas �t� filtr� par les conditions sur la ligne et la station).
	 * @param obs l'observable pour lequel un �v�nement a �t� d�tect� par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument � l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend une ligne et un entier.
			Ligne l = (Ligne)obs;
			Integer t = (Integer)arg;
			
			if ((station == null || station == l.getDerniereStationQuittee()) && (ligne == null || ligne == l))
				System.out.println("Ev�nement � t = "+t+" : un "+l.getNomVehicule()+" vient de quitter la station "+l.getDerniereStationQuittee()+" ("+l+").");
		}
		catch (ClassCastException e) { /* on ignore l'�v�nement */ }
	}
}
