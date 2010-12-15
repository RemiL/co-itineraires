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
 * Une ligne repr�sent�e par un nom, le moyen de transport qu'elle utilise et
 * la liste des �tapes qui la composent. Une ligne poss�de des horaires utilis�s
 * lors de la simulation pour d�finir les instants auxquels un v�hicule quitte
 * la station de d�part et commence � parcourir le circuit. Les arr�ts dans
 * les deux sens sont distingu�s pour simplifier la mod�lisation et si la ligne
 * est "aller-retour", un v�hicule fait demi-tour et repart dans l'autre sens.
 * Une ligne impl�mente l'interface Simulable ce qui signifie qu'on peut faire
 * �voluer son �tat au cours du temps pour simuler le d�placement des v�hicules.
 * Elle d�rive de la classe Observable ce qui signifie que les �v�nements relatifs
 * � la ligne (en particulier le d�part des v�hicules des stations) peuvent �tre
 * surveill�s par des observateurs.
 */
public class Ligne extends Observable implements Simulable
{
	/** Le nom de la ligne */
	private String nom;
	/** Le moyen de transport utilis� par la ligne */
	private MoyenTransport moyenTransport;
	/** Les diff�rentes �tapes composant la ligne */
	private LinkedList<Etape> tron�ons;
	/** Les horaires de la ligne (en tic d'horloge) */
	private int[] horaires;
	/** Les num�ros d'�tape que parcourent actuellement les v�hicules
	 *  sur la ligne */
	private LinkedList<Integer> etapesVehicules;
	/** La dur�e restante avant la fin de l'�tape en cours pour chacun
	 *  des v�hicules */
	private LinkedList<Integer> dureeRestanteEtapes;
	/** La derni�re station quitt�e par un v�hicule */
	private Lieu derniereStationQuittee;
	/** La liste des stations auxquelles un v�hicule est pr�sent actuellement */
	private ArrayList<Lieu> stationsVehiculePresent;
	
	/**
	 * Construit une ligne � partir de son nom, du moyen de transport utilis�
	 * et de ses horaires.
	 * @param nom le nom de la ligne.
	 * @param moyenTransport le moyen de transport utilis� sur la ligne.
	 * @param horaires un tableau d'entiers symbolisant les horaires de d�part.
	 */
	public Ligne(String nom, MoyenTransport moyenTransport, int[] horaires)
	{
		this.nom = nom;
		this.moyenTransport = moyenTransport;
		this.tron�ons = new LinkedList<Etape>();
		Arrays.sort(horaires); // On trie les horaires pour faciliter la recherche
		this.horaires = horaires;
		this.etapesVehicules = new LinkedList<Integer>();
		this.dureeRestanteEtapes = new LinkedList<Integer>();
		this.derniereStationQuittee = null;
		this.stationsVehiculePresent = new ArrayList<Lieu>();
	}
	/**
	 * Retourne le nom de la ligne.
	 * @return le nom de la ligne
	 */
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Retourne le nom des v�hicules utilis�s sur la ligne.
	 * @return le nom des v�hicules utilis�s sur la ligne.
	 */
	public String getNomVehicule()
	{
		return moyenTransport.getNomVehicule();
	}
	
	/**
	 * Retourne le moyenTransport utilis� sur la ligne.
	 * @return le moyen de transport utilis� sur la ligne.
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * Permet d'ajouter un tron�on � une ligne. Cette m�thode
	 * peut lever une exception LigneException si le nouveau
	 * tron�on n'est pas coh�rent avec la ligne actuelle.
	 * @param tron�on le morceau de tron�on � ajouter
	 * @throws LigneException si le nouveau tron�on ne commence pas l�
	 * 		   o� finir la ligne actuellement et s'il utilise un moyen
	 * 		   de transport diff�rent de la ligne.
	 */
	public void ajouterTron�on(Etape tron�on) throws LigneException
	{
		if (tron�ons.size() > 0 && tron�ons.getLast().getLieuArrivee() != tron�on.getLieuDepart())
			throw new LigneException("Ligne non coh�rante, l'�tape \""+tron�on+"\" ne peut pas suivre l'�tape \""+tron�ons.getLast()+"\".");
		if (tron�on.getMoyenTransport() != moyenTransport)
			throw new LigneException("Ajout impossible, l'�tape \""+tron�on+"\" n'utilise pas le bon moyen de transport.");
		
		tron�ons.add(tron�on);
	}
	
