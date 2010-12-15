package trajet;

import graphe.Etape;

/**
 * Exception héritant de TrajetException représentant une exception
 * levée lorsque l'ajout d'une étape rend le trajet non cohérent.
 * C'est-à-dire quand on essaie de faire s'enchainer une étape qui
 * se termine en un certain lieu avec une étape qui commence en un
 * autre lieu.
 */
public class TrajetNonCoherentException extends TrajetException
{
	private static final long serialVersionUID = 8470833671943488438L;
	/**	La dernière étape du trajet avant l'ajout */
	private Etape derniereEtape;
	/**	L'étape qu'on a voulu ajouter */
	private Etape nouvelleEtape;
	
	/**
	 * Construit une exception TrajetNonCoherentException suite à l'ajout
	 * de nouvelleEtape à la suite d'un trajet finissant par dernièreEtape.
	 * @param derniereEtape la dernière étape du trajet qui a provoqué l'exception.
	 * @param nouvelleEtape l'étape non compatible que l'on a voulu ajouter.
	 */
	public TrajetNonCoherentException(Etape derniereEtape, Etape nouvelleEtape)
	{
		super();
		this.derniereEtape = derniereEtape;
		this.nouvelleEtape = nouvelleEtape;
	}
	
	/**
	 * Retourne un message décrivant les étapes ayant provoquées l'enchainement non cohérent.
	 * @return un message d'erreur décrivant les étapes ayant provoquées l'enchainement non cohérent.
	 */
	public String getMessage()
	{
		return "Trajet non cohérant, l'étape \""+nouvelleEtape+"\" ne peut pas suivre l'étape \""+derniereEtape+"\".";
	}
}
