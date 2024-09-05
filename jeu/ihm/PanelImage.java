package jeu.ihm;

import jeu.Controleur;

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
public class PanelImage extends JPanel
{
	private Graphics2D  g2;
	private String 		nomCoul;
	private Controleur  ctrl;


	public PanelImage (Controleur ctrl,String	nomCoul)
	{
		this.nomCoul=nomCoul;
		this.ctrl=ctrl;

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.g2 = (Graphics2D) g;


		this.g2.drawImage(getToolkit().getImage("../src/images/"+ this.ctrl.getNomThemePrincipal() +"/"+this.nomCoul+".png"),0 ,0 ,20,20, this);
	}
}