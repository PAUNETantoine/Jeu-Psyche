package jeu.ihm;

import jeu.Controleur;

import javax.swing.JFrame;

/**
 * Cette classe créé la Frame qui permet de modifier ou de créer les routes.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class FrameRoute extends JFrame
{
    private PanelRoutes panelDesRoutes;

	/**
	 * Constructeur de la classe interface permettant de générer une IHM de 800px / 200px
	 * Contient tout les éléments graphiques
	 */
    public FrameRoute( Controleur ctrl)
	{
		this.setTitle   ( "Frame Routes" );
		this.setSize    ( 800, 200 );
		this.setLocation( 50,50 );  
		this.setResizable(false); 
        
        this.panelDesRoutes = new PanelRoutes( ctrl );
		
        this.add( this.panelDesRoutes );

		this.setVisible(true);
	}
}