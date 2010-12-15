package moyenstransport;
/**
 * Class MarcheAPied qui impl�mente MoyenTransport
 * repr�sente le moyen de transport � pied de l'usager
 * @author Marie
 *
 */
public class MarcheAPied implements MoyenTransport
{
	private static MarcheAPied instance;
	
	/**
	 * constructeur priv�
	 */
	private MarcheAPied()
	{
		
	}
	
	/**
	 * M�thode statique getInstance 
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
     * M�thode toString qui indique que l'on est dans une position de marche
     */
    public String toString()
    {
    	return "Marcher";
    }
}
