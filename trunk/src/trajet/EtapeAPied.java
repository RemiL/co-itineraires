package trajet;

import lieu.Lieu;

public class EtapeAPied implements Etape
{
	private Lieu lieuDepart;
	private Lieu lieuArrivee;
	
	public EtapeAPied(Lieu lieuDepart, Lieu lieuArrivee)
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
	}
}
