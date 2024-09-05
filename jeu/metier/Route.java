package jeu.metier;

/**
 * Cette classe d'instansié des routes. Chaque route est composée d'un sommet de départ, un sommet d'arrivée et d'un nombre de troncons.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Route
{
	private Sommet sommetDep;
	private Sommet sommetArr;
	private int  troncons;

	private Joueur joueur;

	/**
	 * Constructeur de Route
	 * @param sommetDep  mine de départ
	 * @param mineArrive  mine d'arrive
	 * @param mineSection nombre de section
	 */

	public Route ( Sommet sommetDep, Sommet sommetArrr, int troncons )
	{
		this.sommetDep  = sommetDep;
		this.troncons = troncons;
		this.sommetArr=sommetArrr;
		this.sommetDep.addRoute(this);
		this.sommetArr.addRoute(this);
		this.joueur=null;
	}

	/**
	 * Renvoie le sommet de départ de la Route.
	 * @return le sommet de départ de la Route
	 */
	public Sommet getSommetDep  () { return this.sommetDep;}

	/**
	 * Renvoie le sommet d'arrivée de la Route.
	 * @return le sommet d'arrivée de la Route
	 */
	public Sommet getSommetArr() { return this.sommetArr;  }

	/**
	 * Renvoie le nombre de troncons de la Route.
	 * @return le nombre de troncons de la Route
	 */
	public int  getNbTroncons () { return this.troncons;}

	/**
	 * Renvoie le Joueur en possession de la Route.
	 * @return le Joueur en possession de la Route
	 */
	public Joueur getJoueur() {return this.joueur;}

	/**
	 * Méthode qui permet de comparer deux Routes.
	 * @return vrai si les deux Routes sont identiques, faux si non.
	 */
	public boolean equals(Route rt ) 
	{
		return this.sommetDep == rt.sommetDep && this.sommetArr == rt.sommetArr && this.troncons == rt.troncons ;
	}

	/**
	 * Méthode qui permet de modifier le sommet de départ d'une Route.
	 * @param sommetDep le nouveau sommet de départ
	 */
	public void setSommetDep ( Sommet sommetDep ) { this.sommetDep = sommetDep; }

	/**
	 * Méthode qui permet de modifier le sommet de départ d'une Route.
	 * @param sommetArr le nouveau sommet de départ
	 */
	public void setsommetArr ( Sommet sommetArr ) { this.sommetArr = sommetArr; }

	/**
	 * Méthode qui permet de modifier le sommet de départ d'une Route.
	 * @param sommetDep le nouveau sommet de départ
	 */
	public void setNbTroncons ( int    troncons  ) { this.troncons  = troncons;  }

	/**
	 * Méthode qui permet de modifier le Joueur en possesion de cette Route.
	 * @param j le nouveau Joueur en possesion de cette Route
	 */
	public void setJoueur (Joueur j)
	{
		this.joueur = j;
	}

	/**
	 * Renvoie les informations de la Route sous forme texte.
	 * @return le sommet de départ, le sommet d'arrivée, le nombre de troncons et le joueur en le possession de la Route.
	 */
	public String toString()
	{
		if(this.joueur != null)
			return "\n"+this.sommetDep+"\n--------\n"+this.sommetArr+"    ("+this.troncons+" )"+ "   p:"+this.joueur.getNomJoueur()+"\n";
		else
			return "\n"+this.sommetDep+"\n--------\n"+this.sommetArr+"    ("+this.troncons+" )"+ "   p: Aucun joueur" + "\n";
	}
}