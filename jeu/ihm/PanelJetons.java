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
public class PanelJetons extends JPanel
{
	private Controleur  ctrl;
	private Graphics2D  g2;
	private int 		j;
	private Joueur		joueur;
	private FrameJoueur fmJoueur;


	public PanelJetons (FrameJoueur frame,Controleur ctrl, Joueur	joueur)
	{
		this.ctrl=ctrl;
		this.joueur=joueur;
		this.fmJoueur =frame;

		this.setPreferredSize(new Dimension(300, 50));
		this.setBackground (new Color (208,208,208));

		if (this.joueur==this.ctrl.getJoueur1())
			this.j=1;
		else
			this.j=2;

		//this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g2 = (Graphics2D) g;
		int temp=0;

		this.g2.setBackground(new Color (208,208,208));
		this.g2.drawImage(getToolkit().getImage("../src/images/"+ this.ctrl.getNomThemePrincipal() +"/pion_joueur_"+this.j+".png"), 10,10,20,20, this);

		Font font = new Font("Arial", Font.BOLD, 25);
		g2.setFont(font);

		

		temp = this.fmJoueur.calculeScore();



		if (this.j==1)
			this.g2.drawString("×  "+temp, 40, 30);
		if (this.j==2)
			this.g2.drawString("×  "+temp, 40, 30);
	}
}