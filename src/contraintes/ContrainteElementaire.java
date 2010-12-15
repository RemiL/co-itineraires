package contraintes;

/**
 * Classe abstraite implémentant l'interface Contraite et servant
 * à regrouper toutes les contraintes élémentaires.
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
