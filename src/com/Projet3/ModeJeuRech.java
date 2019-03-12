package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * ModeJeuRech est une classe contenant toutes les méthodes des différents modes contenu dans le jeu Recherche+/-
 * Chacune des méthodes va faire appel aux méthodes contenu dans la classe CreationJeu.
 * @see Classe CreationJeu
 * @see Classe Jeu
 * 
 * @author Loïc
 * @version 1.0
 */

public class ModeJeuRech {
	
	/**
	 * Création d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(ModeJeuRech.class.getName());
	
	/**
	 * Créer un objet jeu afin de pouvoir l'appeler dans les différentes méthodes
	 */
	CreationJeu jeu = new CreationJeu();
	
	/**
	 * Création d'un objet prop pour récupérer les propriétés définit dans le fichier
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	
	/**
	 * Tableau d'entier utilisé dans les méthodes pour contenir la combinaison secrete de l'humain
	 */
	int[] combiH;
	
	/**
	 * Tableau d'entier utilisé dans les méthodes pour contenir la proposition de l'humain
	 */
	int[] propoH;

	/**
	 * Définiton du nombre de tour, commencant à 1
	 */
	int tour = 1;
	
	/**
	 * Définition de nombre de tour maximum	
	 */
	int tourMax = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * Tableau de chaine de caractere pour contenir la reponse à la proposition
	 */
	String [] reponse = new String[jeu.getSize()];
	
	/**
	 * Définition de booléen pour vérifier si la réponse est bonne
	 */
	boolean verifR = false;
	
	/**
	 * Définition d'un booléen pour vérifier le nombre de tour
	 */
	boolean verifV = false;
	
