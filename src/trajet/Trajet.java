package trajet;

import graphe.Etape;
import graphe.Lieu;

import java.util.Iterator;
import java.util.LinkedList;

import moyenstransport.MoyenTransport;

/**
 * Classe représentant un trajet constitué d'un enchainement logique d'étapes
 * précisant le moyen de transport utilisé et des informations telles que le
 * coût et la durée de l'étape.
 */
public class Trajet implements Cloneable
{
	/**	Les étapes constituant le trajet */
	private LinkedList<Etape> etapes;
	/**	Un itérateur sur les étapes */
	private Iterator<Etape> iterator;
	
	/**
	 * Crée un trajet vide.
	 */
	public Trajet()
	{
		etapes = new LinkedList<Etape>();
		iterator = etapes.listIterator();
	}
	
	/**
	 * Permet d'ajouter une nouvelle étape au trajet. Lève une exception si l'étape ajoutée n'est
	 * pas cohérente dans l'enchainement des étapes ou si elle n'existe pas dans le graphe.
	 * @param etape l'étape à ajouter.
	 * @throws EtapeNonExistanteException si l'étape n'existe pas.
	 * @throws TrajetNonCoherentException si l'étape produit une incohérence dans le trajet.
	 */
	public void ajouterEtape(Etape etape) throws TrajetException
	{
		if (!etape.getLieuDepart().mettreAJourCoutEtDuree(etape))
			throw new EtapeNonExistanteException(etape);
		
		if (etapes.size() > 0 && etapes.getLast().getLieuArrivee() != etape.getLieuDepart())
			throw new TrajetNonCoherentException(etapes.getLast(), etape);
		
		etapes.add(etape);
		iterator = etapes.listIterator();
	}
	
	/**
	 * Retourne le lieu de départ du trajet.
	 * @return le lieu de depart du trajet.
	 */
	public Lieu getLieuDepart()
	{
		return etapes.getFirst().getLieuDepart();
	}
	
	/**
	 * Retourne le lieu d'arrivée du trajet.
	 * @return le lieu d'arrivée du trajet.
	 */
	public Lieu getLieuArrivee()
	{
		return etapes.getLast().getLieuArrivee();
	}
	
	/**
	 * Retourne le coût global du trajet.
	 * @return le coût global du trajet. 
	 */
	public double getCout()
	{
		double cout = 0;
		
		for (Etape e : etapes)
			cout += e.getCout();
		
		return cout;
	}
	
	/**
	 * Retourne la durée globale du trajet hors tant d'attente.
	 * @return la durée globale du trajet hors tant d'attente.
	 */
	public int getDuree()
	{
		int duree = 0;
		
		for (Etape e : etapes)
			duree += e.getDuree();
		
		return duree;
	}
	
	/**
	 * Teste si le trajet passe par le lieu fourni.
	 * @param lieu le lieu à tester.
	 * @return vrai si le lieu appartient au trajet, faux sinon.
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
	 * Teste si au moins une étape du trajet utilise le moyen 
	 * de transport fourni.
	 * @param moyenTransport le moyen de transport à tester.
	 * @return vrai si une étape au moins au mois utilise le moyen de transport, faux sinon.
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
	 * Teste si le trajet est terminé (i.e. si l'itérateur sur les
	 * étapes a atteint la fin de la liste).
	 * @return vrai si et seulement si le trajet est terminé.
	 */
	public boolean estTermine()
	{
		return !iterator.hasNext();
	}
	
	/**
	 * Retourne l'étape suivante du trajet.
	 * @return l'étape suivante du trajet.
	 */
	public Etape getEtapeSuivante()
	{
		return iterator.next();
	}
	
	/**
	 * Retournant la représentation textuelle du trajet en explicitant
	 * ses étapes avec leur numéro.
	 * @return une chaine de caractères représentant le trajet.
	 */
	public String toString()
	{
		String str = "Trajet de \""+getLieuDepart()+"\" à \""+getLieuArrivee()+"\" :";
		
		int numEtape = 1;
		for (Etape e : etapes)
		{
			str += "\n"+numEtape+". "+e+".";
			numEtape++;
		}
		
		return str;
	}
	
	/**
	 * Retourne une copie du trajet.
	 * @return une copie du trajet.
	 */
	public Trajet clone()
	{
		Trajet t = new Trajet();
		t.etapes = (LinkedList<Etape>) this.etapes.clone();
		t.iterator = t.etapes.listIterator();
		
		return t;
	}
}
