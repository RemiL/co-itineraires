package ligne;

/**
 * Représente une exception pouvant être levée lors de la construction d'une ligne.
 * Le message précise la nature de l'exception rencontrée, elle peut être de deux
 * types principaux :
 * - le tronçon ajouté à la ligne ne commence pas là où fini le dernier tronçon de la
 *   ligne.
 * - le tronçon ajouté à la ligne n'utilise pas le même moyen de transport que la ligne.
 */
public class LigneException extends Exception
{
	private static final long serialVersionUID = -6715885409432152035L;
	
	/**
	 * constructeur TrajetNonCoherentException
	 * @param derniereEtape
	 * @param nouvelleEtape
	 */
	public LigneException(String msg)
	{
		super(msg);
	}
}
