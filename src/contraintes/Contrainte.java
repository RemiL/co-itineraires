package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Interface d'une contrainte abstraite permettant de sélectionner les meilleurs
 * trajets parmi plusieurs candidats potentiels en fonction d'un ou plusieurs critères.
 */
public interface Contrainte
{
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats);
}