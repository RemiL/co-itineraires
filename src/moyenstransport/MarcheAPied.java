package moyenstransport;

/**
 * Classe implémentant l'interface MoyenTransport et représentant le moyen 
 * de transport "marche à pied". Cette classe est un singleton.
 */
public class MarcheAPied implements MoyenTransport
{
	private static MarcheAPied instance;
	
	/**
	 * Constructeur privé, voir getInstance().
	 */
	private MarcheAPied()
	{
		
	}
	
	/**
	 * Méthode statique retournant l'unique instance de la classe. 
	 * @return l'instance unique de MarcheAPied.
	 */
	public static MarcheAPied getInstance()
	{
		if (instance == null) 
		{ // Premier appel
			instance = new MarcheAPied();
		}
		
		return instance;
	}
	
	/**
	 * Retourne le nom du véhicule associé au moyen de transport.
	 * @return null car non applicable.
	 */
	public String getNomVehicule()
	{
		return null;
	}
	
	/**
	 * Retourne une représentation textuelle du moyen de transport
	 * sous la forme d'un verbe d'action.
	 * @return la chaine "Marcher".
	 */
	public String toString()
	{
		return "Marcher";
	}
}
