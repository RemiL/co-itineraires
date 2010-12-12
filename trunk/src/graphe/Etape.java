package graphe;

import ligne.Ligne;
import moyenstransport.MoyenTransport;


public class Etape
{
	private Lieu lieuDepart;
	private Lieu lieuArrivee;
	private MoyenTransport moyenTransport;
	private Ligne ligne;
	private double cout;
	private int duree;
	
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne, double cout, int duree)
	{
		this.lieuDepart = lieuDepart;
		this.lieuArrivee = lieuArrivee;
		this.moyenTransport = moyenTransport;
		this.ligne = ligne;
		this.cout = cout;
		this.duree = duree;
		
		if (cout >= 0 && duree >= 0)
			lieuDepart.ajouterEtapeVersVoisin(this);
		if (ligne != null)
			ligne.ajouterTronçon(this);
	}
	
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree, boolean bidirectionnel)
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
		if (bidirectionnel)
			new Etape(lieuArrivee, lieuDepart, moyenTransport, duree);
	}
	
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, int duree)
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, 0, duree);
	}
	
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport, Ligne ligne)
	{
		this(lieuDepart, lieuArrivee, moyenTransport, ligne, -1, -1);
	}
	
	public Etape(Lieu lieuDepart, Lieu lieuArrivee, MoyenTransport moyenTransport)
	{
		this(lieuDepart, lieuArrivee, moyenTransport, null, -1, -1);
	}
	
	public Lieu getLieuDepart()
	{
		return lieuDepart;
	}
	
	public Lieu getLieuArrivee()
	{
		return lieuArrivee;
	}
	
	public MoyenTransport getMoyenTransport()
	{
		return moyenTransport;
	}
	
	public Ligne getLigne()
	{
		return ligne;
	}
	
	public double getCout()
	{
		return cout;
	}
	
	public int getDuree()
	{
		return duree;
	}
	
	public boolean setCout(double cout)
	{
		if (this.cout < 0)
		{
			this.cout = cout;
			return true;
		}
		else
			return false;
	}
	
	public boolean setDuree(int duree)
	{
		if (this.duree < 0)
		{
			this.duree = duree;
			return true;
		}
		else
			return false;
	}

	public boolean mettreAJourCoutEtDuree(Etape etape)
	{
		boolean etapeCompatible = false;
		
		if (this.ligne == etape.ligne && this.moyenTransport == etape.moyenTransport)
		{
			if (this.lieuArrivee == etape.lieuArrivee)
			{
				etapeCompatible = true;
				etape.cout = this.cout;
				etape.duree = this.duree;
			}
			else if (etape.ligne != null)
				etapeCompatible = ligne.mettreAJourCoutEtDuree(etape);
		}
		
		return etapeCompatible;
	}
	
	public String toString()
	{
		String str = moyenTransport.toString();
		
		if (ligne != null)
			str += " ("+ligne+")";
		
		return str+" de \""+getLieuDepart()+"\" jusqu'à \""+getLieuArrivee()+"\".";
	}
}
