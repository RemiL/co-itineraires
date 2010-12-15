package trajet;

/**
 * Classe abstraite permettant de regrouper les exceptions pouvant être levées
 * par l'ajout d'une étape à un trajet.
 */
public abstract class TrajetException extends Exception
{
	private static final long serialVersionUID = 702468081698212125L;
	
	/**
	 * Constructeur par défaut.
	 */
	public TrajetException()
	{
		super();
	}
}
