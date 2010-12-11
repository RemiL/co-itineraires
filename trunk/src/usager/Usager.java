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
	
	public Usager(String nom, Lieu depart, Lieu arrivee, ArrayList<Trajet> trajetsCandidats, Contrainte contrainte, int tempsAttente)
	{
		this.nom = nom;
		this.positionActuelle = this.depart = depart;
		this.arrivee = arrivee;
		this.trajetsCandidats = trajetsCandidats;
		this.tempsAttente = tempsAttente;
	}

	public String getNom()
	{
		return nom;
	}

	public Lieu getPositionActuelle()
	{
		return positionActuelle;
	}
}
