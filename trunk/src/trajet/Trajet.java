package trajet;

import graphe.Etape;
import graphe.Lieu;

import java.util.LinkedList;

public class Trajet
{
	private LinkedList<Etape> etapes;
	
	public Trajet()
	{
		etapes = new LinkedList<Etape>();
	}
	
	public void ajouterEtape(Etape etape)
	{
		etapes.add(etape);
	}
	
	public Lieu getLieuDepart()
	{
		return etapes.getFirst().getLieuArrivee();
	}
	
	public Lieu getLieuArrivee()
	{
		return etapes.getLast().getLieuArrivee();
	}
	
	public String toString()
	{
		String str = "Trajet de \""+getLieuDepart()+"\" à \""+getLieuArrivee()+"\" :";
		
		int numEtape = 1;
		for (Etape e : etapes)
		{
			str += "\n"+numEtape+". "+e.toString();
			numEtape++;
		}
		
		return str;
	}
}
