package simulateurdeplacement;

/**
 * Exception lev�e lorsque la simulation est impossible pour une raison
 * � pr�ciser dans le message de l'exception.
 */
public class SimulationImpossibleException extends Exception
{
	private static final long serialVersionUID = -1565379686372178090L;
	
	/**
	 * Cr�e une nouvelle exception avec le message fourni.
	 * @param msg le message.
	 */
	public SimulationImpossibleException(String msg)
	{
		super(msg);
	}
}
