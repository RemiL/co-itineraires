package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

public interface Contrainte
{
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats);
}
