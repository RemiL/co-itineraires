package simulateurdeplacement;

import java.util.ArrayList;

import usager.Usager;

/**
 * Classe permettant de g�rer la simulation en invoquant r�guli�rement les
 * objets simulables du syst�me. L'unit� de temps correspond � un top d'horloge
 * vituelle.
 */
public class SimulateurDeplacement 
{
	/** Les usagers dont le d�placement doit �tre simul� */
	private ArrayList<Usager> usagers;
	/** Tous autres objets simulables dont l'�volution doit �tre simul�e */
	private ArrayList<Simulable> objetsAFaireEvoluer;
	
	/**
	 * Construit un simulateur de d�placement pour la liste d'usagers
	 * et la liste d'objets simulables fournies.
	 * @param usagers les usagers dont le d�placement doit �tre simul�.
	 * @param objetsAFaireEvoluer les objets dont l'�volution doit �tre simul�e.
	 */
	public SimulateurDeplacement(ArrayList<Usager> usagers, ArrayList<Simulable> objetsAFaireEvoluer)
	{
		this.usagers = usagers;
		this.objetsAFaireEvoluer = objetsAFaireEvoluer;
	}
	
	/**
	 * Permet de lancer la simulation. La simulation est effectu�e aussi rapidement que possible
	 * et fonction de la puissance de l'ordinateur sur laquelle elle est effectu�e. L'unit� de temps
	 * correspond � un top d'horloge vituelle. La simulation commence toujours � t=0 et se termine
	 * lorsque tous les utilisateurs sont arriv�es.
	 */
	public void lancerSimulation()
	{
		boolean continuer = true;
		int t = 0;
		
		System.out.println(">> D�but de la simulation pour les usagers "+usagers+" et les objets "+objetsAFaireEvoluer+".");
		
		while (continuer) // Tant que des utiliseurs ne sont pas arriv�s
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
