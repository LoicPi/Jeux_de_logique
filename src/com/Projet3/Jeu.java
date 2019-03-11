package com.Projet3;

import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Jeu est une classe contenant toutes les m�thodes de pr�sentation des jeux et permettant d'effectuer son choix
 * Chacune des m�thodes va faire appel aux m�thodes contenu dans les classes ModeJeuRech et ModeJeuMaster.
 * @see Classe ModeJeuRech
 * @see Classe ModeJeuMaster
 * 
 * @author Lo�c
 * @version 1.0
 */

public class Jeu {
	
	static Logger logger = Logger.getLogger(Jeu.class);
	
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
	public void presentationJeu (String str){
	
		
		System.out.println("		Bienvenue dans Jeu de Logique.		");
		System.out.println();
		System.out.println();
		System.out.println("Dans Jeu de Logique, vous avez la possibilit� de choisir entre deux jeux :");
		System.out.println("	1 - Recherche +/- : Recherche d'une combinaison chiffr� gr�ce � des indications +, -, =	");
		System.out.println("	2 - Mastermind	  : Recherche d'une combinaison chiffr� gr�ce � des indications sur les chiffres bien plac�s ou mal plac�s");
		System.out.println();
		do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2){
                    System.out.println("Merci de choisir un jeu existant.");
                    testChoix = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de saisir le chiffre 1 ou 2 selon votre choix de jeu.");
                sc.nextLine();
            }
        }while (!testChoix );
		
		switch (choixJeu){
			case 1 : 
				System.out.println("Vous avez choisi la Recherche +/-.");
				System.out.println();
				presentationRecherche(str);
				break;				
			case 2 :
				System.out.println("Vous avez choisi Mastermind.");
				System.out.println();
				presentationMastermind(str);
				break;
		}
	}
	
	/**
	 * Menu du jeu Recherche +/-
	 * @see Classe ModeJeuRech
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */	
	public void presentationRecherche(String str){
		
		ModeJeuRech mode = new ModeJeuRech();
		
        System.out.println("		Recherche de Combinaison");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te de " + prop.valeurPropriete("jeu.size") + " chiffres en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Pour cela des indications +, -, = seront donn�es sur chacun des chiffres propos�s.");
        System.out.println("Si le chiffre cherch� est plus petit que celui propos� alors il sera indiqu� par un +.");
        System.out.println("Si le chiffre cherch� est plus grand que celui propos� alors il sera indiqu� par un -.");
        System.out.println("Si le chiffre cherch� est correct alors il sera indiqu� par un =.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre � vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secr�te de l'ordinateur ?");
        System.out.println("    2 - Mode D�fenseur  : L'ordinateur trouvera-t-il votre combinaison secr�te ?");
        System.out.println("    3 - Mode Duel       : Combattez l'ordinateur, qui gagnera ?");
        System.out.println("    4 - Revenir au Menu Principal");

        do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3 && choixJeu != 4){
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testChoix = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testChoix );

        switch (choixJeu) {
        	case 1 :
        		System.out.println("Vous avez choisi le Mode Challenger.");
        		System.out.println();
        		mode.modeChallenger(str);
        		break;
        	case 2 :
        		System.out.println("Vous avez choisi le Mode D�fenseur.");
        		System.out.println();
        		mode.modeDefenseur(str);
        		break;
        	case 3 :
        		System.out.println("Vous avez choisi le Mode Duel.");
        		System.out.println();
        		mode.modeDuel(str);
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu (str);
        		break;
        }
    }
	
	/**
	 * Menu du jeu Mastermind
	 * @see Classe ModeJeuMaster
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */
	public void presentationMastermind(String str){
		        
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te de " + prop.valeurPropriete("jeu.size") + " chiffres compris entre 0 et " + prop.valeurPropriete("jeu.nbreCouleurs") + " inclus.");
        System.out.println("Il faut effectuer cela en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Des indications sur chacun des chiffres propos�s est indiqu�s.");
        System.out.println("A savoir le nombre de chiffre bien plac�s et ceux pr�sent dans la combinaison mais mal plac�.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre � vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secr�te de l'ordinateur ?");
        System.out.println("    2 - Mode D�fenseur  : L'ordinateur trouvera-t-il votre combinaison secr�te ?");
        System.out.println("    3 - Mode Duel       : Combattez l'ordinateur, qui gagnera ?");
        System.out.println("    4 - Revenir au Menu Principal");
        System.out.println();

        do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3 && choixJeu != 4){
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testChoix = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testChoix );

        switch (choixJeu) {
        	case 1 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Challenger.");
        		System.out.println();
        		mode.modeChallenger(str);
        		break;
        	case 2 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode D�fenseur.");
        		System.out.println();
        		mode.modeDefenseur(str);
        		break;
        	case 3 :
        		System.out.println();
        		System.out.println("Vous avez choisi le Mode Duel.");
        		System.out.println();
        		mode.modeDuel(str);
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu (str);
        		break;
        }
	}
	
	
}
