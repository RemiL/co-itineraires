package usager;

import lieu.Lieu;
import trajet.Trajet;

public class Usager
{
	private String nom;
	private Trajet trajet;
	private Lieu positionActuelle;
	
	public Usager(String nom)
	{
		this.nom = nom;
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public Trajet getTrajet()
	{
		return trajet;
	}

	public void setTrajet(Trajet trajet)
	{
		this.trajet = trajet;
	}

	public Lieu getPositionActuelle()
	{
		return positionActuelle;
	}
}
