package moyenstransport;

/**
 * Classe impl�mentant l'interface MoyenTransport et repr�sentant le moyen 
 * de transport "marche � pied". Cette classe est un singleton.
 */
public class MarcheAPied implements MoyenTransport
{
	private static MarcheAPied instance;
	
	/**
	 * Constructeur priv�, voir getInstance().
	 */
	private MarcheAPied()
	{
		
	}
	
	/**
	 * M�thode statique retournant l'unique instance de la classe. 
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
	 * Retourne le nom du v�hicule associ� au moyen de transport.
	 * @return null car non applicable.
	 */
	public String getNomVehicule()
	{
		return null;
	}
	
	/**
	 * Retourne une repr�sentation textuelle du moyen de transport
	 * sous la forme d'un verbe d'action.
	 * @return la chaine "Marcher".
	 */
	public String toString()
	{
		return "Marcher";
	}
}
