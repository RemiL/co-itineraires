package contraintes;

import java.util.ArrayList;

import moyenstransport.MoyenTransport;

import trajet.Trajet;

/**
 * Classe implémentant une contrainte élémentaire visant à sélectionner uniquement
 * les trajets n'utilisant pas un certain moyen de transport.
 */
public class EviterMoyenTransport extends ContrainteElementaire
{
	/** Le moyen de transport à éviter */
	private MoyenTransport moyenTransportAEviter;
	
	/**
	 * Construit une contrainte visant à éviter d'utiliser le moyen
	 * de transport fourni en paramètre.
	 * @param moyenTransportAEviter le moyen de transport à éviter.
	 */
	public EviterMoyenTransport(MoyenTransport moyenTransportAEviter)
	{
		this.moyenTransportAEviter = moyenTransportAEviter;
	}
	
	/**
	 * Permet d'évaluer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (éventuellement vide) des meilleurs trajets au sens de la contrainte.
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
	 * Retourne une représentation textuelle de la contrainte.
	 * @return une chaine de caractères représentant la contrainte.
	 */
	public String toString()
	{
		return super.toString()+" : Eviter le moyen de transport \""+moyenTransportAEviter+"\"";
	}
}
