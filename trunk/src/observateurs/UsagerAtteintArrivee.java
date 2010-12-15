package observateurs;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

/**
 * Classe impl�mentant un observateur capable de surveiller les usagers et
 * d�tectant l'arriv�e d'un usager � destination.
 */
public class UsagerAtteintArrivee implements Observer
{
	/**
	 * Cr�e un nouvel observateur capable de surveiller les usagers et de
	 * d�tecter leur arriv�e � destination.
	 */
	public UsagerAtteintArrivee()
	{
		
	}
	
	/**
	 * M�thode appell�e automatiquement lorsqu'un objet surveill� est modifi�.
	 * Affiche un message si un usager a atteint sa destination.
	 * @param obs l'observable pour lequel un �v�nement a �t� d�tect� par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument � l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend un usager et un entier.
			Usager usager = (Usager)obs;
			Integer t = (Integer)arg;
			
			if (usager.estArrive())
				System.out.println("Ev�nement � t = "+t+" : l'usager "+usager+" vient d'atteindre sa destination ("+usager.getLieuArrivee()+") (temps pass� � attendre les transports = "+usager.getTempsAttenduTransport()+").");
		}
		catch (ClassCastException e) { /* on ignore l'�v�nement */ }
	}
}