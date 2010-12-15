package graphe;

import ligne.Ligne;
import ligne.LigneException;
import moyenstransport.MoyenTransport;

/**
 * Classe repr�sentant une �tape entre deux lieux. Elle est caract�ris�e par
 * le moyen de transport utilis�e, la ligne de transport �ventuellement utilis�e,
 * sa dur�e et son co�t. Elle peut �tre vu par comme un arc d'un graphe dont les
 * noeuds seraient les lieux.
 */
public class Etape
{
	/** Le lieu de d�part de l'�tape */
	private Lieu lieuDepart;
	/** Le lieu d'arriv�e de l'�tape */
	private Lieu lieuArrivee;
	/** Le moyen de transport utilis� pour l'�tape */
	private MoyenTransport moyenTransport;
	/** La ligne � laquelle appartient l'�tape */
	private Ligne ligne;
	/** Le co�t de l'�tape */
	private double cout;
	/** La dur�e de l'�tape */
	private int duree;
	
	/**
	 * Construit une �tape entre deux lieux � partir de ses caract�ristiques.
	 * @param lieuDepart le lieu de d�part de l'�tape.
	 * @param lieuArrivee le lieu d'arriv�e de l'�tape.
	 * @param moyenTransport le moyen de transport utilis� pour l'�tape.
	 * @param ligne la ligne � laquelle appartient l'�tape
	 * @param cout le co�t de l'�tape.
	 * @param duree la dur�e de l'�tape.
	 * @throws LigneException si l'�tape casse la coh�rence de la ligne.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne, double cout, int duree) throws LigneException
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
		this.moyenTransport = moyenTransport;
		this.ligne = ligne;
		this.cout = cout;
		this.duree = duree;
		
		// Si cout et dur�e sont n�gatifs, il s'agit d'une �tape 
		// dont on veut d�terminer ces param�tres � partir du graphe
		// donc on ne l'ajoute pas au graphe.
		if (cout >= 0 && duree >= 0)
		{
			lieuDepart.ajouterEtapeVersVoisin(this);
			if (ligne != null)
				ligne.ajouterTron�on(this);
		}
	}
	
	/**
	 * Construit une �tape n'utilisant pas de ligne et au cout nul, pouvant �tre bidirectionnelle.
	 * Dans ce cas l'�tape oppos�e est aussi cr�ee automatiquement.
	 * @param lieuDepart le lieu de d�part de l'�tape.
	 * @param lieuArrivee le lieu d'arriv�e de l'�tape.
	 * @param moyenTransport le moyen de transport utilis� pour l'�tape.
	 * @param duree la dur�e de l'�tape.
	 * @param bidirectionnelle indique si l'�tape existe dans les deux sens.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree, boolean bidirectionnelle) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
		if (bidirectionnelle) // On cr�e l'�tape oppos�e
			new Etape(lieuArrivee, lieuDepart, moyenTransport, duree);
	}
	
	/**
	 * Construit une �tape n'utilisant pas de ligne et au cout nul.
	 * @param lieuDepart le lieu de d�part de l'�tape.
	 * @param lieuArrivee le lieu d'arriv�e de l'�tape.
	 * @param moyenTransport le moyen de transport utilis� pour l'�tape.
	 * @param duree la dur�e de l'�tape.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
	}
	
	/**
	 * Construit une �tape appartenant � une certaine ligne et utilisant un certain moyen de transport
	 * Son cout et sa dur�e devront �tre d�termin�s plus tard.
	 * @param lieuDepart le lieu de d�part de l'�tape.
	 * @param lieuArrivee le lieu d'arriv�e de l'�tape.
	 * @param moyenTransport le moyen de transport utilis� pour l'�tape.
	 * @param ligne la ligne � laquelle appartient l'�tape
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, ligne, -1, -1);
	}
	
	/**
	 * Construit une �tape n'appartenant pas � une ligne et utilisant un certain moyen de transport.
	 * Son cout et sa dur�e devront �tre d�termin�s plus tard.
	 * @param lieuDepart le lieu de d�part de l'�tape.
	 * @param lieuArrivee le lieu d'arriv�e de l'�tape.
	 * @param moyenTransport le moyen de transport utilis� pour l'�tape.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, -1, -1);
	}
	
	/**
	 * Retourne le lieu de d�part de l'�tape.
	 * @return le lieu de d�part de l'�tape.
	 */
	public Lieu getLieuDepart()
	{
		return lieuDepart;
	}
	
