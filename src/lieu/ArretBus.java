package lieu;

import java.util.ArrayList;

import ligne.LigneBus;

/**
 * Un arrêt de bus pouvant faire partie d'une ligne de bus.
 * @author Nicolas
 */
public class ArretBus extends LieuAccessibleEnTransport
{
	/**
	 * La liste des lignes auxquelles l'arrêt appartient.
	 * On peut ajouter des lignes de bus.
	 * @see ArretBus#ajouterLigne(LigneBus ligne)
	 */
	private ArrayList<LigneBus> lignes;
	
	/**
	 * Construit un arrêt de bus avec un nom et n'appartenant à aucune ligne.
	 * @param nom le nom de la ligne.
	 */
	public ArretBus(String nom)
	{
		super(nom);
		lignes = new ArrayList<LigneBus>();
	}
	
	/**
	 * 
	 * @param ligne la nouvelle ligne de bus à laquelle appartient l'arrêt de bus.
	 */
	public void ajouterLigne(LigneBus ligne)
	{
		lignes.add(ligne);
	}

	/**
	 * Teste si un lieu est accessible en bus depuis l'arret de bus courant.
	 * @param lieu le lieu à tester.
	 * @return true si le lieu est accessible en bus par une ligne passant par l'arrêt de bus courant, false sinon.
	 */
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
