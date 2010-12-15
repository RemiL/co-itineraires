package moyenstransport;

/**
 * Classe implémentant l'interface MoyenTransport et représentant le moyen 
 * de transport "prendre le bus". Cette classe est un singleton.
 */
public class PrendreBus implements MoyenTransport
{
	private static PrendreBus instance;
	
	/**
	 * Constructeur privé, voir getInstance().
	 */
	private PrendreBus()
	{
		
	}
	
	/**
	 * Méthode statique retournant l'unique instance de la classe. 
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
	 * Retourne le nom du véhicule associé au moyen de transport.
	 * @return la chaine "bus".
	 */
	public String getNomVehicule()
	{
		return "bus";
	}
	
	/**
	 * Retourne une représentation textuelle du moyen de transport
	 * sous la forme d'un verbe d'action.
	 * @return la chaine "Prendre le bus".
	 */
	public String toString()
	{
		return "Prendre le bus";
	}
}
