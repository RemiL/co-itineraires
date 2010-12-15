package contraintes;

import java.util.ArrayList;

import moyenstransport.MoyenTransport;

import trajet.Trajet;

/**
 * Classe impl�mentant une contrainte �l�mentaire visant � s�lectionner uniquement
 * les trajets n'utilisant pas un certain moyen de transport.
 */
public class EviterMoyenTransport extends ContrainteElementaire
{
	/** Le moyen de transport � �viter */
	private MoyenTransport moyenTransportAEviter;
	
	/**
	 * Construit une contrainte visant � �viter d'utiliser le moyen
	 * de transport fourni en param�tre.
	 * @param moyenTransportAEviter le moyen de transport � �viter.
	 */
	public EviterMoyenTransport(MoyenTransport moyenTransportAEviter)
	{
		this.moyenTransportAEviter = moyenTransportAEviter;
	}
	
	/**
	 * Permet d'�valuer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (�ventuellement vide) des meilleurs trajets au sens de la contrainte.
	 */
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats) 
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>();
		
		for (Trajet trajet : trajetsCandidats)
		{
			if (!trajet.utilise(moyenTransportAEviter))
			{
				meilleursTrajets.add(trajet);
			}
		}
		
		return meilleursTrajets;
	}
	
	/**
	 * Retourne une repr�sentation textuelle de la contrainte.
	 * @return une chaine de caract�res repr�sentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Eviter le moyen de transport \""+moyenTransportAEviter+"\"";
	}
}
