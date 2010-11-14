package ligne;

import java.util.ArrayList;
import java.util.Hashtable;

import lieu.ArretBus;
import lieu.Lieu;
import lieu.LieuAccessibleEnTransport;

public class LigneBus extends Ligne
{
	private ArrayList<ArretBus> arrets;
	private Hashtable<ArretBus, Integer> tempsTrajet;
	private ArrayList<ArretBus> positionsBus;
	private double tarif;
	
	public LigneBus(String nom, double tarif)
	{
		super(nom);
		this.tarif = tarif;
		arrets = new ArrayList<ArretBus>();
		tempsTrajet = new Hashtable<ArretBus, Integer>();
		positionsBus = new ArrayList<ArretBus>();
	}
	
	public void ajouterArret(ArretBus arret, int temps)
	{
		arrets.add(arret);
		
		tempsTrajet.put(arret, temps);
	}

	public boolean contientArrets(Lieu depart, Lieu arrivee)
	{
		return (arrets.contains(depart) && arrets.contains(arrivee));
	}
	
	public double calculCoutTrajet(LieuAccessibleEnTransport depart, LieuAccessibleEnTransport arrivee) 
	{
		return tarif;
	}
}
