package moyenstransport;

public class MarcheAPied implements MoyenTransport
{
	private static MarcheAPied instance;
	
	private MarcheAPied()
	{
		
	}
	
    public static MarcheAPied getInstance()
    {
        if (instance == null) 
        { // Premier appel
            instance = new MarcheAPied();
        }
        
        return instance;
    }
    
    public String toString()
    {
    	return "Marcher";
    }
}
