package trajet;

import graphe.Etape;

public class EtapeNonExistanteException extends TrajetException
{
	private static final long serialVersionUID = -2620710761828646860L;
	private Etape etape;
	
	public EtapeNonExistanteException(Etape etape)
	{
		super();
		this.etape = etape;
	}
}
