package jeu.ihm;

import jeu.Controleur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Cette classe correspond au Panel où les joueurs peuvent séléctionner leurs pseudonymes ainsi que leur faction.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelChoixJoueur extends JPanel implements ActionListener
{
	private JTextField txtJoueur1;
	private JTextField txtJoueur2;

	private JButton btnSauvegarder;
	private JButton btnAnnuler    ;

	private FrameChoix frameChoix;

	private Controleur ctrl;

	/**
	 * Constructeur du Panel de choix des joueurs.
	 * @param frameChoix la Frame sur laquelle ce Panel est utilisé
	 */
	public PanelChoixJoueur(FrameChoix frameChoix, Controleur ctrl)
	{

		this.ctrl=ctrl; 

		this.setLayout( new GridLayout(3, 2));
		this.frameChoix = frameChoix;

		this.add( new JLabel("  Entrez le nom du joueur " + ctrl.getNomThemeJ1() + " : "));
		this.add(txtJoueur1    = new JTextField( "Joueur 1"                                   ));

		this.add( new JLabel("  Entrez le nom du joueur " + ctrl.getNomThemeJ2() + " : "));
		this.add(txtJoueur2    = new JTextField( "Joueur 2"                              ));
		this.txtJoueur1.setSize(100, 30);

		this.add(btnSauvegarder = new JButton("Lancer le jeu (ENTREE)"));
		this.add(btnAnnuler = new JButton("Annuler (ECHAP)"));


		this.btnSauvegarder.addActionListener(this);
		this.btnAnnuler.addActionListener    (this);

		toucheAction(this, KeyStroke.getKeyStroke("ENTER"), "Jouer (Entrer)", new AbstractAction() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				btnSauvegarder.doClick();
			}
		} );

		toucheAction(this, KeyStroke.getKeyStroke("ESCAPE"), "Fermer (Echap)", new AbstractAction() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				btnAnnuler.doClick();
			}
		} );


	}
	/**
	 * Renvoie le texte entré dans le textField numéro 1
	 * @return le String contenu dans txtJoueur1.getText()
	 */
	public String getText1()
	{
		return this.txtJoueur1.getText();
	}

	/**
	 * Renvoie le texte entré dans le textField numéro 2
	 * @return le String contenu dans txtJoueur2.getText()
	 */
	public String getText2()
	{
		return this.txtJoueur2.getText();
	}

	/**
	 * Réalise une action lorsqu'un bouton est appuyé
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed(ActionEvent e)
	{
		if( e.getSource().equals(this.btnSauvegarder) )
		{	
			//Si les texteField sont écrits, alors change le nom du joueur
			if (!this.txtJoueur1.getText().isEmpty() && !this.txtJoueur2.getText().isEmpty() )
			{
				this.ctrl.getJoueur1().setNomJoueur(txtJoueur1.getText());
				this.ctrl.getJoueur2().setNomJoueur(txtJoueur2.getText());
				this.frameChoix.creerFrameJoueur();
			}
		}

		if( e.getSource().equals(this.btnAnnuler) )
		{
			//Ferme la fenêtre
			this.frameChoix.dispose();
		}
	}
	
	/**
	 * Associe une action à une touche placée sur le composant donné.
	 * 
	 * @param composant le composant sur lequel est la touche
	 * @param touche    est associé à l'action
	 * @param nomAction est le nom donné à l'action
	 * @param action    l'action a executer lorsque la touche est pressée
	 */
	private static void toucheAction(JComponent composant, KeyStroke touche, String nomAction, Action action )
	{
		InputMap inputCarte = composant.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionCarte = composant.getActionMap();

		inputCarte.put(touche, nomAction);
		actionCarte.put(nomAction, action);
	}
}
