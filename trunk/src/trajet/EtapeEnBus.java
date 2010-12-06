package trajet;

import lieu.ArretBus;
import lieu.Lieu;
import ligne.LigneBus;

public class EtapeEnBus implements Etape
{
	private ArretBus lieuDepart;
	private ArretBus lieuArrivee;
	private LigneBus ligne;
	
	public EtapeEnBus(ArretBus lieuDepart, ArretBus lieuArrivee, LigneBus ligne)
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
		this.ligne = ligne;
	}

	public Lieu getLieuDepart()
	{
		return lieuDepart;
	}
	
	public Lieu getLieuArrivee()
	{
		return lieuArrivee;
	}
}
