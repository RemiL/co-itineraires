package ligne;

import graphe.Etape;

import java.util.ArrayList;

import moyenstransport.MoyenTransport;

/**
 * Une ligne abstraite repr�sent�e par un nom.
 * @author Nicolas
 */
public class Ligne
{
	private String nom;
	private MoyenTransport moyenTransport;
	private ArrayList<Etape> tron�ons;
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
		tron�ons = new ArrayList<Etape>();
	}
	/**
	 * methode getNom
	 * @return le nom de la ligne
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * methode getMoyenTransport
	 * @return moyenTransport le moyen de transport utilis�
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * methode ajouterTron�on
	 * @param tron�on le morceau de tron�on � ajouter
	 */
	public void ajouterTron�on(Etape tron�on)
	{
		tron�ons.add(tron�on);
	}

	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		double cout = 0;
		int duree = 0;
		boolean departTrouve = false;
		
		for (Etape e : tron�ons)
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
	/**
	 * methode toString qui indique le nom de la ligne
	 */
	public String toString()
	{
		return "Ligne \""+nom+"\"";
	}
}
