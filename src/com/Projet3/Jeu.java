package com.Projet3;

import java.util.Scanner;

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
	
	/**
	 * Param�tre d'entr�e des choix de la personne
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Menu principal du jeu
	 * On va faire ici le choix du jeu et va nous emmener dans les m�thodes de pr�sentations de Recherche +/- ou Mastermind
	 */
	public void presentationJeu (){
	
		int choixJeu = 0;
		boolean testChoix = false;
		
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
				presentationRecherche();
				break;				
			case 2 :
				System.out.println("Vous avez choisi Mastermind.");
				System.out.println();
				presentationMastermind();
				break;
		}
	}
	
	/**
	 * Menu du jeu Recherche +/-
	 * Va permettre de faire le choix entre les diff�rents modes et va emmener dans la classe ModeJeuRech pour utiliser la bonne m�thode selon le choix fait
	 * 
	 * @see Classe ModeJeuRech
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */	
	public void presentationRecherche(){
		
        int choixMode = 0;
        boolean testMode = false;
        ModeJeuRech mode = new ModeJeuRech();

        System.out.println("		Recherche de Combinaison");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te en un nombre de tour pr�d�fini.");
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
            testMode = sc.hasNextInt();
            if(testMode){
                choixMode = sc.nextInt();
                if (choixMode != 1 && choixMode != 2 && choixMode != 3 && choixMode != 4){
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testMode = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testMode );

        switch (choixMode) {
        	case 1 :
        		System.out.println("Vous avez choisi le Mode Challenger.");
        		System.out.println();
        		mode.modeChallenger();
        		break;
        	case 2 :
        		System.out.println("Vous avez choisi le Mode D�fenseur.");
        		System.out.println();
        		mode.modeDefenseur();
        		break;
        	case 3 :
        		System.out.println("Vous avez choisi le Mode Duel.");
        		System.out.println();
        		mode.modeDuel();
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu ();
        		break;
        }
    }
	
	/**
	 * Menu du jeu Mastermind
	 * Va permettre de faire le choix entre les diff�rents modes et va emmener dans la classe ModeJeuMaster pour utiliser la bonne m�thode selon le choix fait
	 * 
	 * @see Classe ModeJeuMaster
	 * 		Pour utilisation des m�thodes apr�s le choix effectu�
	 */
	public void presentationMastermind(){
		
		int choixMode = 0;
        boolean testMode = false;
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secr�te de 5 couleurs en un nombre de tour pr�d�fini.");
        System.out.println("Pour cela des indications sur chacun des chiffres propos�s est indiqu�s.");
        System.out.println("A savoir le nombre de chiffre bien plac�s et ceux pr�sent dans la combinaison mais mal plac�.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre � vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secr�te de l'ordinateur ?");
        System.out.println("    2 - Mode D�fenseur  : L'ordinateur trouvera-t-il votre combinaison secr�te ?");
        System.out.println("    3 - Mode Duel       : Combattez l'ordinateur, qui gagnera ?");
        System.out.println("    4 - Revenir au Menu Principal");

        do {
            System.out.println("Quel est votre choix ?");
            testMode = sc.hasNextInt();
            if(testMode){
                choixMode = sc.nextInt();
                if (choixMode != 1 && choixMode != 2 && choixMode != 3 && choixMode != 4){
                    System.out.println("Merci de choisir entre les choix 1, 2, 3, 4.");
                    testMode = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3, 4.");
                sc.nextLine();
            }
        }while (!testMode );

        switch (choixMode) {
        	case 1 :
        		System.out.println("Vous avez choisi le Mode Challenger.");
        		System.out.println();
        		mode.modeChallenger();
        		break;
        	case 2 :
        		System.out.println("Vous avez choisi le Mode D�fenseur.");
        		System.out.println();
        		mode.modeDefenseur();
        		break;
        	case 3 :
        		System.out.println("Vous avez choisi le Mode Duel.");
        		System.out.println();
        		mode.modeDuel();
        		break;
        	case 4 :
        		System.out.println();
        		presentationJeu ();
        		break;
        }
	}
	
	
}
