package ligne;

import graphe.Etape;
import graphe.Lieu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;

import simulateurdeplacement.Simulable;

import moyenstransport.MoyenTransport;

/**
 * Une ligne abstraite représentée par un nom.
 * @author Nicolas
 */
public class Ligne extends Observable implements Simulable
{
	private String nom;
	private MoyenTransport moyenTransport;
	private ArrayList<Etape> tronçons;
	private int[] horaires;
	private LinkedList<Integer> etapesVehicules;
	private LinkedList<Integer> dureeRestanteEtapes;
	private Lieu derniereStationQuittee;
	private ArrayList<Lieu> stationsVehiculePresent;
	
	/**
	 * Construit une ligne avec un nom.
	 * @param nom le nom de la ligne.
	 */
	public Ligne(String nom, MoyenTransport moyenTransport, int[] horaires)
	{
		this.nom = nom;
		this.moyenTransport = moyenTransport;
		this.tronçons = new ArrayList<Etape>();
		Arrays.sort(horaires);
		this.horaires = horaires;
		this.etapesVehicules = new LinkedList<Integer>();
		this.dureeRestanteEtapes = new LinkedList<Integer>();
		this.derniereStationQuittee = null;
		this.stationsVehiculePresent = new ArrayList<Lieu>();
	}
	/**
	 * methode getNom
	 * @return le nom de la ligne
	 */
	public String getNom()
	{
		return nom;
	}
	
	public String getNomVehicule()
	{
		return moyenTransport.getNomVehicule();
	}
	
	/**
	 * methode getMoyenTransport
	 * @return moyenTransport le moyen de transport utilisé
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * methode ajouterTronçon
	 * @param tronçon le morceau de tronçon à ajouter
	 */
	public void ajouterTronçon(Etape tronçon)
	{
		tronçons.add(tronçon);
	}
	
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		double cout = 0;
		int duree = 0;
		boolean departTrouve = false;
		
		for (Etape e : tronçons)
		{
			if (!departTrouve)
			{
				if (e.getLieuDepart() == etape.getLieuDepart())
				{
					departTrouve = true;
					cout = e.getCout();
					duree = e.getDuree();
				}
			}
			else
			{
				cout += e.getCout();
				duree += e.getDuree();
				
				if (e.getLieuArrivee() == etape.getLieuArrivee())
				{
					etape.setCout(cout);
					etape.setDuree(duree);
					
					return true;
				}
			}
		}
		
		return false;
	}

	public Lieu getDerniereStationQuittee()
	{
		return derniereStationQuittee;
	}
	
	/**
	 * methode toString qui indique le nom de la ligne
	 */
	public String toString()
	{
		return "Ligne \""+nom+"\"";
	}
	
	public void simulerEvolution(int t)
	{
		boolean aSupprimer = false;
		
		stationsVehiculePresent.clear();
		
		if (Arrays.binarySearch(horaires, t) >= 0)
		{
			etapesVehicules.add(0);
			dureeRestanteEtapes.add(tronçons.get(0).getDuree());
			derniereStationQuittee = tronçons.get(0).getLieuDepart();
			stationsVehiculePresent.add(derniereStationQuittee);
			
			setChanged();
			notifyObservers(t);
		}
		
		for (int i=0; i<dureeRestanteEtapes.size(); i++)
		{			
			if (dureeRestanteEtapes.get(i) == 0)
			{
				if (etapesVehicules.get(i) == (tronçons.size()-1))
					aSupprimer = true;
				else
				{
					etapesVehicules.set(i, etapesVehicules.get(i)+1);
					dureeRestanteEtapes.set(i, tronçons.get(etapesVehicules.get(i)).getDuree());
					derniereStationQuittee = tronçons.get(etapesVehicules.get(i)).getLieuDepart();
					stationsVehiculePresent.add(tronçons.get(etapesVehicules.get(i)).getLieuDepart());
					
					setChanged();
					notifyObservers(t);
				}
			}
			
			dureeRestanteEtapes.set(i, dureeRestanteEtapes.get(i)-1);
		}
		
		if (aSupprimer)
		{
			etapesVehicules.remove();
			dureeRestanteEtapes.remove();
		}
	}
	
	public boolean estPresentVehicule(Lieu station)
	{	
		return stationsVehiculePresent.contains(station);
	}
}
