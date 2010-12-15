package trajet;

import graphe.Etape;
import graphe.Lieu;

import java.util.LinkedList;

import moyenstransport.MoyenTransport;

public class Trajet
{
	private LinkedList<Etape> etapes;
	/**
	 * constructeur Trajet
	 */
	public Trajet()
	{
		etapes = new LinkedList<Etape>();
	}
	
	public void ajouterEtape(Etape etape) throws TrajetException
	{
		if (!etape.getLieuDepart().mettreAJourCoutEtDuree(etape))
			throw new EtapeNonExistanteException(etape);
		
		if (etapes.size() > 0 && etapes.getLast().getLieuArrivee() != etape.getLieuDepart())
			throw new TrajetNonCoherentException(etapes.getLast(), etape);
		
		etapes.add(etape);
	}
	
	public Lieu getLieuDepart()
	{
		return etapes.getFirst().getLieuDepart();
	}
	
	public Lieu getLieuArrivee()
	{
		return etapes.getLast().getLieuArrivee();
	}
	
	public double getCout()
	{
		double cout = 0;
		
		for (Etape e : etapes)
			cout += e.getCout();
		
		return cout;
	}
	
	public int getDuree()
	{
		int duree = 0;
		
		for (Etape e : etapes)
			duree += e.getDuree();
		
		return duree;
	}
	
	public boolean passePar(Lieu lieu)
	{
		for (Etape e : etapes)
		{
			if (e.passePar(lieu))
				return true;
		}
		
		return false;
	}
	

	public boolean utilise(MoyenTransport moyenTransport)
	{
		for (Etape e : etapes)
		{
			if (e.getMoyenTransport() == moyenTransport)
				return true;
		}
		
		return false;
	}
	
	public String toString()
	{
		String str = "Trajet de \""+getLieuDepart()+"\" à \""+getLieuArrivee()+"\" :";
		
		int numEtape = 1;
		for (Etape e : etapes)
		{
			str += "\n"+numEtape+". "+e;
			numEtape++;
		}
		
		return str;
	}
}
