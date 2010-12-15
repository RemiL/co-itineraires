package moyenstransport;

/**
 * Interface MoyenTransport regroupant tous les moyens de transport.
 */
public interface MoyenTransport
{
	/**
	 * Retourne le nom du v�hicule associ� au moyen de transport.
	 * @return une chaine de caract�res ou null si non applicable.
	 */
	public String getNomVehicule();
}