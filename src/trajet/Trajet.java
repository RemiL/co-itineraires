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
	
	/**
	 * Methode ajouterEtape qui ajoute une �tape au trajet
	 * Retourne une exception si l'�tape n'existe pas avec les conditions demandees
	 * @param etape �tape � ajouter
	 * @throws TrajetException exception de non coh�rence entre l'�tape demand�e et le lieu actuel 
	 */
	public void ajouterEtape(Etape etape) throws TrajetException
	{
		if (!etape.getLieuDepart().mettreAJourCoutEtDuree(etape))
			throw new EtapeNonExistanteException(etape);
		
		if (etapes.size() > 0 && etapes.getLast().getLieuArrivee() != etape.getLieuDepart())
			throw new TrajetNonCoherentException(etapes.getLast(), etape);
		
		etapes.add(etape);
	}
	/**
	 * Methode getLieuDepart
	 * @return le lieu de depart du trajet
	 */
	public Lieu getLieuDepart()
	{
		return etapes.getFirst().getLieuDepart();
	}
	
	/**
	 * Methode getLieuArrivee
	 * @return le lieu d'arrivee du trajet
	 */
	public Lieu getLieuArrivee()
	{
		return etapes.getLast().getLieuArrivee();
	}
	
	/**
	 * M�thode getCout calcule le cout global de toutes les �tapes d'un trajet
	 * @return cout
	 */
	public double getCout()
	{
		double cout = 0;
		
		for (Etape e : etapes)
			cout += e.getCout();
		
		return cout;
	}
	
	/**
	 * Methode getDuree qui calcule la duree totale de toutes les etapes d'un trajet
	 * @return duree
	 */
	public int getDuree()
	{
		int duree = 0;
		
		for (Etape e : etapes)
			duree += e.getDuree();
		
		return duree;
	}
	
	/**
	 * Methode passePar qui verifie etant donne un lieu si il appartient � un trajet
	 * @param lieu
	 * @return vrai si le lieu appartient au trajet, faux sinon
	 */
	public boolean passePar(Lieu lieu)
	{
		for (Etape e : etapes)
		{
			if (e.passePar(lieu))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Methode utilise qui verifie si pour un trajet, une �tape utilise le moyen de transport voulu
	 * @param moyenTransport
	 * @return vrai si le trajet comporte le moyen de transport, faux sinon
	 */
	public boolean utilise(MoyenTransport moyenTransport)
	{
		for (Etape e : etapes)
		{
			if (e.getMoyenTransport() == moyenTransport)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Methode toString qui affiche les caracteristiques d'un trajet
	 * en explicitant les �tapes avec leur num�ro
	 */
	public String toString()
	{
		String str = "Trajet de \""+getLieuDepart()+"\" � \""+getLieuArrivee()+"\" :";
		
		int numEtape = 1;
		for (Etape e : etapes)
		{
			str += "\n"+numEtape+". "+e;
			numEtape++;
		}
		
		return str;
	}
}
