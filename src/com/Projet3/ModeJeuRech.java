package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * ModeJeuRech est une classe contenant toutes les m�thodes des diff�rents modes contenu dans le jeu Recherche+/-
 * Chacune des m�thodes va faire appel aux m�thodes contenu dans la classe CreationJeu.
 * @see Classe CreationJeu
 * @see Classe Jeu
 * 
 * @author Lo�c
 * @version 1.0
 */

public class ModeJeuRech {
	
	/**
	 * Cr�ation d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(ModeJeuRech.class.getName());
	
	/**
	 * Cr�er un objet jeu afin de pouvoir l'appeler dans les diff�rentes m�thodes
	 */
	CreationJeu jeu = new CreationJeu();
	
	/**
	 * Cr�ation d'un objet prop pour r�cup�rer les propri�t�s d�finit dans le fichier
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	
	/**
	 * Tableau d'entier utilis� dans les m�thodes pour contenir la combinaison secrete de l'humain
	 */
	int[] combiH;
	
	/**
	 * Tableau d'entier utilis� dans les m�thodes pour contenir la proposition de l'humain
	 */
	int[] propoH;

	/**
	 * D�finiton du nombre de tour, commencant � 1
	 */
	int tour = 1;
	
	/**
	 * D�finition de nombre de tour maximum	
	 */
	int tourMax = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * Tableau de chaine de caractere pour contenir la reponse � la proposition
	 */
	String [] reponse = new String[jeu.getSize()];
	
	/**
	 * D�finition de bool�en pour v�rifier si la r�ponse est bonne
	 */
	boolean verifR = false;
	
	/**
	 * D�finition d'un bool�en pour v�rifier le nombre de tour
	 */
	boolean verifV = false;
	
	/**
	 * Permet de cr�er un objet Scanner qui va r�cup�rer les entr�es des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Cr�e le mode Challenger	 * 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 * @param value
	 * 		Bool�en pour v�rifier si le mode Challenger est lanc� en mode d�veloppeur ou non
	 */
    public void modeChallenger(boolean value) {
    	
    	jeu.combiOrdi();
    	logger.info("La combinaison secr�te est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.println();
    		System.out.println();
    	}
    	
    	while (!verifR && !verifV){
    		propoH = jeu.combiHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
        	reponse = jeu.compareProposition(jeu.getCombiO(), propoH);
        	System.out.println(" -> R�ponse � votre proposition : " + jeu.afficheReponse(reponse) + "\n");
        	logger.info("Le jeu nous donne comme r�ponse : " + jeu.afficheReponse(reponse));
        	verifR = jeu.verifReponse(reponse);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'utilisateur a trouv� la combinaison.");
        	if (verifR){
        		System.out.println("Vous avez trouv� la combinaison.");
        		logger.info("L'utilisateur a trouv� la combinaison");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.print("Vous n'avez pas trouv� la combinaison. Celle-ci �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'utilisateur n'a pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour++;
        	logger.info("On passe au tour : " + tour);
    	}     
    }
    
    /**
	 * Cr�e le mode Defenseur
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 * @param value
	 * 		Bool�en pour v�rifier si le mode Defenseur est lanc� en mode d�veloppeur ou non
	 */
    public void modeDefenseur(boolean value){
    	
    	combiH = jeu.combiHumain("Votre combinaison secr�te : ");
    	logger.info("La combinaison donn� par l'utilisateur est : " + jeu.afficheCombi(combiH));
    	System.out.print("\n\n");
    	
    	while (!verifR && !verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		logger.info("La proposition donn� par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> R�ponse � sa proposition : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	System.out.println(jeu.afficheReponse(reponse) +"\n");
        	logger.info("Le jeu nous donne comme r�ponse : " + jeu.afficheReponse(reponse));
        	verifR = jeu.verifReponse(reponse);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'ordinateur a trouv� la combinaison.");
        	if (verifR){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        		logger.info("L'ordinateur a trouv� la combinaison");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        		logger.info("L'ordinateur n'a pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour ++;
        	logger.info("On passe au tour : " + tour);
    	}     	
    }
    
    /**
	 * Cr�e le mode Duel 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 * @param value
	 * 		Bool�en pour v�rifier si le mode Duel est lanc� en mode d�veloppeur ou non
	 */
    public void modeDuel(boolean value){
    	
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	
    	combiH = jeu.combiHumain("Votre combinaison secr�te : ");
    	logger.info("La combinaison secr�te de l'utilisateur est : " + jeu.afficheCombi(combiH));
    	jeu.combiOrdi();
    	logger.info("La combinaison secr�te de l'ordinateur est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.println("\n\n");
    	
    	while (!verifH && !verifO && !verifV){
    		
    		propoH = jeu.combiHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		reponseH = jeu.compareProposition(jeu.getCombiO(), propoH);
        	System.out.println(" -> R�ponse � votre proposition : " + jeu.afficheReponse(reponseH));
        	logger.info("Le jeu nous donne comme r�ponse pour l'utilisateur : " + jeu.afficheReponse(reponseH));
    		jeu.propoOrdi(reponseO,tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		logger.info("La proposition donn� par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	System.out.print(" -> R�ponse � sa proposition : " + jeu.afficheReponse(reponseO) +"\n");
        	logger.info("Le jeu nous donne comme r�ponse pour l'ordinateur : " + jeu.afficheReponse(reponseO));
        	verifH = jeu.verifReponse(reponseH);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifH + " pour savoir si l'utilisateur a trouv� la combinaison.");
        	verifO = jeu.verifReponse(reponseO);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifO + " pour savoir si l'ordinateur a trouv� la combinaison.");
        	if (verifH && !verifO){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur.");
        		logger.info("L'utilisateur a trouv� la combinaison");
        		rejouer(value);
        	}
        	if (!verifH && verifO){
        		System.out.print("L'ordinateur a trouv� votre combinaison. La combinaison de l'ordinateur �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur a trouv� la combinaison de l'utilisateur.");
        		rejouer(value);
        	}
        	if (verifH && verifO){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        		logger.info("L'ordinateur et l'utilisateur ont trouv� en m�me temps les combinaisons secr�tes.");
        		rejouer(value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.print("Match nul. Les combinaison n'ont pas �t� trouv� dans le temps imparti. La combinaison de l'ordinateur �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur et l'utilisateur n'ont pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer(value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)) + "\n");
        	logger.info("Il reste " + (tourMax -tour) + " tour.");
        	tour++;
        	logger.info("On passe au tour : " + tour);
    	}
    }
    
    /**
     * Permet de mettre le mot plac� au singulier ou au pluriel
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
     * M�thode pour le choix utilisateur en fin de jeu 
	 * @see Classe Jeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci car elle renvoi vers les diff�rents jeux
	 * @param value
	 * 		Bool�en pour le mode d�veloppeur mis en param�tre afin de le faire h�riter au choix de fait
	 */
    public void rejouer (boolean value){
	
		boolean testRejouer = false;
		int choixRejouer = 0;
		Jeu rejouer = new Jeu();
		
		System.out.println();
		System.out.println("Que voulez vous faire ?");
		System.out.println("	1 - Rejouer au m�me jeu");
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
				System.out.println("A tr�s bient�t sur Jeu de Logique !");
				System.exit(0);
		}
	}
    
}
