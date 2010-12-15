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
 * Classe repr�sentant un usager identifi� par son nom et ses lieux de d�part et d'arriv�e d�sir�s.
 * L'usager est capable de choisir entre plusieurs trajets selon une contrainte. Il impl�mente Simulable,
 * il est donc possible de simuler ses d�placements selon le trajet qu'il a choisi. Par ailleurs il
 * h�rite de Observable par cons�quent on peut lui attacher un certain nombre d'observateurs capables
 * de le surveiller et de traiter les �v�nements qu'il g�n�re (en particulier le d�part d'un lieu ou
 * l'arriv�e � destination).
 */
public class Usager extends Observable implements Simulable
{
	/** Le nom de l'usager */
	private String nom;
	/** Le trajet suivi par l'usager */
	private Trajet trajet;
	/**	Les lieux de d�part et d'arriv�e de l'usager */
	private Lieu depart, arrivee;
	/** Le lieu de d�part de la prochaine �tape de l'usager */
	private Lieu prochainDepart;
	/** La liste des trajets possibles */
	private ArrayList<Trajet> trajetsCandidats;
	/** Le temps d'attente avant le prochain �v�nement */
	private int attenteAvantEvenement;
	/** Le temps attendu dans les transports */
	private int tempsAttenduTransport;
	/**	L'�tape courante de l'usager */
	private Etape etapeActuelle;
	/** Indique si l'usager a atteint sa destination */
	private boolean estArrive;
	/** Indique si l'usager est en train d'attendre un transport */
	private boolean attenteTransport;
	/** La contrainte de choix de l'usager */
	private Contrainte contrainte;
	
	/**
	 * Cr�e un nouvel usager identifi� par son nom qui souhaite se rendre d'un lieu de d�part
	 * � un lieu d'arriv�e. Il dispose pour �a d'une liste de trajets candidats � emprunter
	 * et d'une contrainte permettant de faire son choix dans la liste.
	 * @param nom le nom de l'usager.
	 * @param depart le lieu de depart de l'usager.
	 * @param arrivee le lieu d'arriv�e de l'usager.
	 * @param trajetsCandidats les trajets envisag�s.
	 * @param contrainte une contrainte permettant de choisir le trajet � emprunter.
	 * @param tempsAttente le temps avant le d�part de l'usager.
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
	 * Retourne le lieu de d�part de l'usager.
	 * @return le lieu de d�part de l'usager.
	 */
	public Lieu getLieuDepart()
	{
		return depart;
	}
	
	/**
	 * Retourne le lieu d'arriv�e de l'usager.
	 * @return le lieu d'arriv�e de l'usager.
	 */
	public Lieu getLieuArrivee()
	{
		return arrivee;
	}
	
	/**
	 * Retourne l'�tape actuelle de l'usager.
	 * @return l'�tape actuelle de l'usager.
	 */
	public Etape getEtapeActuelle()
	{
		return etapeActuelle;
	}
    
	/**
	 * Teste si l'usager est arriv� � destination.
	 * @return vrai uniquement si l'usager est arriv� � destination.
	 */
	public boolean estArrive()
	{
		return estArrive;
	}
	
	/**
	 * Retourne le temps que l'usager a pass� � attendre les transports.
	 * @return le temps que l'usager a pass� � attendre les transports.
	 */
	public int getTempsAttenduTransport()
	{
		return tempsAttenduTransport;
	}
    
	/**
	 * Choisit et retourne le meilleur trajet pour l'usager. La m�thode peut retourner null
	 * si aucun trajet ne convient.
	 * @return le meilleur trajet choisi parmi les trajets candidats selon la contrainte
	 *         et les lieux de d�part et d'arriv�e.
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
	 * Permet de simuler le d�placement de l'usager le long de son trajet.
	 * Chaque appel � cette m�thode correspond au passage d'une unit� de temps.
	 * @param t le temps courant.
	 * @throws SimulationImpossibleException si la simulation est impossible, en
	 * 		   particulier si l'usager n'a pas de trajet qui lui est rattach�.
	 */
	public void simulerEvolution(int t) throws SimulationImpossibleException
	{
		if (trajet == null)
			throw new SimulationImpossibleException("Impossible de simuler si l'usager n'a pas de trajet rattach�.");
		
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
