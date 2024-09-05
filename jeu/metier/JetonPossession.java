package jeu.metier;

/**
 * Cette classe permet d'attribuer des jetons à un joueur.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class JetonPossession
{
	private Joueur joueur ;

	private int x;
	private int y;

	/**
	 * Constructeur de JetonPossession.
	 * @param joueur le joueur possedant le jeton
	 */
	public JetonPossession (Joueur joueur)
	{
		this.joueur=joueur;
	}

	/**
	 * Méthode qui modifie l'abscisse du jeton
	 * @param x l'abcisse du jeton
	 */
	public void setX (int x) {this.x = x;}

	/**
	 * Méthode qui modifie y l'ordonnée du jeton
	 * @param y l'ordonnée du jeton
	 */
	public void setY (int y) {this.y = y;}

	/**
	 * Renvoie le Joueur en possession de ce jeton
	 * @return le Joueur en possession de ce jeton
	 */
	public Joueur getJoueur() {return this.joueur ;}

	/**
	 * Renvoie l'abscisse du jeton
	 * @return l'abcisse du jeton
	 */
	public int getX() {return this.x ;}

	/**
	 * Renvoie l'ordonnée du jeton
	 * @return l'ordonnée du jeton
	 */
	public int getY() {return this.y; }
}