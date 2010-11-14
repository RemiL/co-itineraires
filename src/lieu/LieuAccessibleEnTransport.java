package lieu;

public abstract class LieuAccessibleEnTransport extends Lieu
{
	public LieuAccessibleEnTransport(String nom)
	{
		super(nom);
	}
	
	public abstract boolean estAccessibleEnTransport(Lieu lieu);
}
