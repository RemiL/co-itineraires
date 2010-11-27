package trajet;

import lieu.ArretBus;

public class EtapeEnBus implements Etape
{
	private ArretBus lieuDepart;
	private ArretBus lieuArrivee;
	
	public EtapeEnBus(ArretBus lieuDepart, ArretBus lieuArrivee)
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
	}
}
