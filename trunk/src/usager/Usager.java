package usager;

import java.util.ArrayList;
import java.util.Observable;

import contraintes.Contrainte;

import graphe.Etape;
import graphe.Lieu;
import simulateurdeplacement.Simulable;
import simulateurdeplacement.SimulationImpossibleException;
import trajet.Trajet;

/**
 * Classe représentant un usager identifié par son nom et ses lieux de départ et d'arrivée désirés.
 * L'usager est capable de choisir entre plusieurs trajets selon une contrainte. Il implémente Simulable,
 * il est donc possible de simuler ses déplacements selon le trajet qu'il a choisi. Par ailleurs il
 * hérite de Observable par conséquent on peut lui attacher un certain nombre d'observateurs capables
 * de le surveiller et de traiter les évènements qu'il génère (en particulier le départ d'un lieu ou
 * l'arrivée à destination).
 */
public class Usager extends Observable implements Simulable
{
	/** Le nom de l'usager */
	private String nom;
	/** Le trajet suivi par l'usager */
	private Trajet trajet;
	/**	Les lieux de départ et d'arrivée de l'usager */
	private Lieu depart, arrivee;
	/** Le lieu de départ de la prochaine étape de l'usager */
	private Lieu prochainDepart;
	/** La liste des trajets possibles */
	private ArrayList<Trajet> trajetsCandidats;
	/** Le temps d'attente avant le prochain évènement */
	private int attenteAvantEvenement;
	/** Le temps attendu dans les transports */
	private int tempsAttenduTransport;
	/**	L'étape courante de l'usager */
	private Etape etapeActuelle;
	/** Indique si l'usager a atteint sa destination */
	private boolean estArrive;
	/** Indique si l'usager est en train d'attendre un transport */
	private boolean attenteTransport;
	/** La contrainte de choix de l'usager */
	private Contrainte contrainte;
	
	/**
	 * Crée un nouvel usager identifié par son nom qui souhaite se rendre d'un lieu de départ
	 * à un lieu d'arrivée. Il dispose pour ça d'une liste de trajets candidats à emprunter
	 * et d'une contrainte permettant de faire son choix dans la liste.
	 * @param nom le nom de l'usager.
	 * @param depart le lieu de depart de l'usager.
	 * @param arrivee le lieu d'arrivée de l'usager.
	 * @param trajetsCandidats les trajets envisagés.
	 * @param contrainte une contrainte permettant de choisir le trajet à emprunter.
	 * @param tempsAttente le temps avant le départ de l'usager.
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
	 * Retourne le nom de l'usager
	 * @return le nom de l'usager.
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Retourne le lieu de départ de l'usager.
	 * @return le lieu de départ de l'usager.
	 */
	public Lieu getLieuDepart()
	{
		return depart;
	}
	
	/**
	 * Retourne le lieu d'arrivée de l'usager.
	 * @return le lieu d'arrivée de l'usager.
	 */
	public Lieu getLieuArrivee()
	{
		return arrivee;
	}
	
	/**
	 * Retourne l'étape actuelle de l'usager.
	 * @return l'étape actuelle de l'usager.
	 */
	public Etape getEtapeActuelle()
	{
		return etapeActuelle;
	}
    
	/**
	 * Teste si l'usager est arrivé à destination.
	 * @return vrai uniquement si l'usager est arrivé à destination.
	 */
	public boolean estArrive()
	{
		return estArrive;
	}
	
	/**
	 * Retourne le temps que l'usager a passé à attendre les transports.
	 * @return le temps que l'usager a passé à attendre les transports.
	 */
	public int getTempsAttenduTransport()
	{
		return tempsAttenduTransport;
	}
    
	/**
	 * Choisit et retourne le meilleur trajet pour l'usager. La méthode peut retourner null
	 * si aucun trajet ne convient.
	 * @return le meilleur trajet choisi parmi les trajets candidats selon la contrainte
	 *         et les lieux de départ et d'arrivée.
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
	
	/**
	 * Permet de simuler le déplacement de l'usager le long de son trajet.
	 * Chaque appel à cette méthode correspond au passage d'une unité de temps.
	 * @param t le temps courant.
	 * @throws SimulationImpossibleException si la simulation est impossible, en
	 * 		   particulier si l'usager n'a pas de trajet qui lui est rattaché.
	 */
	public void simulerEvolution(int t) throws SimulationImpossibleException
	{
		if (trajet == null)
			throw new SimulationImpossibleException("Impossible de simuler si l'usager n'a pas de trajet rattaché.");
		
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
