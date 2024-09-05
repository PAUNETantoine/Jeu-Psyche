package jeu.ihm;

import jeu.*;
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

public class FrameScore extends JFrame
{
	private PanelScore panelDesScore;

	private Controleur ctrl;

	/**
	 * Constructeur de la classe interface permettant de générer une IHM de 800px / 200px
	 * Contient tout les éléments graphiques
	 */
	public FrameScore(Controleur ctrl)
	{
		this.setTitle   ( "Résultat de la partie : "  );
		this.setSize    ( 500, 750 );
		this.setLocation( 500,100    );
		this.setResizable(false);
		this.ctrl=ctrl;

		this.panelDesScore = new PanelScore( this.ctrl, this );

		this.add( this.panelDesScore );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}