	/**
	 * Permet de créer un objet Scanner qui va récupérer les entrées des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Crée le mode Challenger	 * 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 * @param value
	 * 		Booléen pour vérifier si le mode Challenger est lancé en mode développeur ou non
	 */
    public void modeChallenger(boolean value) {
    	
    	jeu.combiOrdi();
    	logger.info("La combinaison secrète est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.println();
    		System.out.println();
    	}
    	
    	while (!verifR && !verifV){
    		propoH = jeu.combiHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
        	reponse = jeu.compareProposition(jeu.getCombiO(), propoH);
        	System.out.println(" -> Réponse à votre proposition : " + jeu.afficheReponse(reponse) + "\n");
        	logger.info("Le jeu nous donne comme réponse : " + jeu.afficheReponse(reponse));
        	verifR = jeu.verifReponse(reponse);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'utilisateur a trouvé la combinaison.");
        	if (verifR){
        		System.out.println("Vous avez trouvé la combinaison.");
        		logger.info("L'utilisateur a trouvé la combinaison");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.print("Vous n'avez pas trouvé la combinaison. Celle-ci était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'utilisateur n'a pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour++;
        	logger.info("On passe au tour : " + tour);
    	}     
    }
    
    /**
	 * Crée le mode Defenseur
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 * @param value
	 * 		Booléen pour vérifier si le mode Defenseur est lancé en mode développeur ou non
	 */
    public void modeDefenseur(boolean value){
    	
    	combiH = jeu.combiHumain("Votre combinaison secrète : ");
    	logger.info("La combinaison donné par l'utilisateur est : " + jeu.afficheCombi(combiH));
    	System.out.print("\n\n");
    	
    	while (!verifR && !verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		logger.info("La proposition donné par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> Réponse à sa proposition : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	System.out.println(jeu.afficheReponse(reponse) +"\n");
        	logger.info("Le jeu nous donne comme réponse : " + jeu.afficheReponse(reponse));
        	verifR = jeu.verifReponse(reponse);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'ordinateur a trouvé la combinaison.");
        	if (verifR){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        		logger.info("L'ordinateur a trouvé la combinaison");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        		logger.info("L'ordinateur n'a pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour ++;
        	logger.info("On passe au tour : " + tour);
    	}     	
    }
    
    /**
	 * Crée le mode Duel 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 * @param value
	 * 		Booléen pour vérifier si le mode Duel est lancé en mode développeur ou non
	 */
    public void modeDuel(boolean value){
    	
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	
    	combiH = jeu.combiHumain("Votre combinaison secrète : ");
    	logger.info("La combinaison secrète de l'utilisateur est : " + jeu.afficheCombi(combiH));
    	jeu.combiOrdi();
    	logger.info("La combinaison secrète de l'ordinateur est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.println("\n\n");
    	
    	while (!verifH && !verifO && !verifV){
    		
    		propoH = jeu.combiHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		reponseH = jeu.compareProposition(jeu.getCombiO(), propoH);
        	System.out.println(" -> Réponse à votre proposition : " + jeu.afficheReponse(reponseH));
        	logger.info("Le jeu nous donne comme réponse pour l'utilisateur : " + jeu.afficheReponse(reponseH));
    		jeu.propoOrdi(reponseO,tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		logger.info("La proposition donné par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	System.out.print(" -> Réponse à sa proposition : " + jeu.afficheReponse(reponseO) +"\n");
        	logger.info("Le jeu nous donne comme réponse pour l'ordinateur : " + jeu.afficheReponse(reponseO));
        	verifH = jeu.verifReponse(reponseH);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifH + " pour savoir si l'utilisateur a trouvé la combinaison.");
        	verifO = jeu.verifReponse(reponseO);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifO + " pour savoir si l'ordinateur a trouvé la combinaison.");
        	if (verifH && !verifO){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur.");
        		logger.info("L'utilisateur a trouvé la combinaison");
        		rejouer(value);
        	}
        	if (!verifH && verifO){
        		System.out.print("L'ordinateur a trouvé votre combinaison. La combinaison de l'ordinateur était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur a trouvé la combinaison de l'utilisateur.");
        		rejouer(value);
        	}
        	if (verifH && verifO){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		logger.info("L'ordinateur et l'utilisateur ont trouvé en même temps les combinaisons secrètes.");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.print("Match nul. Les combinaison n'ont pas été trouvé dans le temps imparti. La combinaison de l'ordinateur était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur et l'utilisateur n'ont pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour++;
        	logger.info("On passe au tour : " + tour);
    	}
    }
    
    /**
     * Permet de mettre le mot placé au singulier ou au pluriel
     * @param nb
     * 		Nombre de proposition restante
     * @return
     * 		Retourne le mot " proposition(s) " au singulier ou au pluriel
     */
    public String proposition (int nb){
    	
    	if (nb>1){
    		return " propositions.";
    	} else {
    		return " proposition.";
    	}
    }
    
    /**
     * Méthode pour le choix utilisateur en fin de jeu 
	 * @see Classe Jeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci car elle renvoi vers les différents jeux
	 * @param value
	 * 		Booléen pour le mode développeur mis en paramètre afin de le faire hériter au choix de fait
	 */
    public void rejouer (boolean value){
	
		boolean testRejouer = false;
		int choixRejouer = 0;
		Jeu rejouer = new Jeu();
		
		System.out.println();
		System.out.println("Que voulez vous faire ?");
		System.out.println("	1 - Rejouer au même jeu");
		System.out.println("	2 - Revenir au menu principal");
		System.out.println("	3 - Quitter le jeu");
		
		do {
            System.out.println("Quel est votre choix ?");
            testRejouer = sc.hasNextInt();
            if(testRejouer){
                choixRejouer = sc.nextInt();
                if (choixRejouer != 1 && choixRejouer != 2 && choixRejouer != 3){
                	logger.error("Le choix n'est pas bon, l'utilisateur a mis " + sc + " au lieu de 1, 2 ou 3.");
                	System.out.println("Merci de choisir entre les choix 1, 2, 3.");
                    testRejouer = false;
                }
            } else{
            	logger.error("La combinaison n'est pas bonne car ce n'est pas un entier.");
            	System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3.");
                sc.nextLine();
            }
        }while (!testRejouer );
		logger.info("Le choix de l'utilisateur est " + choixRejouer);
		
		switch(choixRejouer){
			case 1 :
				System.out.println();
				rejouer.presentationRecherche(value);
			case 2 :
				System.out.println();
				rejouer.presentationJeu(value);
			case 3 :
				System.out.println();
				System.out.println("A très bientôt sur Jeu de Logique !");
				System.exit(0);
		}
	}
    
}
