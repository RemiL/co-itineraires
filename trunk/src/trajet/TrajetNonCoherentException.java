package trajet;

import graphe.Etape;

public class TrajetNonCoherentException extends TrajetException
{
	private static final long serialVersionUID = 8470833671943488438L;
	private Etape derniereEtape;
	private Etape nouvelleEtape;
	
	public TrajetNonCoherentException(Etape derniereEtape, Etape nouvelleEtape)
	{
		super();
		this.derniereEtape = derniereEtape;
		this.nouvelleEtape = nouvelleEtape;
	}
}
