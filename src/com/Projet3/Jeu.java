package com.Projet3;

import java.util.Scanner;

/**
 * Jeu est une classe contenant toutes les méthodes de présentation des jeux et permettant d'effectuer son choix
 * Chacune des méthodes va faire appel aux méthodes contenu dans les classes ModeJeuRech et ModeJeuMaster.
 * @see Classe ModeJeuRech
 * @see Classe ModeJeuMaster
 * 
 * @author Loïc
 * @version 1.0
 */

public class Jeu {
	
	/**
	 * Paramètre d'entrée des choix de la personne
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Menu principal du jeu
	 * On va faire ici le choix du jeu et va nous emmener dans les méthodes de présentations de Recherche +/- ou Mastermind
	 */
	public void presentationJeu (){
	
		int choixJeu = 0;
		boolean testChoix = false;
		
		System.out.println("		Bienvenue dans Jeu de Logique.		");
		System.out.println();
		System.out.println();
		System.out.println("Dans Jeu de Logique, vous avez la possibilité de choisir entre deux jeux :");
		System.out.println("	1 - Recherche +/- : Recherche d'une combinaison chiffré grâce à des indications +, -, =	");
		System.out.println("	2 - Mastermind	  : Recherche d'une combinaison chiffré grâce à des indications sur les chiffres bien placés ou mal placés");
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
	 * Va permettre de faire le choix entre les différents modes et va emmener dans la classe ModeJeuRech pour utiliser la bonne méthode selon le choix fait
	 * 
	 * @see Classe ModeJeuRech
	 * 		Pour utilisation des méthodes après le choix effectué
	 */	
	public void presentationRecherche(){
		
        int choixMode = 0;
        boolean testMode = false;
        ModeJeuRech mode = new ModeJeuRech();

        System.out.println("		Recherche de Combinaison");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secrète en un nombre de tour prédéfini.");
        System.out.println("Pour cela des indications +, -, = seront données sur chacun des chiffres proposés.");
        System.out.println("Si le chiffre cherché est plus petit que celui proposé alors il sera indiqué par un +.");
        System.out.println("Si le chiffre cherché est plus grand que celui proposé alors il sera indiqué par un -.");
        System.out.println("Si le chiffre cherché est correct alors il sera indiqué par un =.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre à vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secrète de l'ordinateur ?");
        System.out.println("    2 - Mode Défenseur  : L'ordinateur trouvera-t-il votre combinaison secrète ?");
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
        		System.out.println("Vous avez choisi le Mode Défenseur.");
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
	 * Va permettre de faire le choix entre les différents modes et va emmener dans la classe ModeJeuMaster pour utiliser la bonne méthode selon le choix fait
	 * 
	 * @see Classe ModeJeuMaster
	 * 		Pour utilisation des méthodes après le choix effectué
	 */
	public void presentationMastermind(){
		
		int choixMode = 0;
        boolean testMode = false;
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secrète de 5 couleurs en un nombre de tour prédéfini.");
        System.out.println("Pour cela des indications sur chacun des chiffres proposés est indiqués.");
        System.out.println("A savoir le nombre de chiffre bien placés et ceux présent dans la combinaison mais mal placé.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre à vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secrète de l'ordinateur ?");
        System.out.println("    2 - Mode Défenseur  : L'ordinateur trouvera-t-il votre combinaison secrète ?");
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
        		System.out.println("Vous avez choisi le Mode Défenseur.");
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
