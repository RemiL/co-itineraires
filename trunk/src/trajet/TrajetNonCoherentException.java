package trajet;

import graphe.Etape;

/**
 * Class TrajetNonCoherentException qui hérite de TrajetException
 * exception qui capture un trajet non cohérent
 * 
 * @author Marie
 *
 */
public class TrajetNonCoherentException extends TrajetException
{
	private static final long serialVersionUID = 8470833671943488438L;
	private Etape derniereEtape;
	private Etape nouvelleEtape;
	
	/**
	 * constructeur TrajetNonCoherentException
	 * @param derniereEtape
	 * @param nouvelleEtape
	 */
	public TrajetNonCoherentException(Etape derniereEtape, Etape nouvelleEtape)
	{
		super();
		this.derniereEtape = derniereEtape;
		this.nouvelleEtape = nouvelleEtape;
	}
}
