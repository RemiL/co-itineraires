package trajet;

import graphe.Etape;

/**
 * Exception h�ritant de TrajetException repr�sentant une exception
 * lev�e lors de l'ajout d'une �tape non existante � un trajet.
 */
public class EtapeNonExistanteException extends TrajetException
{
	private static final long serialVersionUID = -2620710761828646860L;
	/** L'�tape non existante dans le graphe. */
	private Etape etape;
	
	/**
	 * Construit une exception EtapeNonExistanteException suite � la tentative
	 * d'ajout de etape � un trajet.
	 * @param etape l'�tape non existante.
	 */
	public EtapeNonExistanteException(Etape etape)
	{
		super();
		this.etape = etape;
	}
	
	/**
	 * Retourne un message d�crivant l'�tape non existante.
	 * @return un message d'erreur d�crivant l'�tape non existante.
	 */
	public String getMessage()
	{
		return "L'�tape \""+etape+"\" n'existe pas.";
	}
}
