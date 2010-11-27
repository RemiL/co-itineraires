package trajet;

import java.util.ArrayList;

public class Trajet
{
	private ArrayList<Etape> etapes;
	
	public Trajet()
	{
		etapes = new ArrayList<Etape>();
	}
	
	public void ajouterEtape(Etape etape)
	{
		etapes.add(etape);
	}
}
