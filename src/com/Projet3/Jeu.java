package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Jeu est une classe contenant toutes les m�thodes de pr�sentation des jeux et permettant d'effectuer ses choix de jeu
 * Chacune des m�thodes va faire appel aux m�thodes contenu dans les classes ModeJeuRech et ModeJeuMaster.
 * @see Classe ModeJeuRech
 * @see Classe ModeJeuMaster
 * 
 * @author Lo�c
 * @version 1.0
 */

public class Jeu {
	
	/**
	 * Cr�ation d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(Jeu.class.getName());
	
	/**
	 * Cr�ation d'un objet prop pour r�cup�rer les propri�t�s d�finit dans le fichier   
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	/**
	 * D�finition d'un bool�en pour v�rifier le choix effectuer par l'utilisateur
	 */
	boolean testChoix = false;
	
	/**
	 * D�finition du nombre d�terminant le choix de l'utilisateur mis � 0 par d�faut
	 */
	int choixJeu = 0;
	
	/**
	 * Param�tre d'entr�e des choix de la personne
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Pr�sentation du menu principal du jeu
	 */
	public void presentationJeu (boolean value){
	
		
		System.out.println("		Bienvenue dans Jeu de Logique.		" + "\n\n");
		System.out.println("Dans Jeu de Logique, vous avez la possibilit� de choisir entre deux jeux :");
		System.out.println("	1 - Recherche +/- : Recherche d'une combinaison chiffr�e gr�ce � des indications +, -, =	");
		System.out.println("	2 - Mastermind	  : Recherche d'une combinaison chiffr�e gr�ce � des indications sur les chiffres bien plac�s ou mal plac�s");
		System.out.println("	3 - Quitter le jeu " + "\n");

		do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3){
                	logger.error("Le choix n'est pas bon, l'utilisateur a mis " + sc + " au lieu de 1, 2 ou 3.");
                    System.out.println("Merci de faire votre choix entre les num�ros 1, 2, 3.");
                    testChoix = false;
                }
            } else{
            	logger.error("La combinaison n'est pas bonne car ce n'est pas un entier.");
                System.out.println("Votre saisie est incorrecte. Merci de saisir le chiffre 1, 2 ou 3 selon votre choix.");
                sc.nextLine();
            }
        }while (!testChoix );
		logger.info("Le choix de l'utilisateur est " + choixJeu);
		
		switch (choixJeu){
			case 1 : 
				System.out.println("Vous avez choisi la Recherche +/-." + "\n");
				presentationRecherche(value);
				break;				
			case 2 :
				System.out.println("Vous avez choisi Mastermind." + "\n");
				presentationMastermind(value);
				break;
			case 3 :
				System.out.println();
				System.out.println("A tr�s bient�t sur Jeu de Logique !");
				System.exit(0);	
		}
	}
	
	/**
	 * Menu du jeu Recherche +/-
	 * @see Classe ModeJeuRech
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */	
	public void presentationRecherche(boolean value){
		
		ModeJeuRech mode = new ModeJeuRech();
		
        System.out.println("		Recherche de Combinaison"+ "\n");
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te de " + prop.valeurPropriete("jeu.size") + " chiffres en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Pour cela des indications +, -, = seront donn�es sur chacun des chiffres propos�s.");
        System.out.println("Si le chiffre cherch� est plus petit que celui propos� alors il sera indiqu� par un -.");
        System.out.println("Si le chiffre cherch� est plus grand que celui propos� alors il sera indiqu� par un +.");
        System.out.println("Si le chiffre propos� est correct alors il sera indiqu� par un =." + "\n");
        System.out.println("3 modes de jeu s'offrent � vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secr�te de l'ordinateur ?");
        System.out.println("    2 - Mode D�fenseur  : L'ordinateur trouvera-t-il votre combinaison secr�te ?");
        System.out.println("    3 - Mode Duel       : Affrontez l'ordinateur, qui trouvera la combinaison de l'autre en premier ?");
        System.out.println("    4 - Revenir au Menu Principal" + "\n");

        do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3 && choixJeu != 4){
                	logger.error("Le choix n'est pas bon, l'utilisateur a mis " + sc + " au lieu de 1, 2, 3 ou 4.");
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testChoix = false;
                }
            } else{
            	logger.error("La combinaison n'est pas bonne car ce n'est pas un entier.");
                System.out.println("Votre saisie est incorrecte. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testChoix );
        logger.info("Le choix de l'utilisateur est " + choixJeu);
        
        switch (choixJeu) {
        	case 1 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Challenger." + "\n");
        		mode.modeChallenger(value);
        		break;
        	case 2 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode D�fenseur."+ "\n");
        		mode.modeDefenseur(value);
        		break;
        	case 3 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Duel."+ "\n");
        		mode.modeDuel(value);
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu (value);
        		break;
        }
    }
	
	/**
	 * Menu du jeu Mastermind
	 * @see Classe ModeJeuMaster
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */
	public void presentationMastermind(boolean value){
		        
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind" + "\n");
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te de " + prop.valeurPropriete("jeu.size") + " chiffres.");
        System.out.println("Chaque chiffre a une valeur comprise entre 0 et " + prop.valeurPropriete("jeu.nbreCouleurs") + " inclus.");
        System.out.println("Il faut retrouver la combinaison en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Des indications sur chacun des chiffres propos�s est indiqu�es.");
        System.out.println("Le nombre de chiffres bien plac�s, il sera alors indiqu� le nombre avec le terme 'bien plac�(s)'.");
        System.out.println("Le nombre de chiffres mal plac�s, il sera alors indiqu� le nombre avec le terme 'pr�sent(s)'." + "\n");
        System.out.println("3 modes de jeu s'offrent � vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secr�te de l'ordinateur ?");
        System.out.println("    2 - Mode D�fenseur  : L'ordinateur trouvera-t-il votre combinaison secr�te ?");
        System.out.println("    3 - Mode Duel       : Affrontez l'ordinateur, qui trouvera la combinaison de l'autre en premier ?");
        System.out.println("    4 - Revenir au Menu Principal" + "\n");

        do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3 && choixJeu != 4){
                	logger.error("Le choix n'est pas bon, l'utilisateur a mis " + sc + " au lieu de 1, 2, 3 ou 4.");
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testChoix = false;
                }
            } else{
            	logger.error("La combinaison n'est pas bonne car ce n'est pas un entier.");
                System.out.println("Votre saisie est incorrecte. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testChoix );
        logger.info("Le choix de l'utilisateur est " + choixJeu);
        
        switch (choixJeu) {
        	case 1 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Challenger." + "\n");
        		mode.modeChallenger(value);
        		break;
        	case 2 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode D�fenseur." + "\n");
        		mode.modeDefenseur(value);
        		break;
        	case 3 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Duel." + "\n");
        		mode.modeDuel(value);
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu (value);
        		break;
        }
	}
	
	
}
