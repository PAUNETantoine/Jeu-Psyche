package jeu.metier;

import jeu.Controleur;

import java.util.ArrayList;

/**
 * Cette classe d'instansié les mines
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Sommet
{

    private int     numSom;
    private String  nomCoul;
    private boolean depart;
	private Joueur  joueur;

	private int id;
    private int x;
    private int y;

	private ArrayList<Route> routes;


    private Materiaux materiaux;

    /**
	 * Constructeur de Sommet
	 * @param numSom      numéro du sommet
	 * @param nomCoul     nom de la couleur du sommet
	 * @param x           postion x
	 * @param y           postion y
	 * @param materiaux   matérieux assignée au sommet
	 * @param estDepart   si ce sommet correspond au sommet de départ du jeu
	 * @param joueur      correpond au joueur qui déteins le sommet 
	 * 
	 */
    
    public Sommet( int numSom, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart, Joueur joueur )
    {
		this.id = Controleur.nbSommets++;
        this.numSom     = numSom;
        this.nomCoul    = nomCoul;
        this.materiaux  = materiaux;
        this.x          = x;
        this.y          = y;
        this.depart     = estDepart;
		this.joueur     = joueur;
		this.routes = new ArrayList<>(10);
    }


	public Sommet( int numSom, String nomCoul, int x, int y, Materiaux materiaux, boolean estDepart, Joueur joueur, int id )
	{
		this.id = id;
		this.numSom     = numSom;
		this.nomCoul    = nomCoul;
		this.materiaux  = materiaux;
		this.x          = x;
		this.y          = y;
		this.depart     = estDepart;
		this.joueur     = joueur;
		this.routes = new ArrayList<>(10);
	}


    public int       getNumSom()         { return this.numSom;    }
    public String    getNomCoul()        { return this.nomCoul;   }
    public int       getX()              { return this.x;         }
    public int       getY()              { return this.y;         }
    public int       setX( int x )       { return this.x = x;     }
    public int       setY( int y )       { return this.y = y;     }
    public Materiaux getMateriaux()      { return this.materiaux; }
    public boolean   getDepart()         { return this.depart;    }
	public Joueur    getJoueur()         { return this.joueur;    }
	public void      setJoueur(Joueur j) { this.joueur = j;       }
	public int       getId()             { return this.id;        }
 

	public void addRoute(Route r)
	{
		this.routes.add(r);
	}

    /**
	 * @param  i numéro de la route
	 * @return retourne la route
	 * 
	 */
	public Route getRoute(int i)
	{
		return this.routes.get(i);
	}

    /**
	 * 
	 * @return retourne la tableau de routes
	 * 
	 */
	public ArrayList<Route> getTabRoute()
	{
		return this.routes;
	}

	public boolean  possede ( int x, int y )
	{

		if ( x <= this.x + 20  && x >= this.x-20 && y <= this.y + 20  && y >= this.y-20 )
			return true;

		return false;
    }

	public boolean equals(Sommet som)
	{
		return this.id  == som.id ;
	}

			

     /**
	 * 
	 * @return retourne le matérieau prit
	 * 
	 */
	public Materiaux prendreMateriaux ()
	{
		return this.materiaux;
	}

    public String    toString()     { return "Num : " + this.numSom + " Coul : " + this.nomCoul + " Depart " + this.depart + " x : " + this.x + " y : " + this.y + " Matériau : " + this.materiaux + "\n"; }

}