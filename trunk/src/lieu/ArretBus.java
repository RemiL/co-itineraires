package lieu;

import java.util.ArrayList;

import ligne.LigneBus;

public class ArretBus extends LieuAccessibleEnTransport
{
	private ArrayList<LigneBus> lignes;
	
	public ArretBus(String nom)
	{
		super(nom);
		lignes = new ArrayList<LigneBus>();
	}
	
	public void ajouterLigne(LigneBus ligne)
	{
		lignes.add(ligne);
	}

	public boolean estAccessibleEnTransport(Lieu lieu)
	{
		for(LigneBus ligne : lignes)
		{
			if(ligne.contientArrets(this, lieu))
				return true;
		}
			
		return false;
	}
}
