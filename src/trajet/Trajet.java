package trajet;

import graphe.Etape;
import graphe.Lieu;

import java.util.Iterator;
import java.util.LinkedList;

import moyenstransport.MoyenTransport;

/**
 * Classe repr�sentant un trajet constitu� d'un enchainement logique d'�tapes
 * pr�cisant le moyen de transport utilis� et des informations telles que le
 * co�t et la dur�e de l'�tape.
 */
public class Trajet implements Cloneable
{
	/**	Les �tapes constituant le trajet */
	private LinkedList<Etape> etapes;
	/**	Un it�rateur sur les �tapes */
	private Iterator<Etape> iterator;
	
	/**
	 * Cr�e un trajet vide.
	 */
	public Trajet()
	{
		etapes = new LinkedList<Etape>();
		iterator = etapes.listIterator();
	}
	
	/**
	 * Permet d'ajouter une nouvelle �tape au trajet. L�ve une exception si l'�tape ajout�e n'est
	 * pas coh�rente dans l'enchainement des �tapes ou si elle n'existe pas dans le graphe.
	 * @param etape l'�tape � ajouter.
	 * @throws EtapeNonExistanteException si l'�tape n'existe pas.
	 * @throws TrajetNonCoherentException si l'�tape produit une incoh�rence dans le trajet.
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
	 * Retourne le lieu de d�part du trajet.
	 * @return le lieu de depart du trajet.
	 */
	public Lieu getLieuDepart()
	{
		return etapes.getFirst().getLieuDepart();
	}
	
	/**
	 * Retourne le lieu d'arriv�e du trajet.
	 * @return le lieu d'arriv�e du trajet.
	 */
	public Lieu getLieuArrivee()
	{
		return etapes.getLast().getLieuArrivee();
	}
	
	/**
	 * Retourne le co�t global du trajet.
	 * @return le co�t global du trajet. 
	 */
	public double getCout()
	{
		double cout = 0;
		
		for (Etape e : etapes)
			cout += e.getCout();
		
		return cout;
	}
	
	/**
	 * Retourne la dur�e globale du trajet hors tant d'attente.
	 * @return la dur�e globale du trajet hors tant d'attente.
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
	 * @param lieu le lieu � tester.
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
	 * Teste si au moins une �tape du trajet utilise le moyen 
	 * de transport fourni.
	 * @param moyenTransport le moyen de transport � tester.
	 * @return vrai si une �tape au moins au mois utilise le moyen de transport, faux sinon.
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
	 * Teste si le trajet est termin� (i.e. si l'it�rateur sur les
	 * �tapes a atteint la fin de la liste).
	 * @return vrai si et seulement si le trajet est termin�.
	 */
	public boolean estTermine()
	{
		return !iterator.hasNext();
	}
	
	/**
	 * Retourne l'�tape suivante du trajet.
	 * @return l'�tape suivante du trajet.
	 */
	public Etape getEtapeSuivante()
	{
		return iterator.next();
	}
	
	/**
	 * Retournant la repr�sentation textuelle du trajet en explicitant
	 * ses �tapes avec leur num�ro.
	 * @return une chaine de caract�res repr�sentant le trajet.
	 */
	public String toString()
	{
		String str = "Trajet de \""+getLieuDepart()+"\" � \""+getLieuArrivee()+"\" :";
		
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
