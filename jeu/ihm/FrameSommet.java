package jeu.ihm;

import jeu.Controleur;
import javax.swing.*;

/**
 * Cette classe créé la Frame qui permet de modifier ou de créer les sommets.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class FrameSommet extends JFrame
{
	private PanelSommet panelDesSommets;

	/**
	 * Constructeur de la classe interface permettant de générer une IHM de 800px / 200px
	 * Contient tout les éléments graphiques
	 */
	public FrameSommet(Controleur ctrl)
	{
		this.setTitle   ( "Sommet"  );
		this.setSize    ( 800, 200 );
		this.setLocation( 50,50    );
		this.setResizable(false);

		this.panelDesSommets = new PanelSommet( ctrl );

		this.add( this.panelDesSommets );

		this.setVisible(true);
	}

	/**
	 * Renvoie le PanelSommet correspondant a la FrameSommet
	 * @return un PanelSommet
	 */
	public PanelSommet getPanelDesSommets() {return this.panelDesSommets;}

}
