package usager;

import java.util.ArrayList;
import java.util.Observable;

import contraintes.Contrainte;

import graphe.Etape;
import graphe.Lieu;
import simulateurdeplacement.Simulable;
import trajet.Trajet;

/**
 * Class Usager 
 * @author Marie
 *
 */
public class Usager extends Observable implements Simulable
{
	private String nom;
	private Trajet trajet;
	private Lieu depart, arrivee, prochainDepart;
	private ArrayList<Trajet> trajetsCandidats;
	private int attenteAvantEvenement, tempsAttenduTransport;
	private Etape etapeActuelle;
	private boolean estArrive, attenteTransport;
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
		this.prochainDepart = this.depart = depart;
		this.arrivee = arrivee;
		this.trajetsCandidats = trajetsCandidats;
		this.etapeActuelle = null;
		this.estArrive = false;
		this.contrainte = contrainte;
		this.attenteAvantEvenement = tempsAttente;
		this.attenteTransport = false;
		this.tempsAttenduTransport = 0;
	}
	
	/**
	 * Methode getNom 
	 * @return le nom de l'usager
	 */
	public String getNom()
	{
		return nom;
	}
	
	public Lieu getLieuDepart()
	{
		return depart;
	}
	
	public Lieu getLieuArrivee()
	{
		return arrivee;
	}
	
	/**
	 * Méthode getPositionActuelle
	 * @return la position actuelle de l'usager
	 */
	public Etape getEtapeActuelle()
	{
		return etapeActuelle;
	}
    
	public boolean estArrive()
	{
		return estArrive;
	}
	
	public int getTempsAttenduTransport()
	{
		return tempsAttenduTransport;
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
				this.trajet = trajet.clone();
				return trajet;
			}
		}
		
		return null;
	}
	
	public void simulerEvolution(int t)
	{
		if (!estArrive)
		{
			if (attenteTransport)
			{
				tempsAttenduTransport++;
				
				if (etapeActuelle.getLigne().estPresentVehicule(prochainDepart))
				{
					attenteTransport = false;
					setChanged();
					notifyObservers(t);
				}
			}
			else if (attenteAvantEvenement == 0)
			{
				if (!trajet.estTermine())
				{
					etapeActuelle = trajet.getEtapeSuivante();
					attenteAvantEvenement = etapeActuelle.getDuree();
					prochainDepart = etapeActuelle.getLieuDepart();
					
					attenteTransport = etapeActuelle.getLigne() != null && !etapeActuelle.getLigne().estPresentVehicule(prochainDepart);
				}
				else
					estArrive = true;
				
				if (!attenteTransport)
				{
					setChanged();
					notifyObservers(t);
				}
			}
			
			if (!attenteTransport)
				attenteAvantEvenement--;
		}
	}
	
	public String toString()
	{
		return nom;
	}
}