	/**
	 * Essaie de mettre � jour le co�t et la dur�e d'une �tape d�sir�e en utilisant
	 * les �tapes constituant la ligne. La fonction retourne vrai ou faux selon qu'il
	 * ait �t� possible de trouver un chemin compatible avec l'�tape d�sir�e sur la ligne.
	 * @param etape l'�tape dont on d�sire mettre � jour les champs co�t et dur�e.
	 * @return vrai si et seulement s'il a �t� possible de trouver un chemin sur
	 * 		   la ligne qui soit compatible avec l'�tape voulue.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		double cout = 0;
		int duree = 0;
		boolean departTrouve = false;
		
		for (Etape e : tron�ons)
		{
			if (!departTrouve) // Tant qu'on a pas trouv� le lieu de d�part
			{ // on cherche une �tape sur la ligne partant du lieu d�sir�
				if (e.getLieuDepart() == etape.getLieuDepart())
				{
					departTrouve = true;
					// On met � jour le co�t et la dur�e total du futur chemin
					cout = e.getCout();
					duree = e.getDuree();
				}
			}
			else
			{ // Si on a trouv� notre d�part, on poursuit la recherche du lieu
			  // d'arriv�e, tout en maintenant � jour le co�t et la dur�e.
				cout += e.getCout();
				duree += e.getDuree();
				
				// Si on trouve l'arriv�e d�sir�e
				if (e.getLieuArrivee() == etape.getLieuArrivee())
				{
					// On peut mettre � jour l'�tape.
					etape.setCout(cout);
					etape.setDuree(duree);
					
					return true;
				}
			}
		}
		// Si on arrive ici, on n'a pas r�ussi � trouver un chemin compatible.
		return false;
	}
	
	/**
	 * Retourne la derni�re station quitt�e par un v�hicule sur cette ligne.
	 * @return la derni�re station quitt�e par un v�hicule sur cette ligne.
	 */
	public Lieu getDerniereStationQuittee()
	{
		return derniereStationQuittee;
	}
	
	/**
	 * Retourne une repr�sentation textuelle de ligne sous la forme de
	 * "Ligne" suivant de son nom entre guillements.
	 * @return une chaine repr�sentant la ligne.
	 */
	public String toString()
	{
		return "Ligne \""+nom+"\"";
	}
	
	/**
	 * Permet de simuler le d�placement des v�hicules sur la ligne au cours du temps.
	 * Chaque appel � cette m�thode correspond au passage d'une unit� de temps
	 * @param t le temps courant.
	 */
	public void simulerEvolution(int t)
	{
		boolean aSupprimer = false; // un v�hicule a-t-il fini son service
		
		// Les v�hicules en stations les ont � pr�sent quitt�es.
		stationsVehiculePresent.clear();
		
		// On cherche si un v�hicule commence son service.
		if (Arrays.binarySearch(horaires, t) >= 0)
		{
			// On met � jour l'�tat des v�hicules sur la ligne
			etapesVehicules.add(0);
			dureeRestanteEtapes.add(tron�ons.get(0).getDuree());
			derniereStationQuittee = tron�ons.get(0).getLieuDepart();
			stationsVehiculePresent.add(derniereStationQuittee);
			// On notifie les �ventuels observateurs du changement.
			setChanged();
			notifyObservers(t);
		}
		
		// On met � jour l'�tat de chaque v�hicule sur la ligne
		for (int i=0; i<dureeRestanteEtapes.size(); i++)
		{			
			if (dureeRestanteEtapes.get(i) == 0)
			{ // S'il a fini son tron�on courant, on passe au suivant s'il existe sinon on le supprime
				if (etapesVehicules.get(i) == (tron�ons.size()-1))
					aSupprimer = true;
				else
				{
					// On met � jour l'�tat du v�hicule sur la ligne en passant au tron�on suivant.
					etapesVehicules.set(i, etapesVehicules.get(i)+1);
					dureeRestanteEtapes.set(i, tron�ons.get(etapesVehicules.get(i)).getDuree());
					derniereStationQuittee = tron�ons.get(etapesVehicules.get(i)).getLieuDepart();
					stationsVehiculePresent.add(tron�ons.get(etapesVehicules.get(i)).getLieuDepart());
					// On notifie les �ventuels observateurs du changement.
					setChanged();
					notifyObservers(t);
				}
			}
			// On met � jour la dur�e restante avant la fin du tron�on courant.
			dureeRestanteEtapes.set(i, dureeRestanteEtapes.get(i)-1);
		}
		
		if (aSupprimer) // Supprime le v�hicule qui peut avoir fini son service.
		{
			etapesVehicules.remove();
			dureeRestanteEtapes.remove();
		}
	}
	
	/**
	 * Permet de savoir si un v�hicule est pr�sent � la station fournie � l'instant courant.
	 * @param station la station o� l'on souhaite savoir si un v�hicule est pr�sent.
	 * @return vrai si et seulement si un v�hicule est pr�sent � la station choisie.
	 */
	public boolean estPresentVehicule(Lieu station)
	{	
		return stationsVehiculePresent.contains(station);
	}
}
