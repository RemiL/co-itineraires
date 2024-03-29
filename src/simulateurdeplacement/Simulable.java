package simulateurdeplacement;

/**
 * Interface permettant de regroupant tous les objets dont l'�volution
 * peut �tre simul�e.
 */
public interface Simulable
{
	/**
	 * Permet de simuler le passage d'une unit� de temps.
	 * @param t le temps courant.
	 * @throws SimulationImpossibleException si la simulation est impossible
	 */
	public void simulerEvolution(int t) throws SimulationImpossibleException;
}
