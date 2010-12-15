package observateurs;

import java.util.Observable;
import java.util.Observer;

import usager.Usager;

/**
 * Classe implémentant un observateur capable de surveiller les usagers et
 * détectant l'arrivée d'un usager à destination.
 */
public class UsagerAtteintArrivee implements Observer
{
	/**
	 * Crée un nouvel observateur capable de surveiller les usagers et de
	 * détecter leur arrivée à destination.
	 */
	public UsagerAtteintArrivee()
	{
		
	}
	
	/**
	 * Méthode appellée automatiquement lorsqu'un objet surveillé est modifié.
	 * Affiche un message si un usager a atteint sa destination.
	 * @param obs l'observable pour lequel un évènement a été détecté par l'observeur.
	 * @param arg un objet quelconque pouvant servir d'argument à l'observeur.
	 */
	public void update(Observable obs, Object arg)
	{
		try
		{ // On attend un usager et un entier.
			Usager usager = (Usager)obs;
			Integer t = (Integer)arg;
			
			if (usager.estArrive())
				System.out.println("Evènement à t = "+t+" : l'usager "+usager+" vient d'atteindre sa destination ("+usager.getLieuArrivee()+") (temps passé à attendre les transports = "+usager.getTempsAttenduTransport()+").");
		}
		catch (ClassCastException e) { /* on ignore l'événement */ }
	}
}