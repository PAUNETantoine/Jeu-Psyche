package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Sommet;
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
public class PanelMine extends JPanel
{
	private Controleur  ctrl;
	private Graphics2D  g2;
	private Joueur		joueur;


	public PanelMine (Controleur ctrl, Joueur	joueur)
	{
		this.ctrl=ctrl;
		this.joueur=joueur;

		this.setPreferredSize(new Dimension(100, 500));
		this.setBackground (new Color (208,208,208));

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g2 = (Graphics2D) g;
		int cpt1=0, cpt2=0  ;

		this.g2.setBackground(new Color (208,208,208));
		int[] tabInt= {10,50};

		for (Sommet s : this.joueur.getSommetRecup())
		{

			this.g2.drawImage(getToolkit().getImage("jeu/src/images/"+ this.ctrl.getNomThemePrincipal() +"/Mine_"+ s.getNomCoul() +".png"), tabInt[cpt1],10+45*cpt2,20,40, this);
			if (cpt1==0){cpt1++;}
			else {cpt1--;}

			if (cpt1==0)
				cpt2++;
		}
		
	}
}