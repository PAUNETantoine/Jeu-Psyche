package jeu.ihm;

import jeu.Controleur;
import javax.swing.*;

/**
 * Cette classe gère la fenetre du menu principal du jeu.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class FrameChoix extends JFrame
{
	private PanelChoixJoueur panelChoixJoueur;
	private Controleur ctrl;
	private FrameJeu   fJeu;

	private FrameJoueur    f1, f2;

	/**
	 * Constructeur de la frame de Choix du joueur
	 * @param ctrl le Controleur qui lance le programme
	 */
	public FrameChoix( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setTitle   ("Choix des joueurs et de leur plateau");
		this.setSize    ( 700,200 );
		this.setLocation(  300, 300 );
		this.setResizable(false);


		// Création et ajout du Panel
		this.panelChoixJoueur = new PanelChoixJoueur(this, this.ctrl);
		this.add(this.panelChoixJoueur);

		
		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * Méthode de lancement des FrameJoueur correspondant à leur choix
	 */
	public void creerFrameJoueur()
	{
		this.f1   = new FrameJoueur(this.ctrl.getJoueur1(), this.ctrl);
		this.f2   = new FrameJoueur(this.ctrl.getJoueur2(), this.ctrl);
		this.fJeu = new FrameJeu(this.ctrl);
		this.dispose();
		this.ctrl.frameDemarrage.dispose();
	}

	/**
	 * Renvoie la frame du joueur n°1.
	 * @return la frame du joueur 1
	 */
	public FrameJoueur getF1 ()
	{
		return this.f1;
	}

	/**
	 * Renvoie la frame du joueur n°2.
	 * @return la frame du joueur 2
	 */
	public FrameJoueur getF2 ()
	{
		return this.f2;
	}

	public FrameJeu getFrameJeu(){return this.fJeu;}

	public void setfJeu(FrameJeu fJeu) {
		this.fJeu = fJeu;
	}


/*
	public static void main( String[] args )
	{
		new FrameChoix();
	}
*/
	
}