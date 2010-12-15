package moyenstransport;

/**
 * Interface MoyenTransport regroupant tous les moyens de transport.
 */
public interface MoyenTransport
{
	/**
	 * Retourne le nom du véhicule associé au moyen de transport.
	 * @return une chaine de caractères ou null si non applicable.
	 */
	public String getNomVehicule();
}