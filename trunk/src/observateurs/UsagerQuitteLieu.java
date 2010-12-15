package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

/**
 * Classe implémentant un observateur capable de surveiller les usagers et
 * détectant leur départ d'un lieu quelconque ou bien défini.
 */
public class UsagerQuitteLieu implements Observer
{
	/** Le lieu filtrant les évènements de départ */
	private Lieu lieu;
	
	/**
	 * Crée un observateur rapportant tous les évènements relatifs au
	 * départ d'un usager d'un lieu quelconque.
	 */
	public UsagerQuitteLieu()
	{
		lieu = null;
	}
	
	/**
	 * Crée un observateur rapportant tous les évènements relatifs au
	 * départ d'un usager d'un lieu précisé.
	 * @param lieu le lieu filtrant les évènements.
	 */
	public UsagerQuitteLieu(Lieu lieu)
	{
		this.lieu = lieu;
	}
	
	/**
	 * Méthode appellée automatiquement lorsqu'un objet surveillé est modifié.
	 * Affiche un message si un usager a quitté le lieu défini ou un lieu quelconque 
	 * selon les cas.
	 * @param obs l'observable pour lequel un évènement a été détecté par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument à l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend un usager et un entier.
			Usager usager = (Usager)obs;
			Integer t = (Integer)arg;
			
			if ((lieu == null || lieu == usager.getEtapeActuelle().getLieuDepart()) && !usager.estArrive())
				System.out.println("Evènement à t = "+t+" : l'usager "+usager+" vient de quitter le lieu "+usager.getEtapeActuelle().getLieuDepart()+".");
		}
		catch (ClassCastException e) { /* on ignore l'événement */ }
	}
}