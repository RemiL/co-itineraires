package usager;

import java.util.ArrayList;

import contraintes.Contrainte;

import graphe.Lieu;
import trajet.Trajet;

public class Usager
{
	private String nom;
	private Trajet trajet;
	private Lieu depart, arrivee;
	private ArrayList<Trajet> trajetsCandidats;
	private int tempsAttente;
	private Lieu positionActuelle;
	private Contrainte contrainte;
	
	public Usager(String nom, Lieu depart, Lieu arrivee, ArrayList<Trajet> trajetsCandidats, Contrainte contrainte, int tempsAttente)
	{
		this.nom = nom;
		this.positionActuelle = this.depart = depart;
		this.arrivee = arrivee;
		this.trajetsCandidats = trajetsCandidats;
		this.tempsAttente = tempsAttente;
		this.contrainte = contrainte;
	}

	public String getNom()
	{
		return nom;
	}

	public Lieu getPositionActuelle()
	{
		return positionActuelle;
	}
	public Trajet choisirMeilleurTrajet(ArrayList<Trajet> trajets)
	{
		ArrayList<Trajet> trajetsValables = contrainte.evaluerTrajets(trajets);
		
		for (Trajet trajet : trajetsValables)
		{
			if(trajet.getLieuDepart() == depart && trajet.getLieuArrivee() == arrivee)
			{
				this.trajet = trajet;
				return trajet;
			}
		}
		
		return null;
	}
}
