package usager;

import java.util.ArrayList;

import contraintes.Contrainte;

import graphe.Lieu;
import trajet.Trajet;

/**
 * Class Usager 
 * @author Marie
 *
 */
public class Usager
{
	private String nom;
	private Trajet trajet;
	private Lieu depart, arrivee;
	private ArrayList<Trajet> trajetsCandidats;
	private int tempsAttente;
	private Lieu positionActuelle;
	private Contrainte contrainte;
	
	/**
	 * constructeur Usager
	 * @param nom le nom de l'usager
	 * @param depart le lieu de depart
	 * @param arrivee le lieu d'arrivee
	 * @param trajetsCandidats Arraylist des trajets candidats 
	 * @param contrainte contrainte de type Contrainte pose une condition sur le choix du trajet 
	 * @param tempsAttente temps d'attente que l'usager doit attendre avant de pouvoir partir
	 */
	public Usager(String nom, Lieu depart, Lieu arrivee, ArrayList<Trajet> trajetsCandidats, Contrainte contrainte, int tempsAttente)
	{
		this.nom = nom;
		this.positionActuelle = this.depart = depart;
		this.arrivee = arrivee;
		this.trajetsCandidats = trajetsCandidats;
		this.tempsAttente = tempsAttente;
		this.contrainte = contrainte;
	}

	/**
	 * Methode getNom 
	 * @return le nom de l'usager
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Méthode getPositionActuelle
	 * @return la position actuelle de l'usager
	 */
	public Lieu getPositionActuelle()
	{
		return positionActuelle;
	}
	
	/**
	 * Méthode choisirMeilleurTrajet
	 * @return le meilleur trajet a choisir parmi les trajets candidats selon une contrainte
	 */
	public Trajet choisirMeilleurTrajet()
	{
		ArrayList<Trajet> trajetsValables = contrainte.evaluerTrajets(trajetsCandidats);
		
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
