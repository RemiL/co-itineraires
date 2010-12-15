package simulateurdeplacement;

import java.util.ArrayList;

import usager.Usager;

/**
 * Classe permettant de gérer la simulation en invoquant régulièrement les
 * objets simulables du système. L'unité de temps correspond à un top d'horloge
 * vituelle.
 */
public class SimulateurDeplacement 
{
	/** Les usagers dont le déplacement doit être simulé */
	private ArrayList<Usager> usagers;
	/** Tous autres objets simulables dont l'évolution doit être simulée */
	private ArrayList<Simulable> objetsAFaireEvoluer;
	
	/**
	 * Construit un simulateur de déplacement pour la liste d'usagers
	 * et la liste d'objets simulables fournies.
	 * @param usagers les usagers dont le déplacement doit être simulé.
	 * @param objetsAFaireEvoluer les objets dont l'évolution doit être simulée.
	 */
	public SimulateurDeplacement(ArrayList<Usager> usagers, ArrayList<Simulable> objetsAFaireEvoluer)
	{
		this.usagers = usagers;
		this.objetsAFaireEvoluer = objetsAFaireEvoluer;
	}
	
	/**
	 * Permet de lancer la simulation. La simulation est effectuée aussi rapidement que possible
	 * et fonction de la puissance de l'ordinateur sur laquelle elle est effectuée. L'unité de temps
	 * correspond à un top d'horloge vituelle. La simulation commence toujours à t=0 et se termine
	 * lorsque tous les utilisateurs sont arrivées.
	 */
	public void lancerSimulation()
	{
		boolean continuer = true;
		int t = 0;
		
		System.out.println(">> Début de la simulation pour les usagers "+usagers+" et les objets "+objetsAFaireEvoluer+".");
		
		while (continuer) // Tant que des utiliseurs ne sont pas arrivés
		{
			continuer = false;
			
			for (Simulable o : objetsAFaireEvoluer)
			{
				o.simulerEvolution(t);
			}
			
			for (Usager u : usagers)
			{
				u.simulerEvolution(t);
				continuer |= !u.estArrive(); // restent-ils des utiliseurs en cours de trajet ?
			}
			
			t++; // On passe au top d'horloge suivant.
		}
		
		System.out.println("<< Fin de la simulation t = "+t);
	}
}
