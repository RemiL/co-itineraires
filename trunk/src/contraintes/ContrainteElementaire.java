package contraintes;

/**
 * Classe abstraite impl�mentant l'interface Contraite et servant
 * � regrouper toutes les contraintes �l�mentaires.
 */
public abstract class ContrainteElementaire implements Contrainte
{
	public ContrainteElementaire()
	{
		
	}
	
	public String toString()
	{
		return "Contrainte";
	}
}
