package jeu.metier;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Cette classe représente les matériaux utilisés dans le jeu. Le terme exacte de matériau peut changer en fonction u thème utilisé dans le jeu.
 * @author Antione Paunet,			IUT du Havre
 * @author Mael Vauthier,			IUT du Havre
 * @author Martin Ravenel,			IUT du Havre
 * @author Fanch EVEN,				IUT du Havre
 * @author Anas AARAB,				IUT du Havre
 * @version 1.0 , 2024-05-23
 */
public class Materiaux implements IRessource
{
	private String 	nom;
	private Couleur couleur;


	public static int nbPiece;

	//Permet de vérifier qu'on génère un matériau qui existe bel et bien.
	public static final ArrayList<String> verif = new ArrayList<>(Arrays.asList("AU", "AG", "FE", "AL", "NI", "PT", "CO", "TI", "NR","null"));

	/**
	 * Constructeur de Matériau.
	 * @param nom le nom du matériau
	 */
	public Materiaux(String nom)
	{
		if(!Materiaux.verif.contains(nom))
		{
			return;
		}

		this.nom 	= nom;

		switch (this.nom)
		{
			case "AU" : this.couleur = Couleur.OR; 		break;
			case "AG" : this.couleur = Couleur.GRIS; 	break;
			case "FE" : this.couleur = Couleur.JAUNE; 	break;
			case "AL" : this.couleur = Couleur.PALE; 	break;
			case "NI" : this.couleur = Couleur.BLEU; 	break;
			case "PT" : this.couleur = Couleur.VIOLET; 	break;
			case "CO" : this.couleur = Couleur.MARRON; 	break;
			case "TI" : this.couleur = Couleur.VERT; 	break;
			case "NR" : this.couleur = Couleur.CIAN; 	break;
			case "null" : this.couleur = Couleur.DEPART; 	break;
		}

		Materiaux.nbPiece++;
	}

	/**
	 * Renvoie la couleur du matériau.
	 * @return la couleur du matériau
	 */
	public Couleur getCouleur()
	{
		return this.couleur;
	}

	/**
	 * Renvoie le nom du matériau.
	 * @return le nom du matériau
	 */
	public String getNom()
	{
		return this.nom;
	}

	/**
	 * Renvoie le nom du Matériau sous forme texte.
	 * @return le nom du Matériau
	 */
	public String toString()
	{
		return this.nom;
	}
}