	/**
	 * Retourne le lieu d'arriv�e de l'�tape.
	 * @return le lieu d'arriv�e de l'�tape.
	 */
	public Lieu getLieuArrivee()
	{
		return lieuArrivee;
	}
	
	/**
	 * Retourne le moyen de transport de l'�tape.
	 * @return le moyen de transport de l'�tape.
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * Retourne la ligne de l'�tape ou �ventuellement null 
	 * si aucun ligne n'est utilis�e.
	 * @return la ligne de l'�tape.
	 */
	public Ligne getLigne()
	{
		return ligne;
	}
	
	/**
	 * Retourne le co�t de l'�tape. Si la valeur est n�gative,
	 * cela signifie que le co�t de l'�tape reste � d�terminer.
	 * @return le co�t de l'�tape.
	 */
	public double getCout()
	{
		return cout;
	}
	
	/**
	 * Retourne la dur�e de l'�tape. Si la valeur est n�gative,
	 * cela signifie que la dur�e de l'�tape reste � d�terminer.
	 * @return la dur�e de l'�tape.
	 */
	public int getDuree()
	{
		return duree;
	}
	
	/**
	 * Permet de mettre � jour le cout de l'�tape uniquement
	 * s'il �tait ind�fini. S'il �tait d�j� d�fini la fonction
	 * retourne faux et ne modifie rien.
	 * @param cout le cout de l'�tape.
	 * @return vrai uniquement si le cout a �t� modifi�.
	 */
	public boolean setCout(double cout)
	{
		if (this.cout < 0)
		{
			this.cout = cout;
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Permet de mettre � jour la dur�e de l'�tape uniquement
	 * si elle �tait ind�finie. Si elle �tait d�j� d�fini la
	 * fonction retourne faux et ne modifie rien.
	 * @param cout la dur�e de l'�tape.
	 * @return vrai uniquement si la dur�e a �t� modifi�e.
	 */
	public boolean setDuree(int duree)
	{
		if (this.duree < 0)
		{
			this.duree = duree;
			return true;
		}
		else
			return false;
	}

	/**
	 * Essaie de mettre � jour le co�t et la dur�e d'une �tape d�sir�e en utilisant
	 * l'�tape consid�r�e. La fonction retourne vrai ou faux selon qu'il ait �t� 
	 * possible de mettre � jour les valeurs souhait�es (i.e. si les deux �tapes
	 * �taient compatibles).
	 * @param etape l'�tape dont on d�sire mettre � jour les champs co�t et dur�e.
	 * @return vrai si et seulement s'il a �t� possible de faire la mise � jour.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		boolean etapeCompatible = false;
		
		// On n'est compatible qui si on partage le m�me lieu de d�part, la m�me ligne 
		// et le m�me moyen de transport.
		if (this.lieuDepart == etape.lieuDepart && this.ligne == etape.ligne && this.moyenTransport == etape.moyenTransport)
		{
			// Si on a le m�me lieu d'arriv�e on est s�r que les deux �tapes sont compatibles
			if (this.lieuArrivee == etape.lieuArrivee)
			{ // on met � jour les param�tres
				etapeCompatible = true;
				etape.cout = this.cout;
				etape.duree = this.duree;
			}
			// Sinon si on a une ligne, on peut l'utiliser pour essayer de faire la mise � jour
			else if (etape.ligne != null)
				etapeCompatible = ligne.mettreAJourCoutEtDuree(etape);
		}
		
		return etapeCompatible;
	}
	
	/**
	 * Teste si l'�tape passe par le lieu fourni.
	 * @param lieu le lieu � tester.
	 * @return vrai ssi l'�tape part ou arrive dans le lien.
	 */
	public boolean passePar(Lieu lieu)
	{
		return (lieuDepart == lieu || lieuArrivee == lieu);
	}
	
	/**
	 * Retourne une repr�sentation textuelle de l'�tape contenant des informations
	 * sur le moyen de transport utilis�, l'�ventuelle ligne et les lieux de d�part
	 * et d'arriv�e.
	 * @return une chaine de caract�res repr�sentant l'�tape.
	 */
	public String toString()
	{
		String str = moyenTransport.toString();
		
		if (ligne != null)
			str += " ("+ligne+")";
		
		return str+" de \""+getLieuDepart()+"\" jusqu'� \""+getLieuArrivee()+"\"";
	}
}
