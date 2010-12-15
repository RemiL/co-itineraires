package graphe;

import ligne.Ligne;
import ligne.LigneException;
import moyenstransport.MoyenTransport;

/**
 * Classe représentant une étape entre deux lieux. Elle est caractérisée par
 * le moyen de transport utilisée, la ligne de transport éventuellement utilisée,
 * sa durée et son coût. Elle peut être vu par comme un arc d'un graphe dont les
 * noeuds seraient les lieux.
 */
public class Etape
{
	/** Le lieu de départ de l'étape */
	private Lieu lieuDepart;
	/** Le lieu d'arrivée de l'étape */
	private Lieu lieuArrivee;
	/** Le moyen de transport utilisé pour l'étape */
	private MoyenTransport moyenTransport;
	/** La ligne à laquelle appartient l'étape */
	private Ligne ligne;
	/** Le coût de l'étape */
	private double cout;
	/** La durée de l'étape */
	private int duree;
	
	/**
	 * Construit une étape entre deux lieux à partir de ses caractéristiques.
	 * @param lieuDepart le lieu de départ de l'étape.
	 * @param lieuArrivee le lieu d'arrivée de l'étape.
	 * @param moyenTransport le moyen de transport utilisé pour l'étape.
	 * @param ligne la ligne à laquelle appartient l'étape
	 * @param cout le coût de l'étape.
	 * @param duree la durée de l'étape.
	 * @throws LigneException si l'étape casse la cohérence de la ligne.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne, double cout, int duree) throws LigneException
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
		this.moyenTransport = moyenTransport;
		this.ligne = ligne;
		this.cout = cout;
		this.duree = duree;
		
		// Si cout et durée sont négatifs, il s'agit d'une étape 
		// dont on veut déterminer ces paramètres à partir du graphe
		// donc on ne l'ajoute pas au graphe.
		if (cout >= 0 && duree >= 0)
		{
			lieuDepart.ajouterEtapeVersVoisin(this);
			if (ligne != null)
				ligne.ajouterTronçon(this);
		}
	}
	
	/**
	 * Construit une étape n'utilisant pas de ligne et au cout nul, pouvant être bidirectionnelle.
	 * Dans ce cas l'étape opposée est aussi créee automatiquement.
	 * @param lieuDepart le lieu de départ de l'étape.
	 * @param lieuArrivee le lieu d'arrivée de l'étape.
	 * @param moyenTransport le moyen de transport utilisé pour l'étape.
	 * @param duree la durée de l'étape.
	 * @param bidirectionnelle indique si l'étape existe dans les deux sens.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree, boolean bidirectionnelle) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
		if (bidirectionnelle) // On crée l'étape opposée
			new Etape(lieuArrivee, lieuDepart, moyenTransport, duree);
	}
	
	/**
	 * Construit une étape n'utilisant pas de ligne et au cout nul.
	 * @param lieuDepart le lieu de départ de l'étape.
	 * @param lieuArrivee le lieu d'arrivée de l'étape.
	 * @param moyenTransport le moyen de transport utilisé pour l'étape.
	 * @param duree la durée de l'étape.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
	}
	
	/**
	 * Construit une étape appartenant à une certaine ligne et utilisant un certain moyen de transport
	 * Son cout et sa durée devront être déterminés plus tard.
	 * @param lieuDepart le lieu de départ de l'étape.
	 * @param lieuArrivee le lieu d'arrivée de l'étape.
	 * @param moyenTransport le moyen de transport utilisé pour l'étape.
	 * @param ligne la ligne à laquelle appartient l'étape
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, ligne, -1, -1);
	}
	
	/**
	 * Construit une étape n'appartenant pas à une ligne et utilisant un certain moyen de transport.
	 * Son cout et sa durée devront être déterminés plus tard.
	 * @param lieuDepart le lieu de départ de l'étape.
	 * @param lieuArrivee le lieu d'arrivée de l'étape.
	 * @param moyenTransport le moyen de transport utilisé pour l'étape.
	 */
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport) throws LigneException
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, -1, -1);
	}
	
	/**
	 * Retourne le lieu de départ de l'étape.
	 * @return le lieu de départ de l'étape.
	 */
	public Lieu getLieuDepart()
	{
		return lieuDepart;
	}
	
	/**
	 * Retourne le lieu d'arrivée de l'étape.
	 * @return le lieu d'arrivée de l'étape.
	 */
	public Lieu getLieuArrivee()
	{
		return lieuArrivee;
	}
	
	/**
	 * Retourne le moyen de transport de l'étape.
	 * @return le moyen de transport de l'étape.
	 */
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	/**
	 * Retourne la ligne de l'étape ou éventuellement null 
	 * si aucun ligne n'est utilisée.
	 * @return la ligne de l'étape.
	 */
	public Ligne getLigne()
	{
		return ligne;
	}
	
	/**
	 * Retourne le coût de l'étape. Si la valeur est négative,
	 * cela signifie que le coût de l'étape reste à déterminer.
	 * @return le coût de l'étape.
	 */
	public double getCout()
	{
		return cout;
	}
	
	/**
	 * Retourne la durée de l'étape. Si la valeur est négative,
	 * cela signifie que la durée de l'étape reste à déterminer.
	 * @return la durée de l'étape.
	 */
	public int getDuree()
	{
		return duree;
	}
	
	/**
	 * Permet de mettre à jour le cout de l'étape uniquement
	 * s'il était indéfini. S'il était déjà défini la fonction
	 * retourne faux et ne modifie rien.
	 * @param cout le cout de l'étape.
	 * @return vrai uniquement si le cout a été modifié.
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
	 * Permet de mettre à jour la durée de l'étape uniquement
	 * si elle était indéfinie. Si elle était déjà défini la
	 * fonction retourne faux et ne modifie rien.
	 * @param cout la durée de l'étape.
	 * @return vrai uniquement si la durée a été modifiée.
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
	 * Essaie de mettre à jour le coût et la durée d'une étape désirée en utilisant
	 * l'étape considérée. La fonction retourne vrai ou faux selon qu'il ait été 
	 * possible de mettre à jour les valeurs souhaitées (i.e. si les deux étapes
	 * étaient compatibles).
	 * @param etape l'étape dont on désire mettre à jour les champs coût et durée.
	 * @return vrai si et seulement s'il a été possible de faire la mise à jour.
	 */
	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		boolean etapeCompatible = false;
		
		// On n'est compatible qui si on partage le même lieu de départ, la même ligne 
		// et le même moyen de transport.
		if (this.lieuDepart == etape.lieuDepart && this.ligne == etape.ligne && this.moyenTransport == etape.moyenTransport)
		{
			// Si on a le même lieu d'arrivée on est sûr que les deux étapes sont compatibles
			if (this.lieuArrivee == etape.lieuArrivee)
			{ // on met à jour les paramètres
				etapeCompatible = true;
				etape.cout = this.cout;
				etape.duree = this.duree;
			}
			// Sinon si on a une ligne, on peut l'utiliser pour essayer de faire la mise à jour
			else if (etape.ligne != null)
				etapeCompatible = ligne.mettreAJourCoutEtDuree(etape);
		}
		
		return etapeCompatible;
	}
	
	/**
	 * Teste si l'étape passe par le lieu fourni.
	 * @param lieu le lieu à tester.
	 * @return vrai ssi l'étape part ou arrive dans le lien.
	 */
	public boolean passePar(Lieu lieu)
	{
		return (lieuDepart == lieu || lieuArrivee == lieu);
	}
	
	/**
	 * Retourne une représentation textuelle de l'étape contenant des informations
	 * sur le moyen de transport utilisé, l'éventuelle ligne et les lieux de départ
	 * et d'arrivée.
	 * @return une chaine de caractères représentant l'étape.
	 */
	public String toString()
	{
		String str = moyenTransport.toString();
		
		if (ligne != null)
			str += " ("+ligne+")";
		
		return str+" de \""+getLieuDepart()+"\" jusqu'à \""+getLieuArrivee()+"\"";
	}
}
