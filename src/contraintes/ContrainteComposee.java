package contraintes;

import java.util.ArrayList;

import trajet.Trajet;

/**
 * Classe impl�mentant l'interface Contrainte et repr�sentant une contrainte 
 * compos�e d'une ou plusieurs contraintes �l�mentaires. Pour obtenir les
 * meilleurs trajets selon la contrainte compos�e, on applique d'abord la
 * premi�re sous-contrainte PUIS la seconde et ainsi de suite.
 */
public class ContrainteComposee implements Contrainte
{
	/** La liste des sous-contraintes */
	private ArrayList<ContrainteElementaire> contraintes;
	
	/**
	 * Construit une contrainte compos�e vide
	 */
	public ContrainteComposee()
	{
		contraintes = new ArrayList<ContrainteElementaire>();
	}
	
	/**
	 * Permet d'ajouter une contraite �l�mentaire � la contrainte compos�e.
	 * @param contrainte la contraite � ajouter.
	 */
	public void ajouterContrainte(ContrainteElementaire contrainte)
	{
		contraintes.add(contrainte);
	}

	/**
	 * Permet d'�valuer la liste de trajets fournie au sens de la contrainte.
	 * @param la liste des trajets candidats.
	 * @return la liste (�ventuellement vide) des meilleurs trajets au sens de la contrainte.
	 */
	public ArrayList<Trajet> evaluerTrajets(ArrayList<Trajet> trajetsCandidats)
	{
		ArrayList<Trajet> meilleursTrajets = new ArrayList<Trajet>(trajetsCandidats);
		
		for (Contrainte c : contraintes) // A chaque application d'une contrainte,
			meilleursTrajets = c.evaluerTrajets(meilleursTrajets); // le r�sultat devient le candidat de la suivante.
		
		return meilleursTrajets;
	}
}
