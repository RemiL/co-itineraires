package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe implémentant l'interface Contrainte et représentant une contrainte 
 * composée d'une ou plusieurs contraintes élémentaires. Pour obtenir les
 * meilleurs trajets selon la contrainte composée, on applique d'abord la
 * première sous-contrainte PUIS la seconde et ainsi de suite.
 */
public class ContrainteComposee implements Contrainte
{
	/** La liste des sous-contraintes */
	private ArrayList<ContrainteElementaire> contraintes;
	
	/**
	 * Construit une contrainte composée vide
	 */
	public ContrainteComposee()
	{
		contraintes = new ArrayList<ContrainteElementaire>();
	}
	
	/**
	 * Permet d'ajouter une contraite élémentaire à la contrainte composée.
	 * @param contrainte la contraite à ajouter.
	 */
	public void ajouterContrainte(ContrainteElementaire contrainte)
	{
		contraintes.add(contrainte);
	}

	/**
	 * Permet d'évaluer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (éventuellement vide) des meilleurs trajets au sens de la contrainte.
	 */
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats)
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>(trajetsCandidats);
		
		for (Contrainte c : contraintes) // A chaque application d'une contrainte,
			meilleursTrajets = c.evaluerTrajets(meilleursTrajets); // le résultat devient le candidat de la suivante.
		
		return meilleursTrajets;
	}
}
