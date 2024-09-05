package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Sommet;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * Cette classe créé le Panel contenant la carte de jeu.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelModification extends JPanel
{
	private Controleur ctrl;
	private Graphics2D g2;

	/**
	 * Constructeur du Panel qui permet de modifier la carte.
	 * @param ctrl le Controleur qui lance la frame
	 */
	public PanelModification(Controleur ctrl)
	{
		this.ctrl = ctrl;			
	}

	// public void actionPerformed(ActionEvent e)
	// {
	// 	this.repaint();
	// }
	
	/**
	 * Méthode gêrant le dessin de la carte sur le panel.
	 * @param g l'instance de Graphics2D utilisée
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		this.g2 = (Graphics2D) g;

		Sommet sommet;
		
		// Dessiner l'ensemble des figures
		this.g2.setStroke (new BasicStroke (2.0f));

		if ( this.ctrl.getTabSommet().size() > 0 )
		{
			

			if ( this.ctrl.getTabRoute().size() > 0 )
			{
				// Dessiner les routes
				this.g2.setColor( new Color(0, 0, 0) );

				for (int cpt = this.ctrl.getTabRoute().size()-1; cpt >= 0; cpt--)
				{
					int x1 = this.ctrl.getTabRoute().get(cpt).getSommetDep().getX();
					int y1 = this.ctrl.getTabRoute().get(cpt).getSommetDep().getY();

					int x2 = this.ctrl.getTabRoute().get(cpt).getSommetArr().getX();
					int y2 = this.ctrl.getTabRoute().get(cpt).getSommetArr().getY();
					
					
					if (this.ctrl.getTabRoute().get(cpt).getNbTroncons() > 1)
					{
						int nbTroncons = this.ctrl.getTabRoute().get(cpt).getNbTroncons();
						
						
						/*
							Principe du fonctionnement des tronçons :
							
							1) Calculer la longueur totale de la ligne
							2) Calculer la longueur d'un seul espace  (Un vingtième de la longueur de la ligne)
							3) Calculer la longueur d'un seul tronçon (D'abord soustraire la longueurLigne de la place que les espaces prondront, puis diviser par le nombre d'espaces)
							4) Placer les longueurs dans le pattern
							Tout les indexes   pairs doivent être la longueurTroncons
							Tout les indexes impairs doivent être la longueurEspace
							5) Créer le BasicStroke, qui sera une sorte de décoration pour le trait, un "skin"
							
						*/
						
						
						//  1)
						double longueurLigne = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));


						//  2)
						float longueurEspace  = (float)longueurLigne / 20;
						float longueurTroncons;
						
						//  3)
						longueurTroncons = (float)longueurLigne - (longueurEspace * (nbTroncons - 1));
						longueurTroncons = longueurTroncons / nbTroncons;
						
						
						//  4)
						float[] patternTroncons = new float[nbTroncons * 2 - 1];

						for (int cpt2 = 0; cpt2 < patternTroncons.length-1; cpt2++) 
						{
							if (cpt2 % 2 == 0) 
							{
								
							} 
							else 
							{
								patternTroncons[cpt2-1] = longueurTroncons;
								patternTroncons[cpt2]   = longueurEspace; 
							}
						}
						
						patternTroncons[patternTroncons.length-1] = (patternTroncons.length-1)%2==0?longueurTroncons:0;
						
						//  5)
						BasicStroke pointilles = new BasicStroke(2,                             // Épaisseur du trait
																 BasicStroke.CAP_BUTT,          // Style de terminaison du trait
																 BasicStroke.JOIN_MITER,        // Style de jonction des segments
																 10,                            // Miter limit (inutile pour les lignes simples)
																 patternTroncons,               // Tableau de motif des pointillés
																 0                              // Phase de départ
																 );

						g2.setStroke(pointilles);
					}
					
					this.g2.drawLine(x1+10, y1+10, x2+10, y2+10);
					
					g2.setStroke(new BasicStroke());
				}
			}
			
			// Dessiner les sommets
			this.g2.setColor( new Color(20, 20, 200) );

			for (int cpt = this.ctrl.getTabSommet().size()-1; cpt >= 0; cpt--)
			{
				sommet = this.ctrl.getTabSommet().get(cpt);
				
				this.g2.drawOval( sommet.getX(), sommet.getY(), 20, 20 );
				this.g2.fillOval( sommet.getX(), sommet.getY(), 20, 20 );

				this.g2.drawString( "Test", sommet.getX()-10, sommet.getY()-5 );
			}
		}
	}	
}