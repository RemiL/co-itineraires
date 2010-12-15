package trajet;

import graphe.Etape;

/**
 * class EtapeNonExistanteException qui hérite de TrajetException
 * exception qui attrape un trajet non existant
 * @author Marie
 *
 */
public class EtapeNonExistanteException extends TrajetException
{
	private static final long serialVersionUID = -2620710761828646860L;
	private Etape etape;
	
	/**
	 * constructeur EtapeNonExistanteException
	 * @param etape : etape inexistante
	 */
	public EtapeNonExistanteException(Etape etape)
	{
		super();
		this.etape = etape;
	}
}
