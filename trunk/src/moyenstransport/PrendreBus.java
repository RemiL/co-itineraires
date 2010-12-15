package moyenstransport;
/**
 * Class PrendreBus qui impl�mente MoyenTransport
 * repr�sente le moyen de transport du bus
 * @author Marie
 *
 */
public class PrendreBus implements MoyenTransport
{
	private static PrendreBus instance;
	
	/**
	 * constructeur prive PrendreBus
	 */
	private PrendreBus()
	{
		
	}
	
	/**
	 * m�thode getInstance 
	 * @return instance une instance de PrendreBus
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
     * m�thode toString qui indique que l'on est en mode bus
     */
    public String toString()
    {
    	return "Prendre le bus";
    }
}
