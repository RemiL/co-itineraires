package observateurs;

import graphe.Lieu;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

/**
 * Classe impl�mentant un observateur capable de surveiller les usagers et
 * d�tectant leur d�part d'un lieu quelconque ou bien d�fini.
 */
public class UsagerQuitteLieu implements Observer
{
	/** Le lieu filtrant les �v�nements de d�part */
	private Lieu lieu;
	
	/**
	 * Cr�e un observateur rapportant tous les �v�nements relatifs au
	 * d�part d'un usager d'un lieu quelconque.
	 */
	public UsagerQuitteLieu()
	{
		lieu = null;
	}
	
	/**
	 * Cr�e un observateur rapportant tous les �v�nements relatifs au
	 * d�part d'un usager d'un lieu pr�cis�.
	 * @param lieu le lieu filtrant les �v�nements.
	 */
	public UsagerQuitteLieu(Lieu lieu)
	{
		this.lieu = lieu;
	}
	
	/**
	 * M�thode appell�e automatiquement lorsqu'un objet surveill� est modifi�.
	 * Affiche un message si un usager a quitt� le lieu d�fini ou un lieu quelconque 
	 * selon les cas.
	 * @param obs l'observable pour lequel un �v�nement a �t� d�tect� par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument � l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend un usager et un entier.
			Usager usager = (Usager)obs;
			Integer t = (Integer)arg;
			
			if ((lieu == null || lieu == usager.getEtapeActuelle().getLieuDepart()) && !usager.estArrive())
				System.out.println("Ev�nement � t = "+t+" : l'usager "+usager+" vient de quitter le lieu "+usager.getEtapeActuelle().getLieuDepart()+".");
		}
		catch (ClassCastException e) { /* on ignore l'�v�nement */ }
	}
}