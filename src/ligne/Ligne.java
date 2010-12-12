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
	
	public String getNom()
	{
		return nom;
	}
	
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	public void ajouterTron�on(Etape tron�on)
	{
		tron�ons.add(tron�on);
	}
}
