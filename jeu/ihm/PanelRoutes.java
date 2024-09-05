package jeu.ihm;

import jeu.Controleur;
import jeu.metier.Route;
import jeu.metier.Sommet;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.table.TableModel;
import java.awt.event.*;


import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe créé le panel pour modifier ou créer les routes.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class PanelRoutes extends JPanel implements ActionListener {
    private Controleur ctrl;
    private JTable table;
    private JScrollPane scrollPaneLstSommetDep;
    private JScrollPane scrollPaneLstSommetArrive;
    private JPanel panelInput;
    private JTextField inputRoute;
    private JButton btnAjouteRoute;

    private JLabel lblErreur;

    private JComboBox<String> lstSommetDep;
    private JComboBox<String> lstSommetArrive;

    /**
     * Constructeur du Panel d'édition des routes.
     */
    public PanelRoutes(Controleur ctrl) {

        this.ctrl = ctrl;
        this.setLayout(new GridLayout(1, 2));
        this.ajouterTabRoute();
        this.initListe();
        this.panelInput();
        this.setVisible(true);
    }

    /**
     * Méthode qui initialise tous les composants du panel d'édition des routes.
     */
    public void panelInput() {
        this.panelInput = new JPanel();

        this.panelInput.setLayout(new GridLayout(5, 2));

        this.inputRoute = new JTextField();

        this.btnAjouteRoute = new JButton("Ajouter/Supprimer");

        this.panelInput.add(new JLabel("SommetDep : "));
        this.panelInput.add(scrollPaneLstSommetDep);

        this.panelInput.add(new JLabel("SommetArr : "));
        this.panelInput.add(scrollPaneLstSommetArrive);

        this.panelInput.add(new JLabel("Nombre Troncons : "));
        this.panelInput.add(inputRoute);

        this.panelInput.add(this.lblErreur = new JLabel(""));
        this.lblErreur.setFont(new Font("Arial", Font.BOLD, 15));

        this.panelInput.add(btnAjouteRoute);

        this.btnAjouteRoute.addActionListener(this);
        this.add(panelInput);

    }

    public void initListe()
    {
	/**
	 * Initialise la liste contenant toutes les routes.
	 */
        ArrayList<Sommet> tabVille = this.ctrl.getTabSommet();

        // Liste ville
        String[] data = new String[tabVille.size()];

        for (int cpt = 0; cpt < data.length; cpt++) {

            data[cpt] = (tabVille.get(cpt).getId() +  " " + tabVille.get(cpt).getNumSom()+tabVille.get(cpt).getNomCoul());

        }

        lstSommetDep = new JComboBox<>(data);
        lstSommetArrive = new JComboBox<>(data);

        // Add the list to a scroll pane (allows scrolling if needed)
        this.scrollPaneLstSommetDep = new JScrollPane(lstSommetDep);
        this.scrollPaneLstSommetArrive = new JScrollPane(lstSommetArrive);

        if (this.panelInput != null) {
            this.panelInput.add(scrollPaneLstSommetDep);
            this.panelInput.add(scrollPaneLstSommetArrive);
            this.repaint();
        }

    }


    /**
     * Méthode qui ajoute une route à la liste de routes de la carte.
     */
    public void ajouterTabRoute()
    {
        // Tableau contenant tout les routes
        List<Route> lstRoute = ctrl.getTabRoute();
        String[][] data = new String[lstRoute.size()][3];

        // nom des colonnes
        String[] columnNames = {"SommetDep", "SommetArr", "nbTroncons"};

        for ( int lig = 0; lig < ( lstRoute ).size(); lig++ )
        {

            data[ lig ][ 0 ] = ( lstRoute.get( lig ) ).getSommetDep().getId() +  " " + lstRoute.get(lig).getSommetDep().getNumSom() + lstRoute.get(lig).getSommetDep().getNomCoul();
            data[ lig ][ 1 ] = ( lstRoute.get( lig ) ).getSommetArr().getId() +  " " + lstRoute.get(lig).getSommetArr().getNumSom() + lstRoute.get(lig).getSommetArr().getNomCoul();
            data[ lig ][ 2 ] = ( lstRoute.get( lig ) ).getNbTroncons() +"";
        }

        // Create a table model and set the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        this.table = new JTable(model);

        // Add the table to a scroll pane (allows scrolling if needed)
        JScrollPane scrollPane = new JScrollPane(table);

        this.add(scrollPane);
        this.repaint();
    }





    /**
     * Réalise une action lorsqu'un bouton ou le tableau est appuyé
     * @param e est un événement lié à un composant du panel
     */
    public void actionPerformed( ActionEvent e ) {
        String villeDep = null;
        String villeArr = null;
        int troncons = 0;



        try{
            if(!this.inputRoute.getText().isBlank())
                Integer.parseInt(this.inputRoute.getText());
        }catch (Exception ex)
        {
            this.lblErreur.setText("<html> Seul les entiers  <br> sont autorisés. </html>");
            return;
        }



        // Get the selected row index
        int selectedRowIndex = table.getSelectedRow();

        // Check if a row is selected
        if (selectedRowIndex != -1) {
            // Get the table model
            TableModel model = table.getModel();

            // Get data from the selected row
            villeDep = (String) model.getValueAt(selectedRowIndex, 0);
            villeDep = villeDep.substring(0,villeDep.indexOf(" "));
            villeArr = (String) model.getValueAt(selectedRowIndex, 1);
            villeArr = villeArr.substring(0,villeArr.indexOf(" "));
            troncons = Integer.parseInt((String) model.getValueAt(selectedRowIndex, 2));

        } else if (!this.inputRoute.getText().isBlank())
        {
            villeDep = (String) this.lstSommetDep.getSelectedItem();
            villeDep = villeDep.substring(0,villeDep.indexOf(" "));
            villeArr = (String) this.lstSommetArrive.getSelectedItem();
            villeArr = villeArr.substring(0,villeArr.indexOf(" "));
            troncons = Integer.parseInt(this.inputRoute.getText());
        }


        if (e.getSource() == this.btnAjouteRoute)
        {
            if ((this.inputRoute.getText().isBlank()) &&
                    selectedRowIndex == -1
            ) {
                this.lblErreur.setText("<html> Tous les champs  <br> ne sont pas complétés. </html>");
                return;
            } else if (!this.inputRoute.getText().isBlank() && (Integer.parseInt(this.inputRoute.getText()) < 0 || Integer.parseInt(this.inputRoute.getText()) > 2)) {
                this.lblErreur.setText("<html> Valeurs comprises entre  <br> 0 et 2. </html>");
                return;
            } else{


                Sommet sDep = ctrl.rechercheSommet(villeDep);
                Sommet sArr = ctrl.rechercheSommet(villeArr);

                if((ctrl.rechercheRoute(sDep, sArr) != null || ctrl.rechercheRoute(sArr, sDep) != null) && selectedRowIndex == -1)
                {
                    this.lblErreur.setText("<html> Ce sommet existe déjà.</html>");
                    return;
                }

                this.lblErreur.setText("");


                this.ctrl.ajouterOuSupprimerRoute(sDep, sArr, troncons);

                //Raffraichir le tableau
                this.removeAll();
                this.ajouterTabRoute();
                this.panelInput();
                this.revalidate();


                // DefaultTableModel model = (DefaultTableModel) table.getModel();
                // model.addRow(new Object[]{ txtNumero.getText(),txtNomCouleur.getText(), txtX.getText(), txtY.getText()});
                this.ctrl.MajFrameModification();
            }
            this.ctrl.MajFrameModification();
        }
    }
}
