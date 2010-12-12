package ligne;

import graphe.Etape;

import java.util.ArrayList;

import moyenstransport.MoyenTransport;

/**
 * Une ligne abstraite représentée par un nom.
 * @author Nicolas
 */
public class Ligne
{
	private String nom;
	private MoyenTransport moyenTransport;
	private ArrayList<Etape> tronçons;
	/*
	private Lieu departAller;
	private Lieu departRetour;
	private ArrayList<Horaire> horairesAller;
	private ArrayList<Horaire> horairesRetour;
	*/
	
	/**
	 * Construit une ligne avec un nom.
	 * @param nom le nom de la ligne.
	 */
	public Ligne(String nom, MoyenTransport moyenTransport)
	{
		this.nom = nom;
		this.moyenTransport = moyenTransport;
		tronçons = new ArrayList<Etape>();
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
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
				departTrouve = (e.getLieuDepart() == etape.getLieuDepart());
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
	
	public String toString()
	{
		return "Ligne \""+nom+"\"";
	}
}
