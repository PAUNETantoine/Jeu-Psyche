package jeu.ihm;

import jeu.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Cette classe créé la Frame sur laquelle les joueurs vont jouer. Elle affiche le plateau et tous les pions des joueurs.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class FrameJeu extends JFrame implements ActionListener
{
	private PanelCarte 	panelCarte;

	private JMenuItem menuiAbandonner;
	private JMenuItem menuiAnnuler, menuiRetablir, menuiAllerA;

	private Controleur 	ctrl;

	/**
	 * Constructeur de la Frame du jeu
	 */
	public FrameJeu(Controleur ctrl)
	{
		this.setTitle("L'Âge de Psyché");
		this.setSize    ( 1269,1080 );
		this.setLocation(  0, 0 );
		this.ctrl = ctrl;
		this.setResizable(false);


		// Création et ajout du Panel
		this.panelCarte = new PanelCarte(ctrl);
		this.add(this.panelCarte);

		// Création et ajout de la barre de menu
		JMenuBar menuBar  = new JMenuBar();
		JMenu menuOptions = new JMenu("Options");

		this.menuiAbandonner  = new JMenuItem ("Quitter sans sauvegarder" );

		menuOptions.add(this.menuiAbandonner );
		menuBar.add(menuOptions);

		if( this.ctrl.getEstScenar() )
		{
			JMenu menuScenar = new JMenu("Scénario");

			this.menuiAnnuler  = new JMenuItem ("Annuler la dernière action"  );
			this.menuiRetablir = new JMenuItem ("Rétablir la dernière action" );
			this.menuiAllerA   = new JMenuItem ("Aller à l'action n° " );

			menuScenar.add(this.menuiAnnuler );
			menuScenar.add(this.menuiRetablir);
			menuScenar.addSeparator();
			menuScenar.add(this.menuiAllerA  );

			menuBar.add(menuScenar);
			menuScenar.setMnemonic('S');

			this.menuiAnnuler .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK) );
			this.menuiRetablir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK) );
			this.menuiAllerA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK) );

			this.menuiAnnuler .addActionListener(this);
			this.menuiRetablir.addActionListener(this);
			this.menuiAllerA  .addActionListener(this);
		}

		this.setJMenuBar( menuBar );

		menuOptions.setMnemonic('O');
		this.menuiAbandonner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK) );

		this.menuiAbandonner.addActionListener(this);

		// Gestion de la fermeture de la fenêtre
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				try
				{
					ctrl.getEditionFichier().sauvegarde();
					System.exit(0);	
				}
				catch( IOException ex ) { throw new RuntimeException(ex); }
			}
		});
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Ne ferme pas automatiquement
		this.setVisible(true);
	}

	/**
	 * Réalise une action lorsqu'on clique sur la menubar
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed ( ActionEvent e )
	{
		if ( e.getSource() == this.menuiAbandonner ) // CTRL + Q
		{
			if( JOptionPane.showConfirmDialog(null,"Êtes-vous sur ?\nVotre partie ne sera pas sauvegardée.") == JOptionPane.YES_OPTION )
			System.exit(0);
		}
		
		if ( e.getSource() == this.menuiAnnuler ) // CTRL + Z
		{
			if ( ( this.ctrl.getNbEtapeScenario() - 1 ) > -2 )					
				this.ctrl.setNbEtapeScenario( this.ctrl.getNbEtapeScenario() - 1 );			
			
			try
			{
				this.ctrl.getEditionFichier().lectureFichier("data.txt", false);
				
			}
			catch ( IOException e1 )
			{
				System.out.println(e1.getStackTrace());
			}	
			
			try
			{
				this.ctrl.getEditionFichier().lireScenario(this.ctrl.getNbScenario(),this.ctrl.getNbEtapeScenario(),true);
				
			}
			catch ( IOException e2 )
			{
				System.out.println( e2.getStackTrace() );
			}
			this.panelCarte.repaint();
		}
		
		if ( e.getSource() == this.menuiRetablir ) // CTRL + Y
		{
			try
			{
				this.ctrl.getEditionFichier().lireScenario(this.ctrl.getNbScenario(),ctrl.getNbEtapeScenario(),false);
			}
			catch ( IOException e3 )
			{
				System.out.println( e3.getStackTrace() );
			}
			
			this.ctrl.setNbEtapeScenario( this.ctrl.getNbEtapeScenario() + 1 );
		}

		if ( e.getSource() == this.menuiAllerA ) // CTRL + G
		{
			try
			{
				int action = Integer.parseInt( JOptionPane.showInputDialog("Entrez le numéro de l'action : "));

				this.ctrl.getEditionFichier().lireScenario(this.ctrl.getNbScenario(), action,true);
				this.ctrl.setNbEtapeScenario(this.ctrl.getNbEtapeScenario() + action);

			}
			catch( Exception exp )
			{
				JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre correct", "N° d'action invalide", JOptionPane.ERROR_MESSAGE);
			}

		}
	}	

	public void majIHM(){this.panelCarte = new PanelCarte(ctrl);this.add(this.panelCarte);this.setVisible(true);}

	public PanelCarte getPanelCarte (){return this.panelCarte;}

/*
	public static void main( String[] args ) throws IOException
	{
		new FrameJeu(new Controleur());
	}
*/
}