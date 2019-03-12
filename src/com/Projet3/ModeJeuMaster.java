package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ModeJeuMaster est une classe contenant toutes les m�thodes des diff�rents modes contenu dans le jeu Mastermind
 * Chacune des m�thodes va faire appel aux m�thodes contenu dans la classe CreationJeu.
 * @see Classe CreationJeu
 * @see Classe Jeu
 * 
 * @author Lo�c
 * @version 1.0
 */

public class ModeJeuMaster {
	
	/**
	 * Cr�ation d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(ModeJeuMaster.class.getName());
	
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
	 * Tableau d'entier utilis� dans les m�thodes pour contenir les nombres bien plac�s et mal plac�s
	 */
	int[] infoPlace = new int[2];
	
	/**
	 * D�finition d'une cha�ne de caract�re pour retourner la r�ponse � la proposition
	 */
	String reponse;
	
	/**
	 * D�finition de bool�en pour v�rifier si la r�ponse est bonne
	 */
	boolean verifR = false;
	
	/**
	 * D�finition d'un bool�en pour v�rifier le nombre de tour
	 */
	boolean verifV = false;
	
	/**
	 * D�finiton du nombre de tour, commencant � 1
	 */
	int tour = 1;
	
	/**
	 * D�finition du nombre de tour maximum	
	 */
	int tourMax = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * Permet de cr�er un objet Scanner qui va r�cup�rer les entr�es des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Cr�ation du mode Challenger
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 * @param value
	 * 		Bool�en pour v�rifier si le mode Challenger est lanc� en mode d�veloppeur ou non
	 */
	public void modeChallenger(Boolean value) {

    	jeu.combiMasterOrdi();
    	logger.info("La combinaison secr�te est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.print("\n\n");
    	}
    	
    	while (!verifR && !verifV){
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		infoPlace = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		logger.info("Au tour " + tour + ", le nombre de chiffre bien plac�s est " + infoPlace[0] + " et le nombre de chiffre mal plac�s est " + infoPlace [1]);
    		reponse = jeu.reponse(infoPlace);
    		logger.info("Le jeu nous donne comme r�ponse : " + reponse);
        	System.out.println(" -> R�ponse � votre proposition : " + reponse + "\n");
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'utilisateur a trouv� la combinaison.");
        	if (verifR){
        		System.out.println("Vous avez trouv� la combinaison.");
        		logger.info("L'utilisateur a trouv� la combinaison");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.print("Vous n'avez pas trouv� la combinaison dans le temps imparti. Celle-ci �tait : "); 
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'utilisateur n'a pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer (value);
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
	public void modeDefenseur (boolean value) {
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secr�te : ");
    	logger.info("La combinaison donn� par l'utilisateur est : " + jeu.afficheCombi(combiH));
    	System.out.print("\n\n");
    	
    	while (!verifR && !verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition de l'ordinateur : ", tour);
    		logger.info("La proposition donn� par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		logger.info("Au tour " + tour + ", le nombre de chiffre bien plac�s est " + infoPlace[0] + " et le nombre de chiffre mal plac�s est " + infoPlace [1]);
    		reponse = jeu.reponse(infoPlace);
    		logger.info("Le jeu nous donne comme r�ponse : " + reponse);
        	System.out.println(" -> R�ponse � sa proposition : " + reponse +"\n");
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'ordinateur a trouv� la combinaison.");
        	if (verifR){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        		logger.info("L'ordinateur a trouv� la combinaison de l'utilisateur.");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        		logger.info("L'ordinateur n'a pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer (value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax -tour) + proposition((tourMax-tour))+"\n");
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
	public void modeDuel (boolean value) {

    	int[] infoPlaceH;
    	int[] infoPlaceO = new int[2];
    	String reponseH;
    	String reponseO;
    	boolean verifH = false;
    	boolean verifO = false;
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secr�te : ");
    	logger.info("La combinaison secr�te de l'utilisateur est : " + jeu.afficheCombi(combiH));
    	jeu.combiMasterOrdi();
    	logger.info("La combinaison secr�te de l'ordinateur est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.print("\n\n");
    	
    	while (!verifH && !verifO && !verifV){
    		
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		logger.info("Pour l'utilisateur au tour "+ tour + ", le nombre de chiffre bien plac�s est " + infoPlaceH[0] + " et le nombre de chiffre mal plac�s est " + infoPlaceH[1]);
    		reponseH = jeu.reponse(infoPlaceH);
    		logger.info("Le jeu nous donne comme r�ponse pour l'utilisateur : " + reponseH);
        	System.out.println(" -> R�ponse � votre proposition : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition de l'ordinateur : ", tour);
        	logger.info("La proposition de l'ordinateur au tour " + tour + " est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		logger.info("Pour l'ordinateur au tour " + tour + ", le nombre de chiffre bien plac�s est " + infoPlaceO[0] + " et le nombre de chiffre mal plac�s est " + infoPlaceO[1]);
    		reponseO = jeu.reponse(infoPlaceO);
    		logger.info("Le jeu nous donne comme r�ponse pour l'ordinateur : " + reponseH);
        	System.out.println(" -> R�ponse � la proposition de l'ordinateur : " + reponseO + "\n");
        	verifH = jeu.verifNombreBienPlace(infoPlaceH);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifH + " pour savoir si l'utilisateur a trouv� la combinaison.");
        	verifO = jeu.verifNombreBienPlace(infoPlaceO);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifO + " pour savoir si l'ordinateur a trouv� la combinaison.");
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur.");
        		logger.info("L'utilisateur a trouv� la combinaison");
        		rejouer (value);
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouv� votre combinaison. Sa combinaison �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur a trouv� la combinaison de l'utilisateur.");
        		rejouer (value);
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        		logger.info("L'ordinateur et l'utilisateur ont trouv� en m�me temps les combinaisons secr�tes.");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est d�pass�.");
        	if (verifV){
        		System.out.print("Match nul. La combinaison n'a pas �t� trouv� dans le temps imparti.La combinaison de l'ordinateur �tait :");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur et l'utilisateur n'ont pas trouv� la combinaison secr�te en " + tourMax + " tours.");
        		rejouer (value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour))+"\n");
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
				rejouer.presentationMastermind(value);
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