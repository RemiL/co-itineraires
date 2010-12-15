package moyenstransport;

/**
 * Classe impl�mentant l'interface MoyenTransport et repr�sentant le moyen 
 * de transport "prendre le bus". Cette classe est un singleton.
 */
public class PrendreBus implements MoyenTransport
{
	private static PrendreBus instance;
	
	/**
	 * Constructeur priv�, voir getInstance().
	 */
	private PrendreBus()
	{
		
	}
	
	/**
	 * M�thode statique retournant l'unique instance de la classe. 
	 * @return l'instance unique de PrendreBus.
	 */
	public static PrendreBus getInstance()
	{
		if (instance == null) 
		{ // Premier appel
			instance = new PrendreBus();
		}
		
		return instance;
	}
	
	/**
	 * Retourne le nom du v�hicule associ� au moyen de transport.
	 * @return la chaine "bus".
	 */
	public String getNomVehicule()
	{
		return "bus";
	}
	
	/**
	 * Retourne une repr�sentation textuelle du moyen de transport
	 * sous la forme d'un verbe d'action.
	 * @return la chaine "Prendre le bus".
	 */
	public String toString()
	{
		return "Prendre le bus";
	}
}
