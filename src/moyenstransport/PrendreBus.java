package moyenstransport;

public class PrendreBus implements MoyenTransport
{
	private static PrendreBus instance;
	
	private PrendreBus()
	{
		
	}
	
    public static PrendreBus getInstance()
    {
        if (instance == null) 
        { // Premier appel
            instance = new PrendreBus();
        }
        
        return instance;
    }
    
    public String toString()
    {
    	return "Prendre le bus";
    }
}
