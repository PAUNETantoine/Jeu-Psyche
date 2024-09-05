package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Materiaux;
import jeu.metier.Sommet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.awt.*;
import java.awt.event.*;

import java.util.List;

/**
 * Cette classe créé le panel pour modifier ou créer les sommets.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */

public class PanelSommet extends JPanel  implements ActionListener
{
	private Controleur ctrl             ;
	private JTable     table            ;
	private JPanel     panelInput       ;
	private JTextField txtNumero        ;
	private JComboBox<String> lstCouleur;
	private JComboBox<String> lstMateriaux;
	private JTextField txtX             ;
	private JTextField txtY             ;

	private JLabel     lblErreur        ;

	private JButton    btnAjouterSommet ;

	/**
	 * Constructeur du Panel de modification ou création des sommets.
	 * @param ctrl qui est le controleur qui gère l'application
	 */
	public PanelSommet( Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout( new GridLayout(  1, 2 ) );
		this.ajouterTabSommet();;
		this.panelInput();
		//this.ajouterBoutonModifier();
		this.setVisible( true );
	}


	/**
	 * Methode qui permet de créer un panel contenant les textfields et labels du PanelSommet
	 */
	public void panelInput()
	{
		this.panelInput    = new JPanel();
	
		this.txtNumero     = new JTextField();
		this.lstCouleur    = new JComboBox<String>(new String[]{"Vert","Jaune","Rouge","Bleu","Gris","Marron"});

		String[] tmp = new String[this.ctrl.getLstMateriaux().size()+1];
		ctrl.getLstMateriaux().toArray(tmp);
		String tmpS = tmp[0];
		tmp[0] = "Aleatoire";

		tmp[this.ctrl.getLstMateriaux().size()] = tmpS;
		this.lstMateriaux  = new JComboBox<String>(tmp);

		this.txtX          = new JTextField();
		this.txtY          = new JTextField();
		
		this.panelInput.setLayout( new GridLayout( 6 , 2 ) );
			

		this.btnAjouterSommet = new JButton( "Ajouter / Supprimer" );
			
		
		this.panelInput.add( new JLabel("Numero : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.txtNumero    );

		this.panelInput.add( new JLabel("Couleur : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.lstCouleur);

		this.panelInput.add( new JLabel("Matériaux : "),  JPanel.RIGHT_ALIGNMENT );
		this.panelInput.add( this.lstMateriaux);
				
		this.panelInput.add( new JLabel("X : "      ),  JPanel.RIGHT_ALIGNMENT  );
		this.panelInput.add( this.txtX         );
		
		this.panelInput.add( new JLabel("Y : "      ) );
		this.panelInput.add( this.txtY         );

		this.panelInput.add(this.lblErreur = new JLabel(""));
		this.lblErreur.setFont(new Font("Arial", Font.BOLD, 15));
		this.panelInput.add( btnAjouterSommet );


			
		this.btnAjouterSommet.addActionListener( this );
				
		this.add( panelInput );
    }

	/**
	 * Methode qui permet de créer le tableau qui contient les sommets déjà existants
	 */
	public void ajouterTabSommet()
	{
		// Tableau contenant tout les routes
		List<Sommet> lstSommet = ctrl.getTabSommet();
		String[][]  data     = new String[ lstSommet.size() ][ 5 ];
			
		// nom des colonnes
		String[] columnNames = {"ID","Numero", "Couleur", "X", "Y"};
			
		for ( int lig = 0; lig < ( lstSommet ).size(); lig++ )
		{
			data[ lig ][ 0 ] = ( lstSommet.get( lig ) ).getId() + "";
			data[ lig ][ 1 ] = ( lstSommet.get( lig ) ).getNumSom () + "";
			data[ lig ][ 2 ] = ( lstSommet.get( lig ) ).getNomCoul() ;
			data[ lig ][ 3 ] = ( lstSommet.get( lig ) ).getX() + ""  ;
			data[ lig ][ 4 ] = ( lstSommet.get( lig ) ).getY() + ""  ;

		}

		// Create a table model and set the data and column names
		DefaultTableModel model = new DefaultTableModel( data, columnNames );

		this.table = new JTable( model );

		// Add the table to a scroll pane (allows scrolling if needed)
		JScrollPane scrollPane = new JScrollPane( table );

		this.add( scrollPane );
		this.repaint();
				
	}
	
	/**
	 * Réalise une action lorsqu'un bouton ou le tableau est appuyé
	 * @param e est un événement lié à un composant du panel
	 */
	public void actionPerformed( ActionEvent e ) 
	{
		int numVille = 0;
		int idVille    = 0;
		String nomVile =null;
		int x       =0;
		int y       =0;
		// Get the selected row index
		int selectedRowIndex = table.getSelectedRow();

		// Check if a row is selected
		if (selectedRowIndex != -1) {
			// Get the table model
			TableModel model = table.getModel();

			// Get data from the selected row
			idVille = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 0));
			numVille = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 1 ) );
			x       = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 3 ) );
			y       = Integer.parseInt( (String) model.getValueAt(selectedRowIndex, 4 ) );
			nomVile = (String) model.getValueAt(selectedRowIndex, 2 );

		}
		else if ( !((
						this.txtNumero.getText().isBlank()     ||
						this.txtX.getText().isBlank()          ||
						this.txtY.getText().isBlank()         )&&
						selectedRowIndex == -1
					)
				)
		{
			try{
				numVille = Integer.parseInt( (String) this.txtNumero.getText() );
				x       = Integer.parseInt( (String) this.txtX.getText()      );
				y       = Integer.parseInt( (String) this.txtY.getText()      );
				nomVile = (String) this.lstCouleur.getSelectedItem()           ;
			}catch (Exception ex)
			{
				this.lblErreur.setText("<html> Numéro, x et y  <br> doivent être entiers. </html>");
				return;
			}
		}

		if(numVille > 99 || numVille < 0)
		{
			this.lblErreur.setText("<html> Numéro compris entre  <br> 0 et 99. </html>");
			return;
		}

		if(x < 0 || y < 0)
		{
			this.lblErreur.setText("<html> x et y doivent <br> être > 0. </html>");
			return;
		}


		if (e.getSource() == this.btnAjouterSommet)
		{
			if ((
				 this.txtNumero.getText().isBlank()     ||
				 this.txtX.getText().isBlank()          ||
				 this.txtY.getText().isBlank()         )&&
				 selectedRowIndex == -1

			   )
			{
				this.lblErreur.setText("<html> Tous les champs  <br> ne sont pas complétés. </html>");
			}
			else
			{
				this.lblErreur.setText("");

				if(idVille != 1 && selectedRowIndex != -1)
				{
					this.ctrl.ajouterOuSupprimerSommet(idVille , numVille, nomVile,x,y, null,false);

				}else if(selectedRowIndex == -1)
				{
					if(this.ctrl.getTabSommet().size() < 41)
					{
						String matNom = (String) this.lstMateriaux.getSelectedItem();
						Materiaux tmpMat;

						if(matNom.equals("Aleatoire"))
						{
							int rndm = (int)(Math.random()*(this.ctrl.getLstMateriaux().size()));
							tmpMat = new Materiaux(this.ctrl.getLstMateriaux().remove(rndm));
						}else{
							tmpMat = new Materiaux(this.ctrl.getLstMateriaux().remove(this.ctrl.getLstMateriaux().indexOf(matNom)));
						}

						this.ctrl.ajouterOuSupprimerSommet(Controleur.nbSommets++ , numVille, nomVile,x,y,tmpMat,false);
					}
					else
					{
						this.lblErreur.setText("<html>Nombre de sommets maximum <br> dépassé. </html>");
						return;
					}
				}else{
					this.lblErreur.setText("<html>Impossible de supprimer <br> le sommet principal. </html>");
					return;
				}



				//Raffraichir le tableau
				this.removeAll();
				this.ajouterTabSommet();
				this.panelInput();
				this.revalidate();
				this.ctrl.MajFrameModification();
			}
		}
	}
}