package simulateurdeplacement;

import java.util.ArrayList;

import usager.Usager;

public class SimulateurDeplacement 
{
	private ArrayList<Usager> usagers;
	private ArrayList<Simulable> objetsAFaireEvoluer;
	
	public SimulateurDeplacement(ArrayList<Usager> usagers, ArrayList<Simulable> objetsAFaireEvoluer)
	{
		this.usagers = usagers;
		this.objetsAFaireEvoluer = objetsAFaireEvoluer;
	}
	
	public void lancerSimulation()
	{
		boolean continuer = true;
		int t = 0;
		
		System.out.println(">> Début de la simulation pour les usagers "+usagers+" et les objets "+objetsAFaireEvoluer+".");
		
		while (continuer)
		{
			continuer = false;
			
			for (Simulable o : objetsAFaireEvoluer)
			{
				o.simulerEvolution(t);
			}
			
			for (Usager u : usagers)
			{
				u.simulerEvolution(t);
				continuer |= !u.estArrive();
			}
			
			t++;
		}
		
		System.out.println("<< Fin de la simulation t = "+t);
	}
}
