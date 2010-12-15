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
 * Une ligne représentée par un nom, le moyen de transport qu'elle utilise et
 * la liste des étapes qui la composent. Une ligne posséde des horaires utilisés
 * lors de la simulation pour définir les instants auxquels un véhicule quitte
 * la station de départ et commence à parcourir le circuit. Les arrêts dans
 * les deux sens sont distingués pour simplifier la modélisation et si la ligne
 * est "aller-retour", un véhicule fait demi-tour et repart dans l'autre sens.
 * Une ligne implémente l'interface Simulable ce qui signifie qu'on peut faire
 * évoluer son état au cours du temps pour simuler le déplacement des véhicules.
 * Elle dérive de la classe Observable ce qui signifie que les évènements relatifs
 * à la ligne (en particulier le départ des véhicules des stations) peuvent être
 * surveillés par des observateurs.
 */
public class Ligne extends Observable implements Simulable
{
	/** Le nom de la ligne */
	private String nom;
	/** Le moyen de transport utilisé par la ligne */
	private MoyenTransport moyenTransport;
	/** Les différentes étapes composant la ligne */
	private LinkedList<Etape> tronçons;
	/** Les horaires de la ligne (en tic d'horloge) */
	private int[] horaires;
	/** Les numéros d'étape que parcourent actuellement les véhicules
	 *  sur la ligne */
	private LinkedList<Integer> etapesVehicules;
	/** La durée restante avant la fin de l'étape en cours pour chacun
	 *  des véhicules */
	private LinkedList<Integer> dureeRestanteEtapes;
	/** La dernière station quittée par un véhicule */
	private Lieu derniereStationQuittee;
	/** La liste des stations auxquelles un véhicule est présent actuellement */
	private ArrayList<Lieu> stationsVehiculePresent;
	
