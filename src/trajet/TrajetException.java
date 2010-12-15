package trajet;

/**
 * Classe abstraite permettant de regrouper les exceptions pouvant �tre lev�es
 * par l'ajout d'une �tape � un trajet.
 */
public abstract class TrajetException extends Exception
{
	private static final long serialVersionUID = 702468081698212125L;
	
	/**
	 * Constructeur par d�faut.
	 */
	public TrajetException()
	{
		super();
	}
}
