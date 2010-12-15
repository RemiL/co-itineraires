package simulateurdeplacement;

/**
 * Interface permettant de regroupant tous les objets dont l'évolution
 * peut être simulée.
 */
public interface Simulable
{
	/**
	 * Permet de simuler le passage d'une unité de temps.
	 * @param t le temps courant.
	 */
	public void simulerEvolution(int t);
}
