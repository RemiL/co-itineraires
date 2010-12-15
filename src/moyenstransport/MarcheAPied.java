package moyenstransport;
/**
 * Class MarcheAPied qui implémente MoyenTransport
 * représente le moyen de transport à pied de l'usager
 * @author Marie
 *
 */
public class MarcheAPied implements MoyenTransport
{
	private static MarcheAPied instance;
	
	/**
	 * constructeur privé
	 */
	private MarcheAPied()
	{
		
	}
	
	/**
	 * Méthode statique getInstance 
	 * @return instance une instance de MarcheAPied
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
     * Méthode toString qui indique que l'on est dans une position de marche
     */
    public String toString()
    {
    	return "Marcher";
    }
}
