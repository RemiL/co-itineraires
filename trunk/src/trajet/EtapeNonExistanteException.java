package trajet;

import graphe.Etape;

/**
 * Exception héritant de TrajetException représentant une exception
 * levée lors de l'ajout d'une étape non existante à un trajet.
 */
public class EtapeNonExistanteException extends TrajetException
{
	private static final long serialVersionUID = -2620710761828646860L;
	/** L'étape non existante dans le graphe. */
	private Etape etape;
	
	/**
	 * Construit une exception EtapeNonExistanteException suite à la tentative
	 * d'ajout de etape à un trajet.
	 * @param etape l'étape non existante.
	 */
	public EtapeNonExistanteException(Etape etape)
	{
		super();
		this.etape = etape;
	}
	
	/**
	 * Retourne un message décrivant l'étape non existante.
	 * @return un message d'erreur décrivant l'étape non existante.
	 */
	public String getMessage()
	{
		return "L'étape \""+etape+"\" n'existe pas.";
	}
}
