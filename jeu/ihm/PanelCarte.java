package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Route;
import jeu.metier.Sommet;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe correspond au Panel sur lequel est affiché la carte ainsi
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelCarte extends JPanel
{
	private final int RAYON = 20;
	private Graphics2D g2;
	private Controleur ctrl;
	private BufferedImage image;
	private BufferedImage pionJoueur1;
	private BufferedImage pionJoueur2;

	private boolean clicked = false;

	/**
	 * Constructeur du panel
	 */
	public PanelCarte(Controleur ctrl)
	{
		try
		{
			if(ctrl.getNomThemePrincipal().equals("Europe") && !ctrl.getEstJeu() && ctrl.getPremierLancer() && ctrl.getEstSauvegarde())
			{
				ctrl.getEditionFichier().lectureFichier(System.getProperty("user.dir") + "/jeu/src/theme_europe.txt", true);
				this.repaint();
			} else if (ctrl.getNomThemePrincipal().equals("Pays") && !ctrl.getEstJeu() && ctrl.getPremierLancer() && ctrl.getEstSauvegarde())
			{
				//initPays
			}

			this.image       = ImageIO.read(new File("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/bgPlateau.png"    ));
			this.pionJoueur1 = ImageIO.read(new File("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_1.png"));
			this.pionJoueur2 = ImageIO.read(new File("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_2.png"));
		} catch (IOException e) {
			try {
				this.ctrl.reInit(); //Si le fichier n'existe pas
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		this.ctrl = ctrl;

		GereSouris gereSouris = new GereSouris();
		
		this.addMouseListener( gereSouris );
		this.addMouseMotionListener( gereSouris );

		ctrl.setPremierLance(false); //On dit qu'on à déjà lancer la frame une fois
	}


	/**
	 * Méthode de dessins
	 * @param g l'instance de Graphics2D utilisée sur ce panel
	 */
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);

		this.g2 = (Graphics2D) g;


		g2.drawImage(this.image, 0,0, this);
		if (ctrl.getEstJeu())
		{
			if ( ctrl.getTourJ( ctrl.getJoueur1() ) )
				g2.drawImage(this.pionJoueur1, 40, 20, this);
			else
				g2.drawImage(this.pionJoueur2, 40, 20, this);

		}

		this.g2.setStroke (new BasicStroke (2.0f));
		int adjCercle = this.RAYON / 2 ;
		this.g2.setColor( new Color(000060) );
		

		for(Route r : ctrl.getTabRoute())
		{
			int x1 = r.getSommetDep().getX(), y1 = r.getSommetDep().getY();
			int x2 = r.getSommetArr().getX(), y2 = r.getSommetArr().getY();

			int vec1, vec2;

			vec1=0;
			vec2=0;

			//this.g2.drawOval(442, 475, 10,10); Position Depart
			
			this.g2.drawLine(x1 + adjCercle+ vec1, y1 + adjCercle + vec2 , x2 + adjCercle + -vec1, y2 + adjCercle + -vec2);

			/*this.g2.drawOval(x1 + adjCercle+ vec1 -5 , y1 + adjCercle -5 + vec2 , 10,10);
			this.g2.fillOval(x1 + adjCercle+ vec1 -5 , y1 + adjCercle -5 + vec2 , 10,10);
			this.g2.drawOval(x2 + adjCercle -5 + -vec1, y2 + adjCercle -5+ -vec2,  10,10);
			this.g2.fillOval(x2 + adjCercle -5 + -vec1, y2 + adjCercle -5+ -vec2,  10,10);*/

			if (r.getNbTroncons()==2)
			{
				this.g2.drawOval( ((x1 + adjCercle)+ (x2+ adjCercle))/2 -5 , ((y1+ adjCercle) + (y2+ adjCercle))/2 -5,10,10 );
				this.g2.fillOval(( (x1 + adjCercle)+ (x2+ adjCercle))/2  -5, ((y1+ adjCercle) + (y2+ adjCercle))/2 -5,10,10);

				if (r.getJoueur()==this.ctrl.getJoueur1())
				{
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_1.png"),((x1 + adjCercle)+((x1 + adjCercle)+ (x2+ adjCercle))/2)/2-15 , ((y1 + adjCercle)+((y1 + adjCercle)+ (y2+ adjCercle))/2)/2-15, this);
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_1.png"),((x2 + adjCercle)+((x1 + adjCercle)+ (x2+ adjCercle))/2)/2-15, ((y2 + adjCercle)+((y1 + adjCercle)+ (y2+ adjCercle))/2)/2-15, this);
				}
				else if (r.getJoueur()==this.ctrl.getJoueur2())
				{
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_2.png"),((x1 + adjCercle)+((x1 + adjCercle)+ (x2+ adjCercle))/2)/2-15 , ((y1 + adjCercle)+((y1 + adjCercle)+ (y2+ adjCercle))/2)/2-15, this);
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_2.png"),((x2 + adjCercle)+((x1 + adjCercle)+ (x2+ adjCercle))/2)/2-15, ((y2 + adjCercle)+((y1 + adjCercle)+ (y2+ adjCercle))/2)/2-15, this);
				}
					
			}

			if (r.getNbTroncons()==1)
			{
				if (r.getJoueur()==this.ctrl.getJoueur1())
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_1.png"),((x1 + adjCercle)+ (x2+ adjCercle))/2-15 , ((y1+ adjCercle) + (y2+ adjCercle))/2-15, this);
				else if (r.getJoueur()==this.ctrl.getJoueur2())
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/pion_joueur_2.png"),((x1 + adjCercle)+ (x2+ adjCercle))/2-15 , ((y1+ adjCercle) + (y2+ adjCercle))/2-15, this);
				
			}

			
				
		}

		for (Sommet s : ctrl.getTabSommet())
		{
			if (s.getJoueur()!=null || !this.ctrl.getEstJeu() )
				g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/Mine_"+s.getNomCoul()+"_clair.png"), s.getX()-20, s.getY()-40, this);
			else if (!s.getDepart())
			{
				g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/Mine_"+s.getNomCoul()+".png"), s.getX()-20, s.getY()-40, this);
				g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/"+s.getMateriaux().getNom()+".png"), s.getX()-10, s.getY()+15, 25,25, this);
			}
			if (s.getDepart())
				g2.drawImage(getToolkit().getImage("jeu/src/images/"+ ctrl.getNomThemePrincipal() +"/Rome.png"), s.getX()-15, s.getY()-20,50,50, this);

			if (!s.getNomCoul().equals("DEPART"))
			{
				g2.setFont(new Font("Arial", Font.BOLD, 18));
				this.g2.drawString(s.getNumSom()+"", s.getX(), s.getY()-10 );
			}
			
		}
	}

	/**
	 * Classe privée permettant de gêrer les actions de la souris.
	 */
	private class GereSouris extends MouseAdapter
	{
		private Point ptSmt1;
		private Point ptSmt2;

		private Sommet sommetChoisi ;

		/**
		 * Méthode qui gêre l'appui sur la souris.
		 * @param e l'action de la souris
		 */

		public void mouseReleased( MouseEvent e)
		{
			clicked = false;
		}

		public void mousePressed( MouseEvent e)
		{
			if(!clicked)
			{
				if ( PanelCarte.this.ctrl.getEstJeu() )
				{
					for(Route r : ctrl.getTabRoute())
					{
						this.ptSmt1 = new Point(r.getSommetDep().getX(), r.getSommetDep().getY());
						this.ptSmt2 = new Point(r.getSommetArr().getX(), r.getSommetArr().getY());

						if(isNearLine(e.getPoint()))
						{
							clicked = true; //Empêcher les doubles clics sur des lignes les unes sur les autres
							if (ctrl.getSommet( e.getX(), e.getY() )==null)
							{
								try
								{
									ctrl.jouer(r);
								} catch (IOException ex) {
									throw new RuntimeException(ex);
								}
							}

						}
					}
				}

				if (  !PanelCarte.this.ctrl.getEstJeu() )
					this.sommetChoisi = ctrl.getSommet( e.getX(), e.getY() );
			}
		}

		
		
	/**
	 * Méthode qui gêre le déplacement sur la souris lorsque le bouton est appuyé.
	 * @param e l'action de la souris
	 */
		public void mouseDragged( MouseEvent e )
		{

			if ( this.sommetChoisi != null )
			{	
				ctrl.setSommet(e.getX(), e.getY(),this.sommetChoisi);
			
				// Rafréchire la frame
				PanelCarte.this.repaint();
			}
		}

	/**
	 * Méthode qui vérifie si la ligne est suffisament proche de la zone cliquée.
	 * @param p le point cliqué par la souris
	 */
		private boolean isNearLine(Point p)
		{
			if(!clicked)
			{
				double distance = distanceARoute(p, ptSmt1, ptSmt2);
				return distance <= 15; //5px de tolérance
			}
			return false;
		}

	/**
	 * A compléter
	 * @param p le point cliqué par la souris
	 * @param v le point cliqué par la souris
	 * @param w le point cliqué par la souris
	 */
		private double distanceARoute(Point p, Point v, Point w)
		{
			double l2 = v.distanceSq(w);
			if (l2 == 0.0) return p.distance(v);
			double t = Math.max(0, Math.min(1, resultPoint(p, v, w) / l2));
			Point projection = new Point((int) (v.x + t * (w.x - v.x)), (int) (v.y + t * (w.y - v.y)));
			return p.distance(projection);
		}

	/**
	 * A complèter.
	 * @param p le point cliqué par la souris
	 * @param v le point cliqué par la souris
	 * @param w le point cliqué par la souris
	 */
		private double resultPoint(Point p, Point v, Point w)
		{
			return (p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y);
		}
	}
}