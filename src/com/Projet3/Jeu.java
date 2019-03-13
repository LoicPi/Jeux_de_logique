package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Jeu est une classe contenant toutes les méthodes de présentation des jeux et permettant d'effectuer ses choix de jeu
 * Chacune des méthodes va faire appel aux méthodes contenu dans les classes ModeJeuRech et ModeJeuMaster.
 * @see Classe ModeJeuRech
 * @see Classe ModeJeuMaster
 * 
 * @author Loïc
 * @version 1.0
 */

public class Jeu {
	
	/**
	 * Création d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(Jeu.class.getName());
	
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
	public void presentationJeu (boolean value){
	
		
		System.out.println("		Bienvenue dans Jeu de Logique.		" + "\n\n");
		System.out.println("Dans Jeu de Logique, vous avez la possibilité de choisir entre deux jeux :");
		System.out.println("	1 - Recherche +/- : Recherche d'une combinaison chiffrée grâce à des indications +, -, =	");
		System.out.println("	2 - Mastermind	  : Recherche d'une combinaison chiffrée grâce à des indications sur les chiffres bien placés ou mal placés");
		System.out.println("	3 - Quitter le jeu " + "\n");

		do {
            System.out.println("Quel est votre choix ?");
            testChoix = sc.hasNextInt();
            if(testChoix){
                choixJeu = sc.nextInt();
                if (choixJeu != 1 && choixJeu != 2 && choixJeu != 3){
                	logger.error("Le choix n'est pas bon, l'utilisateur a mis " + sc + " au lieu de 1, 2 ou 3.");
                    System.out.println("Merci de faire votre choix entre les numéros 1, 2, 3.");
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
				System.out.println("A très bientôt sur Jeu de Logique !");
				System.exit(0);	
		}
	}
	
	/**
	 * Menu du jeu Recherche +/-
	 * @see Classe ModeJeuRech
	 * 		Pour utilisation des méthodes après le choix effectué
	 */	
	public void presentationRecherche(boolean value){
		
		ModeJeuRech mode = new ModeJeuRech();
		
        System.out.println("		Recherche de Combinaison"+ "\n");
        System.out.println("Le but du jeu est de retrouver la combinaison secrète de " + prop.valeurPropriete("jeu.size") + " chiffres en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Pour cela des indications +, -, = seront données sur chacun des chiffres proposés.");
        System.out.println("Si le chiffre cherché est plus petit que celui proposé alors il sera indiqué par un -.");
        System.out.println("Si le chiffre cherché est plus grand que celui proposé alors il sera indiqué par un +.");
        System.out.println("Si le chiffre proposé est correct alors il sera indiqué par un =." + "\n");
        System.out.println("3 modes de jeu s'offrent à vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secrète de l'ordinateur ?");
        System.out.println("    2 - Mode Défenseur  : L'ordinateur trouvera-t-il votre combinaison secrète ?");
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
        		System.out.println("Vous avez choisi le Mode Défenseur."+ "\n");
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
	 * 		Pour utilisation des méthodes après le choix effectué
	 */
	public void presentationMastermind(boolean value){
		        
        ModeJeuMaster mode = new ModeJeuMaster();

        System.out.println("		Mastermind" + "\n");
        System.out.println("Le but du jeu est de retrouver la combinaison secrète de " + prop.valeurPropriete("jeu.size") + " chiffres.");
        System.out.println("Chaque chiffre a une valeur comprise entre 0 et " + prop.valeurPropriete("jeu.nbreCouleurs") + " inclus.");
        System.out.println("Il faut retrouver la combinaison en " +prop.valeurPropriete("jeu.nbreTour") + " tours maximum.");
        System.out.println("Des indications sur chacun des chiffres proposés est indiquées.");
        System.out.println("Le nombre de chiffres bien placés, il sera alors indiqué le nombre avec le terme 'bien placé(s)'.");
        System.out.println("Le nombre de chiffres mal placés, il sera alors indiqué le nombre avec le terme 'présent(s)'." + "\n");
        System.out.println("3 modes de jeu s'offrent à vous : ");
        System.out.println("    1 - Mode Challenger : Trouverez vous la combinaison secrète de l'ordinateur ?");
        System.out.println("    2 - Mode Défenseur  : L'ordinateur trouvera-t-il votre combinaison secrète ?");
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
        		System.out.println("Vous avez choisi le Mode Défenseur." + "\n");
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
