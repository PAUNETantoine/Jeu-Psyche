package jeu.metier;

/**
 * Cette classe permet de definr quel instance de jeton nous manipulon
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Jeton
{
	private IRessource type;

	/**
	 * Constructeur de Jeton.
	 * @param type le type du Jeton
	 */
	public Jeton(IRessource type)
	{
		this.type = type;
	}

	/**
	 * Retourne le type du Jeton
	 * @return le type du Jeton
	 */
	public IRessource getType()
	{
		return this.type;
	}

	/**
	 * Renvoie sous forme texte les informations sur le jeton
	 * @return les informations du jeton.
	 */
	public String toString()
	{
		String sRet = "";

		sRet = this.type.toString();
		return sRet;
	}
}