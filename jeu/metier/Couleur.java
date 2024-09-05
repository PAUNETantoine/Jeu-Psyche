package jeu.metier;
import java.awt.*;

/**
 * Cette classe permet d'insantcier les couleurs dont nous avons besoin.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public enum Couleur
{
	DEPART ( 100, 100, 255 ),
	JAUNE  ( 255, 255, 0   ),
	MARRON ( 165, 42 , 42  ),
	VIOLET ( 255, 255, 255 ),
	VERT   ( 0  , 255, 0   ),
	BLEU   ( 0  , 0  , 255 ),
	CIAN   ( 0  , 255, 255 ),
	GRIS   ( 192, 192, 192 ),
	OR	   ( 255, 215, 0   ),
	PALE   ( 250, 250, 210 );

	private int r, v, b;

	/**
	 * Crée un objet de la classe Couleur avec les paramètres r v b
	 * @param r la part de rouge dans la Couleur.
	 * @param v la part de vert  dans la Couleur.
	 * @param b la part de bleu  dans la Couleur.
	 */
	private Couleur( int r , int v , int b )
	{
		this.r = r;
		this.v = v;
		this.b = b;
	}


	/**
	 * Donne la couleur qui correspond à l'instance qui utilise cette méthode
	 * @return un objet de la class Couleur correspondant à l'instance.
	 */
	public Color getColor()
	{
		return new Color( this.r, this.v, this.b );
	}

	/**
	 * Donne le nombre d'instance de Couleur
	 * @return l'entier correpondant au nombre d'instance de Couleur.
	 */
	public static int getNbCouleur()
	{
		return Couleur.values().length;
	}

	/**
	 * Donne une couleur selon un entier
	 * @return un objet de la classe Couleur.
	 */
	public static Couleur valueOf( int ordinal )
	{
		return Couleur.valueOf(ordinal);
	}
}