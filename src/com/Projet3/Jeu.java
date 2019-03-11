package com.Projet3;

import java.util.Scanner;

import org.apache.log4j.Logger;

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
	
	static Logger logger = Logger.getLogger(Jeu.class);
	
	/**
	 * Création d'un objet prop pour récupérer les propriétés définit dans le fichier   
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	/**
	 * Définition d'un booléen pour vérifier le choix effectuer par l'utilisateur
	 */
	boolean testChoix = false;
	
	/**
	 * Définition du nombre déterminant le choix de l'utilisateur mis à 0 par défaut
	 */
	int choixJeu = 0;
	
	/**
	 * Paramètre d'entrée des choix de la personne
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Présentation du menu principal du jeu
	 */
	public void presentationJeu (String str){
	
		
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
	 * 		Pour utilisation des méthodes après le choix effectué
	 */	
	public void presentationRecherche(String str){
		
		ModeJeuRech mode = new ModeJeuRech();
		
        System.out.println("		Recherche de Combinaison");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secrète de " + prop.valeurPropriete("jeu.size") + " chiffres en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
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
        		System.out.println("Vous avez choisi le Mode Défenseur.");
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
	 * 		Pour utilisation des méthodes après le choix effectué
	 */
	public void presentationMastermind(String str){
		        
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind");
        System.out.println();
        System.out.println("Le but du jeu est de retrouver la combinaison secrète de " + prop.valeurPropriete("jeu.size") + " chiffres compris entre 0 et " + prop.valeurPropriete("jeu.nbreCouleurs") + " inclus.");
        System.out.println("Il faut effectuer cela en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Des indications sur chacun des chiffres proposés est indiqués.");
        System.out.println("A savoir le nombre de chiffre bien placés et ceux présent dans la combinaison mais mal placé.");
        System.out.println();
        System.out.println("3 modes de jeu s'offre à vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secrète de l'ordinateur ?");
        System.out.println("    2 - Mode Défenseur  : L'ordinateur trouvera-t-il votre combinaison secrète ?");
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
        		System.out.println("Vous avez choisi le Mode Défenseur.");
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
