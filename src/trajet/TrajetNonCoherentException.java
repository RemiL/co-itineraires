package trajet;

import graphe.Etape;

/**
 * Exception h�ritant de TrajetException repr�sentant une exception
 * lev�e lorsque l'ajout d'une �tape rend le trajet non coh�rent.
 * C'est-�-dire quand on essaie de faire s'enchainer une �tape qui
 * se termine en un certain lieu avec une �tape qui commence en un
 * autre lieu.
 */
public class TrajetNonCoherentException extends TrajetException
{
	private static final long serialVersionUID = 8470833671943488438L;
	/**	La derni�re �tape du trajet avant l'ajout */
	private Etape derniereEtape;
	/**	L'�tape qu'on a voulu ajouter */
	private Etape nouvelleEtape;
	
	/**
	 * Construit une exception TrajetNonCoherentException suite � l'ajout
	 * de nouvelleEtape � la suite d'un trajet finissant par derni�reEtape.
	 * @param derniereEtape la derni�re �tape du trajet qui a provoqu� l'exception.
	 * @param nouvelleEtape l'�tape non compatible que l'on a voulu ajouter.
	 */
	public TrajetNonCoherentException(Etape derniereEtape, Etape nouvelleEtape)
	{
		super();
		this.derniereEtape = derniereEtape;
		this.nouvelleEtape = nouvelleEtape;
	}
	
	/**
	 * Retourne un message d�crivant les �tapes ayant provoqu�es l'enchainement non coh�rent.
	 * @return un message d'erreur d�crivant les �tapes ayant provoqu�es l'enchainement non coh�rent.
	 */
	public String getMessage()
	{
		return "Trajet non coh�rant, l'�tape \""+nouvelleEtape+"\" ne peut pas suivre l'�tape \""+derniereEtape+"\".";
	}
}
