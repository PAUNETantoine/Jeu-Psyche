package jeu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import jeu.ihm.*;
import jeu.metier.*;

/**
 * Cette classe créé l'interface graphique gérée par le controleur.
 * Elle s'occupe de charger des éléments sur la page graphique
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class Controleur 
{
	private          int                nbEtapeScenario;
	private          int                nbScenario;
	private          Joueur             j1       ;
	private          Joueur             j2       ;
	protected        ArrayList<Sommet>  tabSommet;
	protected        ArrayList<Route>	 tabRoute ;

	private          boolean            estSauvegarde ;
	private          boolean            estScenar;
	private          boolean            estJeu   ;
	private          boolean            tourJ1        ;
	private          boolean            finPartie     ;
	public           FrameDemarrage     frameDemarrage;

	private EditionFichier editionFichier;
	private String[]       elementsTheme ;

	private ArrayList<String> lstMateriaux;
	public static int nbSommets = 2;
	private boolean premierLance;

	private Route routeDepart;

	/**
	 * Constructeur du Controleur
	 */
	public Controleur() throws IOException
	{
		this.j1      		= new Joueur ( this );
		this.j2      		= new Joueur ( this );
		this.editionFichier = new EditionFichier(this);
		this.tabSommet 		= this.editionFichier.getTabSommet();
		this.tabRoute		= this.editionFichier.getTabRoute();
		this.tourJ1         = true;
		this.finPartie      = false;
		this.estJeu         = false;
		this.nbEtapeScenario= 1;
		this.nbScenario     = 0;	
		this.estScenar      = false;
		this.premierLance 	= true;
		this.routeDepart 	= null;
		
		
		this.lstMateriaux = new ArrayList<>(40);
		this.initMateriaux();

		this.frameDemarrage = new FrameDemarrage(this);

		// Affectation de estSauvegarde
		if( this.editionFichier.lectureFichier("data.txt", false) )
			this.estSauvegarde = false;
		else
			this.estSauvegarde = true;

		this.initJetonPossession();
	}

	private void initMateriaux()
	{
		this.lstMateriaux = new ArrayList<>(Arrays.asList("NR", "NR", "NR", "NR", "NR", "NR", "NR", "NR"));
		String[] tabNomsMat = new String[]{ "FE", "AL", "AU", "TI", "AG", "CO", "NI", "PT" };


		//génération du tableau contenant les noms des matériaux possibles
		for(int i = 0 ; i < tabNomsMat.length ; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				this.lstMateriaux.add(tabNomsMat[i]);
			}
		}
	}

	public boolean getPremierLancer()
	{
		return this.premierLance;
	}

	public void setPremierLance(boolean bool)
	{
		this.premierLance = bool;
	}


	
    public void setTabSommet(ArrayList<Sommet> tabSmt)
    {
        this.tabSommet = tabSmt;
    }

    /**
     * Modifie la liste des routes
     */
    public void setTabRoute(ArrayList<Route> tabRt)
    {
        this.tabRoute = tabRt;
    }

	public int getNbEtapeScenario()                     { return this.nbEtapeScenario;          }

	public void setNbEtapeScenario(int nbEtapeScenario) { this.nbEtapeScenario = nbEtapeScenario;}

	public int getNbScenario()                     { return this.nbScenario;          }

	public void setNbScenario(int nbScenario) { this.nbScenario = nbScenario;}
	/**
	 * A completer.
	 * @return La liste des sommets
	 */
	public boolean getEstScenar() { return this.estScenar; }

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public void setEstScenar(boolean estScenar) { this.estScenar = estScenar; }

	/**
	 * A completer.
	 * @return La liste des sommets
	 */
	public boolean getEstJeu() { return this.estJeu; }

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public void setEstJeu(boolean estJeu) { this.estJeu = estJeu; }

	/**
	 * A completer.
	 * @param estJeu le Jeu
	 */
	public boolean getEstSauvegarde() { return this.estSauvegarde; }


	/**
	 * Renvoie l'instance de la classe EditionFichier qui est lié au controleur.
	 * @return  la valeur de editionFichier
	 */
	public EditionFichier getEditionFichier()
	{
		return this.editionFichier;
	}

	/**
	 * Méthode qui vérifie si la route est valide.
	 * @param r la route concernée
	 * @return La liste des sommets
	 */
	public boolean estValide(Route r)
	{
		if (!(r.getSommetDep().getJoueur()!=null || r.getSommetArr().getJoueur()!=null || r.getSommetDep().getDepart() || r.getSommetDep().getDepart()))
			return false ;
		return r.getJoueur() == null;
	}

	/**
	 * Renvoie la liste des sommets de la carte
	 * @return La liste des sommets
	 */
	public ArrayList<Sommet> getTabSommet() 
	{ 
		ArrayList<Sommet> tempSommet = new ArrayList<Sommet>();

		for ( Sommet som : this.tabSommet )
			tempSommet.add(som);

		return tempSommet;
	}

	/**
	 * Renvoie la frameDemarrage liée au controleur
	 * @return la l'instance de frameDemarrage
	 */
	public FrameDemarrage getFrameDemarrage()
	{
		return this.frameDemarrage;
	}

	/**
	 * Renvoie la liste des routes de la catre
	 * @return La liste des routes
	 */
	public ArrayList<Route> getTabRoute() 
	{ 
		ArrayList<Route> tempRoute = new ArrayList<Route>();

		for ( Route rot : this.tabRoute )
			tempRoute.add(rot);

		return tempRoute;
	}

	/**
	 * Méthode qui vérifie si les règles du jeu sont respectées, et effectue les actions du joueurs.
	 * @param r La route concernée
	 */
	public void jouer (Route r) throws IOException
	{
		this.finPartie=estFin(r);
		if (!this.finPartie)
		{
			if (this.estValide(r) )
			{
				if (this.tourJ1)
				{
					if (!this.estScenar)
						this.getEditionFichier().ecrireScenario(1,404,r.getSommetDep().getId(),r.getSommetArr().getId(),r.getNbTroncons());

					this.majFrameJoueur(this.j1, this);
					r.setJoueur(this.j1);

					if(this.routeDepart == null)
						this.routeDepart = r;

					this.j1.addJetons(r.getNbTroncons());
					this.j1.ajouterScoreRoute(r.getNbTroncons());

					
					if (r.getSommetDep().getJoueur()==null && r.getSommetDep().getMateriaux() != null)
						this.j1.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getJoueur()==null && r.getSommetArr().getMateriaux() != null)
						this.j1.addSommetRecup(r.getSommetArr());
					
					this.frameDemarrage.getFrameChoix().getF1().refresh();
					
					this.tourJ1= !this.tourJ1;
					this.majFrameJoueur(this.j1, this);
				}
				else
				{
					if (!this.estScenar )
						this.getEditionFichier().ecrireScenario(2,404,r.getSommetDep().getId(),r.getSommetArr().getId(),r.getNbTroncons());
					
					r.setJoueur(this.j2);
					this.j2.addJetons(r.getNbTroncons());
					this.j2.ajouterScoreRoute(r.getNbTroncons());

					if (r.getSommetDep().getJoueur() == null && r.getSommetDep().getMateriaux() != null)
						this.j2.addSommetRecup(r.getSommetDep());
					
					if (r.getSommetArr().getJoueur() == null && r.getSommetArr().getMateriaux() != null)
						this.j2.addSommetRecup(r.getSommetArr());
					
					this.frameDemarrage.getFrameChoix().getF2().refresh();
					
					this.tourJ1= !this.tourJ1;
					this.majFrameJoueur(this.j2, this);
				}
			}
			this.finPartie=estFin(r);
		}
		
		//this.frameDemarrage.getFrameChoix().getFrameJeu().majIHM();
		this.frameDemarrage.getFrameChoix().getFrameJeu().repaint();
		this.frameDemarrage.getFrameChoix().getF2().refresh();
		this.frameDemarrage.getFrameChoix().getF1().refresh();
		
		
		/*if (r.getJoueur()==this.getJoueur1())
			this.editionFichier.ecrireScenario(1, 1, r.getSommetDep().getId(),r.getSommetArr().getId(), r.getNbTroncons()  );
		else 
			this.editionFichier.ecrireScenario(2, 1, r.getSommetDep().getId(),r.getSommetArr().getId(), r.getNbTroncons()  );*/
	}

	/**
	 * Méthode qui met a jour le titre des FrameJoueurs
	 * @param j le joueur correspondant la frame
	 * @param ctrl le Controleur
	 */
	public void majFrameJoueur( Joueur j , Controleur ctrl )
	{
		this.frameDemarrage.getFrameChoix().getF1().setTitle(this.frameDemarrage.getFrameChoix().getF1().majTitre(this));
		this.frameDemarrage.getFrameChoix().getF2().setTitle(this.frameDemarrage.getFrameChoix().getF2().majTitre(this));
	}

	public Route getRouteDepart()
	{
		return this.routeDepart;
	}

	public boolean estFin (Route r)
	{
		if ( (this.getJoueurJoue().getJetons() + r.getNbTroncons() ) > Joueur.getNbMaxJetonsPossession() )
		{
			return true;
		}

		// true ne peut plus jouer

		if ( this.getJoueur1().getJetons() == Joueur.getNbMaxJetonsPossession() || this.getJoueur2().getJetons() == Joueur.getNbMaxJetonsPossession())
		{
			new FrameScore(this);
			this.frameDemarrage.getFrameChoix().getFrameJeu().setEnabled(false);
		}

		for (Sommet s : this.tabSommet)
			if (s.getJoueur()==null && !s.getDepart())
			{
				return false;
			}
		
		
		if (!this.finPartie)
		{
			new FrameScore(this);
			this.frameDemarrage.getFrameChoix().getFrameJeu().setEnabled(false);
		}
			

		
		return true;
	}

	/**
	 * Méthode qui renvoie le booléen correspondant au joueur qui doit jouer.
	 * @return vrai si c'est au tour du joueur 1, faux si c'est au tour du joueur 2.
	 */
	public boolean getTour()
	{
		return this.tourJ1;
	}

	public void setTourJ2()
	{
		this.tourJ1 = true;

		for (Sommet s : this.tabSommet)
		{
			s.setJoueur(null);
		}

		for (Route r : this.tabRoute)
		{
			r.setJoueur(null);
		}

		this.j1.reset();
		this.j2.reset();
	}

	public void setTourJ1HorsScena()
	{
		this.tourJ1 = true;
	}

	public void setTourJ2HorsScena()
	{
		this.tourJ1 = false;
	}
		

	public void setTourJ1()
	{
		this.tourJ1 = true;
		

		for (Sommet s : this.tabSommet)
		{
			s.setJoueur(null);
		}

		for (Route r : this.tabRoute)
		{
			r.setJoueur(null);
		}

		this.j1.reset();
		this.j2.reset();
		

	}

	/**
	 * Méthode qui renvoie le booléen correspondant à la capacité du joueur de jouer.
	 * @return vrai si c'est au tour du joueur j, faux si c'est au tour de l'autre joueur.
	 */	
	public boolean getTourJ( Joueur j )
	{
		return (j == this.getJoueur1() && this.tourJ1); 
	}

	/**
	 * Méthode qui initialise le jeu. Elle met donc en place la carte, ses chemins et ses sommets lorsque le fichier texte n'est pas utilisé.
	 */
	public void init() throws IOException
	{
		this.initMateriaux();
		String tmpCoul = "";
		int    tmpZone = -1;
		int rndm;
		Materiaux tmpMat;

		Controleur.nbSommets = 2;

		String[] tabNomSmt = new String[] { "V4", "V8", "V2", "V3", "V6", "B6", "B8", "R5", "R3", "R1", "G2", "B3", "B4", "B2", "G4", "J3", "R4", "R2", "M2", "G0", "J1", "J4", "M5", "M3", "M4", "M1", "G3", "G1", "J5", "J2" };
		int[] tabCooX = new int[] {  543, 370, 506, 691, 262, 409, 673, 113, 245, 289, 784, 367, 533, 672, 783, 956, 126, 178, 551, 888, 1194, 1080, 248, 362, 477, 639, 872, 801, 1069, 979 };
		int[] tabCooY = new int[] {   83, 115, 273, 71, 175, 428, 299, 322, 354, 478, 322, 657, 543, 469, 103, 146, 509, 621, 685, 314, 498, 318, 736, 824, 873, 857, 663, 861, 819, 498 };
		//Génération des Sommet
		this.tabSommet.add( new Sommet(0, "DEPART", 769, 543, null, true, null, 1));
		for(int cpt = 0; cpt < tabNomSmt.length ; cpt++)
		{
			switch ( tabNomSmt[cpt].substring( 0, 1 ) )
			{
				case "J" -> tmpCoul = "Jaune";
				case "B" -> tmpCoul = "Bleu";
				case "G" -> tmpCoul = "Gris";
				case "V" -> tmpCoul = "Vert";
				case "R" -> tmpCoul = "Rouge";
				case "M" -> tmpCoul = "Marron";
			}

			rndm = (int)(Math.random()*(this.lstMateriaux.size()));

			tmpMat = new Materiaux(this.lstMateriaux.remove(rndm));

			tmpZone = Integer.parseInt( tabNomSmt[cpt].substring( 1, 2 ) );
			this.tabSommet.add( new Sommet( tmpZone, tmpCoul, tabCooX[cpt], tabCooY[cpt], tmpMat, false, null) );
		}


		//Ajout de la zone de départ
		tabRoute.add(new Route(tabSommet.get(1), tabSommet.get(2), 1));
		tabRoute.add(new Route(tabSommet.get(1), tabSommet.get(3), 1));
		tabRoute.add(new Route(tabSommet.get(2), tabSommet.get(3), 1));
		tabRoute.add(new Route(tabSommet.get(1), tabSommet.get(4), 1));
		tabRoute.add(new Route(tabSommet.get(3), tabSommet.get(4), 1));
		tabRoute.add(new Route(tabSommet.get(5), tabSommet.get(3), 1));
		tabRoute.add(new Route(tabSommet.get(5), tabSommet.get(6), 1));
		tabRoute.add(new Route(tabSommet.get(6), tabSommet.get(3), 1));
		tabRoute.add(new Route(tabSommet.get(6), tabSommet.get(7), 1));
		tabRoute.add(new Route(tabSommet.get(7), tabSommet.get(4), 1));
		tabRoute.add(new Route(tabSommet.get(8), tabSommet.get(5), 2));
		tabRoute.add(new Route(tabSommet.get(8), tabSommet.get(9), 1));
		tabRoute.add(new Route(tabSommet.get(9), tabSommet.get(5), 1));
		tabRoute.add(new Route(tabSommet.get(10), tabSommet.get(6), 1));
		tabRoute.add(new Route(tabSommet.get(10), tabSommet.get(9), 1));
		tabRoute.add(new Route(tabSommet.get(11), tabSommet.get(14), 1));
		tabRoute.add(new Route(tabSommet.get(12), tabSommet.get(10), 1));
		tabRoute.add(new Route(tabSommet.get(12), tabSommet.get(6), 1));
		tabRoute.add(new Route(tabSommet.get(12), tabSommet.get(13), 1));
		tabRoute.add(new Route(tabSommet.get(13), tabSommet.get(14), 1));
		tabRoute.add(new Route(tabSommet.get(13), tabSommet.get(7), 1));
		tabRoute.add(new Route(tabSommet.get(15), tabSommet.get(11), 1));
		tabRoute.add(new Route(tabSommet.get(7), tabSommet.get(14), 1));
		tabRoute.add(new Route(tabSommet.get(15), tabSommet.get(16), 2));
		tabRoute.add(new Route(tabSommet.get(17), tabSommet.get(8), 2));
		tabRoute.add(new Route(tabSommet.get(17), tabSommet.get(18), 1));
		tabRoute.add(new Route(tabSommet.get(10), tabSommet.get(18), 1));
		tabRoute.add(new Route(tabSommet.get(1), tabSommet.get(20), 2));
		tabRoute.add(new Route(tabSommet.get(19), tabSommet.get(12), 1));
		tabRoute.add(new Route(tabSommet.get(20), tabSommet.get(15), 2));
		tabRoute.add(new Route(tabSommet.get(20), tabSommet.get(21), 1));
		tabRoute.add(new Route(tabSommet.get(21), tabSommet.get(22), 1));
		tabRoute.add(new Route(tabSommet.get(22), tabSommet.get(16), 1));

		tabRoute.add(new Route(tabSommet.get(23), tabSommet.get(24), 1));
		tabRoute.add(new Route(tabSommet.get(23), tabSommet.get(18), 2));
		tabRoute.add(new Route(tabSommet.get(24), tabSommet.get(25), 1));
		tabRoute.add(new Route(tabSommet.get(25), tabSommet.get(26), 1));
		tabRoute.add(new Route(tabSommet.get(24), tabSommet.get(19), 2));

		tabRoute.add(new Route(tabSommet.get(27), tabSommet.get(20), 1));
		tabRoute.add(new Route(tabSommet.get(27), tabSommet.get(21), 2));
		tabRoute.add(new Route(tabSommet.get(28), tabSommet.get(27), 1));
		tabRoute.add(new Route(tabSommet.get(28), tabSommet.get(26), 2));
		tabRoute.add(new Route(tabSommet.get(28), tabSommet.get(29), 2));
		tabRoute.add(new Route(tabSommet.get(30), tabSommet.get(29), 1));
		tabRoute.add(new Route(tabSommet.get(29), tabSommet.get(21), 2));
		tabRoute.add(new Route(tabSommet.get(30), tabSommet.get(22), 2));
		tabRoute.add(new Route(tabSommet.get(21), tabSommet.get(30), 1));
		tabRoute.add(new Route(tabSommet.get(0), tabSommet.get(14), 1));
		tabRoute.add(new Route(tabSommet.get(0), tabSommet.get(27), 2));
		tabRoute.add(new Route(tabSommet.get(0), tabSommet.get(19), 1));
		tabRoute.add(new Route(tabSommet.get(19), tabSommet.get(26), 1));

		this.editionFichier.sauvegarde();
	}

	public boolean estTourJ1()
	{
		return this.tourJ1;
	}

	/**
	 * Méthode qui affecte les JetonsPossessions aux joueurs.
	 */
	private void initJetonPossession()
	{
		for(int i = 0 ; i < this.tabRoute.size() ; i++)
		{
			if(this.tabRoute.get(i).getJoueur() == j1)
			{
				this.j1.addJetons(this.tabRoute.get(i).getNbTroncons());
			} else if (this.tabRoute.get(i).getJoueur() == j2)
			{
				this.j2.addJetons(this.tabRoute.get(i).getNbTroncons());
			}
		}
	}

	/**
	 * Renvoie le joueur n°1
	 * @return le joueur 1
	 */
	public Joueur getJoueur1 ()
	{
		return this.j1;
	}

	/**
	 * Renvoie le joueur n°2
	 * @return le joueur 2
	 */
	public Joueur getJoueur2 ()
	{
		return this.j2;
	}

	public Joueur getJoueurJoue()
	{
		return (this.getTour()?j1:j2);
	}


	/**
	 * Méthode qui recherche un sommet dans la liste de sommets a partir de son numéro.
	 * @param numSmt le numéro du sommet
	 * @return le sommet correspondant au numéro
	 */
	public Sommet rechercheSommet(String numSmt)
	{
		for (Sommet s : this.tabSommet)
		{
			if (s.getId() == Integer.parseInt(numSmt))
				return s;
		}
		return null;
	}

	public Route rechercheRoute(Sommet smtDep, Sommet smtArr)
	{
		for(Route r : this.tabRoute)
		{
			if(r.getSommetDep() == smtDep && r.getSommetArr() == smtArr)
				return r;
		}
		return null;
	}

	public ArrayList<String> getLstMateriaux()
	{
		return this.lstMateriaux;
	}

	/**
	 * Méthode qui appelle la méthode de mise a jour de la carte.
	 */
	public void MajFrameModification() { this.frameDemarrage.getFrameModification().repaint(); }

	/**
	 * Méthode qui ajoute ou supprime une route dans la liste de route.
	 * @param sommetDep le sommet de départ
	 * @param sommetArr le sommet d'arrivée
	 * @param nbTroncon le nombre de troncons de la route
	 */
	public void ajouterOuSupprimerRoute( Sommet sommetDep, Sommet sommetArr, int nbTroncon) 
	{
		Route tempRoute = new Route(sommetDep, sommetArr, nbTroncon);
		boolean tempEstSup = false;

		for ( Route rt : this.tabRoute )
		{
			if ( tempRoute.equals(rt) )
			{
				this.tabRoute.remove(rt);
				tempEstSup = true;
				break;
			}
		}
		if ( !tempEstSup )
		{
			this.tabRoute.add( tempRoute );
		}
	}

	/**
	 * Méthode qui ajoute ou supprime un sommet dans la liste de sommet.
	 * @param numSom le nunméro du sommet
	 * @param nomCoul la couleur du sommet
	 * @param x l'abscisse du sommet
	 * @param y l'ordonnée du sommet
	 * @param estDepart vrai si on ajoute un sommet de départ, faux si non
	 */
	public void ajouterOuSupprimerSommet(int idVille ,int numSom, String nomCoul, int x, int y,Materiaux materiaux ,boolean estDepart )
	{
		Sommet tempSommet = new Sommet(numSom, nomCoul, x, y, materiaux , estJeu,null, idVille) ;
		boolean tempEstSup = false;

		for ( Sommet rt : this.tabSommet )
		{
			if ( rt.getNumSom() == numSom &&  rt.possede(x, y) && rt.getDepart() == estDepart)
			{
				ArrayList<Route> tmpR = new ArrayList<>(this.tabRoute.size()/2);

				for(Route r : this.tabRoute)
				{
					if(r.getSommetArr() == rt || r.getSommetDep() == rt)
					{
						tmpR.add(r);
					}
				}


				for(Route r : tmpR)
				{
					this.tabRoute.remove(r);
				}


				if(this.tabSommet.indexOf(rt) == this.tabSommet.size()-1)
				{
					Controleur.nbSommets--;
				}
				this.lstMateriaux.add(rt.getMateriaux().getNom());
				this.tabSommet.remove(rt);
				tempEstSup = true;
				break;
			}
		}
		if ( !tempEstSup )
		{
			this.tabSommet.add( tempSommet );
			Controleur.nbSommets = this.tabSommet.get(this.tabSommet.size()-1).getId()+1;
		}
	}

	/**
	 * Méthode qui recherche et renvoie le sommet placé aux coordonnées données.
	 * @param x l'abcisse du sommet
	 * @param y l'ordonnée du sommet
	 * @return le sommet placé à ces coordonnées.
	 */
	public Sommet getSommet(int x, int y)
	{

		for ( Sommet s : this.tabSommet )
		{
			if ( s.possede(x, y) )
				return s;
		}
		return null;
	}

	/**
	 * Méthode qui recherche et renvoie le sommet qui a l'id passe en parametre.
	 * @param idsom id du sommet
	 * @return le sommet qui dispose de cette id.
	 */
	public Sommet getSommet(int idsom)
	{

		for ( Sommet s : this.tabSommet )
		{
			if ( s.getId() == idsom)
				return s;
		}
		return null;
	}


	public void reInit() throws IOException {
		this.tabSommet = new ArrayList<Sommet>(30);
		this.j1 = new Joueur(this);
		this.j2 = new Joueur(this);
		this.tabRoute  = new ArrayList<Route>(40);
		this.tourJ1 = true;

		if(this.getNomThemePrincipal().equals("Europe"))
		{
			this.getEditionFichier().lectureFichier(System.getProperty("user.dir") + "/jeu/src/theme_europe.txt", true);
		}else{
			this.init();
		}
	}

	public void supprimerTout() throws IOException
	{
		this.j1 = new Joueur(this);
		this.j2 = new Joueur(this);
		this.tabSommet = new ArrayList<Sommet>(30);
		Controleur.nbSommets = 2;
		this.tabSommet.add(new Sommet(0,"DEPART", 769, 543, null, true,null, 1));
		this.tabRoute  = new ArrayList<Route>(40);
		this.initMateriaux();
		this.editionFichier.sauvegarde();
	}

	/**
	 * Méthode qui modifie les coordonnées d'un sommet.
	 * @param x l'abcisse du sommet
	 * @param y l'ordonnée du sommet
	 * @param som le sommet à déplacer
	 */
	public void setSommet(int x, int y, Sommet som)
	{
		som.setX(x); som.setY(y);
	}

	public void setElementsTheme(String[] elements)
	{
		this.elementsTheme = elements;
	}

	public String[] getElementsTheme()
	{
		return this.elementsTheme;
	}

	public String getNomThemePrincipal()
	{
		if(this.elementsTheme != null)
			return this.elementsTheme[0];
		return null;
	}

	public String getNomThemeJ1()
	{
		return this.elementsTheme[1];
	}

	public String getNomThemeJ2()
	{
		return this.elementsTheme[2];
	}

	public String getNomThemeSommet()
	{
		return this.elementsTheme[3];
	}

	public String getNomThemeRoute()
	{
		return this.elementsTheme[4];
	}

	public Route getRoute(Sommet sommetDep, Sommet sommetArr)
	{
		
		for ( Route r : this.tabRoute)
		{
			
			if ( r.getSommetDep().equals(sommetDep) && r.getSommetArr().equals(sommetArr))
				return r;
		}
		
		return null;
	}
	/**
	 * Méthode principale de la classe Controleur. Elle lance l'application.
	 * @param arg les arguments de la méthode
	 */
	public static void main (String[] arg) throws IOException
	{
		new Controleur();
	}
}