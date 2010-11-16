package lieu;

/** 
 * Un lieu abstrait accessible en transport.
 * @author Nicolas
 */
public abstract class LieuAccessibleEnTransport extends Lieu
{
	/**
	 * Construit un LieuAccessibleEnTransportavec un nom.
	 * @param nom le nom du lieu.
	 */
	public LieuAccessibleEnTransport(String nom)
	{
		super(nom);
	}
	
	/**
	 * Teste si un lieu est accessible en transport depuis le lieu courant.
	 * @param lieu le lieu à tester.
	 * @return true si le lieu est accessible en transport depuis le lieu courant, false sinon.
	 */
	public abstract boolean estAccessibleEnTransport(Lieu lieu);
}
