package ligne;

/**
 * Repr�sente une exception pouvant �tre lev�e lors de la construction d'une ligne.
 * Le message pr�cise la nature de l'exception rencontr�e, elle peut �tre de deux
 * types principaux :
 * - le tron�on ajout� � la ligne ne commence pas l� o� fini le dernier tron�on de la
 *   ligne.
 * - le tron�on ajout� � la ligne n'utilise pas le m�me moyen de transport que la ligne.
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