	/**
	 * Construit une ligne à partir de son nom, du moyen de transport utilisé
	 * et de ses horaires.
	 * @param nom le nom de la ligne.
	 * @param moyenTransport le moyen de transport utilisé sur la ligne.
	 * @param horaires un tableau d'entiers symbolisant les horaires de départ.
	 */
	public Ligne(String nom, MoyenTransport moyenTransport, int[] horaires)
	{
		this.nom = nom;
		this.moyenTransport = moyenTransport;
		this.tronçons = new LinkedList<Etape>();
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
	 * Retourne le nom des véhicules utilisés sur la ligne.
	 * @return le nom des véhicules utilisés sur la ligne.
	 */
	public String getNomVehicule()
	{
		return moyenTransport.getNomVehicule();
	}
	
	/**
	 * Retourne le moyenTransport utilisé sur la ligne.
	 * @return le moyen de transport utilisé sur la ligne.
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * Permet d'ajouter un tronçon à une ligne. Cette méthode
	 * peut lever une exception LigneException si le nouveau
	 * tronçon n'est pas cohérent avec la ligne actuelle.
	 * @param tronçon le morceau de tronçon à ajouter
	 * @throws LigneException si le nouveau tronçon ne commence pas là
	 * 		   où finir la ligne actuellement et s'il utilise un moyen
	 * 		   de transport différent de la ligne.
	 */
	public void ajouterTronçon(Etape tronçon) throws LigneException
	{
		if (tronçons.size() > 0 && tronçons.getLast().getLieuArrivee() != tronçon.getLieuDepart())
			throw new LigneException("Ligne non cohérante, l'étape \""+tronçon+"\" ne peut pas suivre l'étape \""+tronçons.getLast()+"\".");
		if (tronçon.getMoyenTransport() != moyenTransport)
			throw new LigneException("Ajout impossible, l'étape \""+tronçon+"\" n'utilise pas le bon moyen de transport.");
		
		tronçons.add(tronçon);
	}
	
	/**
	 * Essaie de mettre à jour le coût et la durée d'une étape désirée en utilisant
	 * les étapes constituant la ligne. La fonction retourne vrai ou faux selon qu'il
	 * ait été possible de trouver un chemin compatible avec l'étape désirée sur la ligne.
	 * @param etape l'étape dont on désire mettre à jour les champs coût et durée.
	 * @return vrai si et seulement s'il a été possible de trouver un chemin sur
	 * 		   la ligne qui soit compatible avec l'étape voulue.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		double cout = 0;
		int duree = 0;
		boolean departTrouve = false;
		
		for (Etape e : tronçons)
		{
			if (!departTrouve) // Tant qu'on a pas trouvé le lieu de départ
			{ // on cherche une étape sur la ligne partant du lieu désiré
				if (e.getLieuDepart() == etape.getLieuDepart())
				{
					departTrouve = true;
					// On met à jour le coût et la durée total du futur chemin
					cout = e.getCout();
					duree = e.getDuree();
				}
			}
			else
			{ // Si on a trouvé notre départ, on poursuit la recherche du lieu
			  // d'arrivée, tout en maintenant à jour le coût et la durée.
				cout += e.getCout();
				duree += e.getDuree();
				
				// Si on trouve l'arrivée désirée
				if (e.getLieuArrivee() == etape.getLieuArrivee())
				{
					// On peut mettre à jour l'étape.
					etape.setCout(cout);
					etape.setDuree(duree);
					
					return true;
				}
			}
		}
		// Si on arrive ici, on n'a pas réussi à trouver un chemin compatible.
		return false;
	}
	
	/**
	 * Retourne la dernière station quittée par un véhicule sur cette ligne.
	 * @return la dernière station quittée par un véhicule sur cette ligne.
	 */
	public Lieu getDerniereStationQuittee()
	{
		return derniereStationQuittee;
	}
	
	/**
	 * Retourne une représentation textuelle de ligne sous la forme de
	 * "Ligne" suivant de son nom entre guillements.
	 * @return une chaine représentant la ligne.
	 */
	public String toString()
	{
		return "Ligne \""+nom+"\"";
	}
	
	/**
	 * Permet de simuler le déplacement des véhicules sur la ligne au cours du temps.
	 * Chaque appel à cette méthode correspond au passage d'une unité de temps
	 * @param t le temps courant.
	 */
	public void simulerEvolution(int t)
	{
		boolean aSupprimer = false; // un véhicule a-t-il fini son service
		
		// Les véhicules en stations les ont à présent quittées.
		stationsVehiculePresent.clear();
		
		// On cherche si un véhicule commence son service.
		if (Arrays.binarySearch(horaires, t) >= 0)
		{
			// On met à jour l'état des véhicules sur la ligne
			etapesVehicules.add(0);
			dureeRestanteEtapes.add(tronçons.get(0).getDuree());
			derniereStationQuittee = tronçons.get(0).getLieuDepart();
			stationsVehiculePresent.add(derniereStationQuittee);
			// On notifie les éventuels observateurs du changement.
			setChanged();
			notifyObservers(t);
		}
		
		// On met à jour l'état de chaque véhicule sur la ligne
		for (int i=0; i<dureeRestanteEtapes.size(); i++)
		{			
			if (dureeRestanteEtapes.get(i) == 0)
			{ // S'il a fini son tronçon courant, on passe au suivant s'il existe sinon on le supprime
				if (etapesVehicules.get(i) == (tronçons.size()-1))
					aSupprimer = true;
				else
				{
					// On met à jour l'état du véhicule sur la ligne en passant au tronçon suivant.
					etapesVehicules.set(i, etapesVehicules.get(i)+1);
					dureeRestanteEtapes.set(i, tronçons.get(etapesVehicules.get(i)).getDuree());
					derniereStationQuittee = tronçons.get(etapesVehicules.get(i)).getLieuDepart();
					stationsVehiculePresent.add(tronçons.get(etapesVehicules.get(i)).getLieuDepart());
					// On notifie les éventuels observateurs du changement.
					setChanged();
					notifyObservers(t);
				}
			}
			// On met à jour la durée restante avant la fin du tronçon courant.
			dureeRestanteEtapes.set(i, dureeRestanteEtapes.get(i)-1);
		}
		
		if (aSupprimer) // Supprime le véhicule qui peut avoir fini son service.
		{
			etapesVehicules.remove();
			dureeRestanteEtapes.remove();
		}
	}
	
	/**
	 * Permet de savoir si un véhicule est présent à la station fournie à l'instant courant.
	 * @param station la station où l'on souhaite savoir si un véhicule est présent.
	 * @return vrai si et seulement si un véhicule est présent à la station choisie.
	 */
	public boolean estPresentVehicule(Lieu station)
	{	
		return stationsVehiculePresent.contains(station);
	}
}
