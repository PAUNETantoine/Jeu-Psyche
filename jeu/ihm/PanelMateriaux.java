package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Joueur;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe correspond au Jetons sur lequel est affiché le panel des joueurs
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelMateriaux extends JPanel
{
	private Graphics2D  g2;
	private Controleur  ctrl;
	private Joueur 		joueur;


	public PanelMateriaux (Controleur ctrl, Joueur j)
	{
		this.ctrl=ctrl;
		this.joueur= j;

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g2 = (Graphics2D) g;

		int k=0;
		if (this.joueur==this.ctrl.getJoueur1()) {k = 1 ;}
		if (this.joueur==this.ctrl.getJoueur2()) {k = 2 ;}

		g2.drawImage(getToolkit().getImage("jeu/src/images/"+ this.ctrl.getNomThemePrincipal() +"/plateau_joueur_"+k+".png"), 0,0,this);

		int x = 65;
		int y = 57;
		int xNR = 65;

		//super.paintComponent(g);
		this.g2 = (Graphics2D) g;
		
		for(int i = 0; i < this.joueur.getTableMateriaux().length ; i++) // Ajout des matériaux
		{
			
			for(int j = 0 ; j < this.joueur.getTableMateriaux()[i].length ; j++)
			{
				if(this.joueur.getTableMateriaux()[i][j] != null)
				{
					g2.drawImage(getToolkit().getImage("jeu/src/images/"+ this.ctrl.getNomThemePrincipal() +"/"+this.joueur.getTableMateriaux()[i][j].getNom()+".png"), x,y,this);
				}
				x += 53;
			}
			y += 54;
			x= 65;
		}

		for(int i = 0; i < this.joueur.getTabPiece().length ; i++) // Ajout des matériaux
		{
			if(this.joueur.getTabPiece()[i] != null)
			{
				g2.drawImage(getToolkit().getImage("jeu/src/images/"+ this.ctrl.getNomThemePrincipal() +"/NR.png"), xNR,328,this);
				xNR+=54;
			}
		}


	}
}