package com.Projet3;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ModeJeuMaster est une classe contenant toutes les méthodes des différents modes contenu dans le jeu Mastermind
 * Chacune des méthodes va faire appel aux méthodes contenu dans la classe CreationJeu.
 * @see Classe CreationJeu
 * @see Classe Jeu
 * 
 * @author Loïc
 * @version 1.0
 */

public class ModeJeuMaster {
	
	/**
	 * Création d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(ModeJeuMaster.class.getName());
	
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
	 * Tableau d'entier utilisé dans les méthodes pour contenir les nombres bien placés et mal placés
	 */
	int[] infoPlace = new int[2];
	
	/**
	 * Définition d'une chaîne de caractère pour retourner la réponse à la proposition
	 */
	String reponse;
	
	/**
	 * Définition de booléen pour vérifier si la réponse est bonne
	 */
	boolean verifR = false;
	
	/**
	 * Définition d'un booléen pour vérifier le nombre de tour
	 */
	boolean verifV = false;
	
	/**
	 * Définiton du nombre de tour, commencant à 1
	 */
	int tour = 1;
	
	/**
	 * Définition du nombre de tour maximum	
	 */
	int tourMax = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * Permet de créer un objet Scanner qui va récupérer les entrées des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Création du mode Challenger
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 * @param value
	 * 		Booléen pour vérifier si le mode Challenger est lancé en mode développeur ou non
	 */
	public void modeChallenger(Boolean value) {

    	jeu.combiMasterOrdi();
    	logger.info("La combinaison secrète est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.print("\n\n");
    	}
    	
    	while (!verifR && !verifV){
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		infoPlace = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		logger.info("Au tour " + tour + ", le nombre de chiffre bien placés est " + infoPlace[0] + " et le nombre de chiffre mal placés est " + infoPlace [1]);
    		reponse = jeu.reponse(infoPlace);
    		logger.info("Le jeu nous donne comme réponse : " + reponse);
        	System.out.println(" -> Réponse à votre proposition : " + reponse + "\n");
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'utilisateur a trouvé la combinaison.");
        	if (verifR){
        		System.out.println("Vous avez trouvé la combinaison.");
        		logger.info("L'utilisateur a trouvé la combinaison");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.print("Vous n'avez pas trouvé la combinaison dans le temps imparti. Celle-ci était : "); 
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'utilisateur n'a pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer (value);
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
	public void modeDefenseur (boolean value) {
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secrète : ");
    	logger.info("La combinaison donné par l'utilisateur est : " + jeu.afficheCombi(combiH));
    	System.out.print("\n\n");
    	
    	while (!verifR && !verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition de l'ordinateur : ", tour);
    		logger.info("La proposition donné par l'ordinateur au tour " + tour + "est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		logger.info("Au tour " + tour + ", le nombre de chiffre bien placés est " + infoPlace[0] + " et le nombre de chiffre mal placés est " + infoPlace [1]);
    		reponse = jeu.reponse(infoPlace);
    		logger.info("Le jeu nous donne comme réponse : " + reponse);
        	System.out.println(" -> Réponse à sa proposition : " + reponse +"\n");
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifR + " pour savoir si l'ordinateur a trouvé la combinaison.");
        	if (verifR){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        		logger.info("L'ordinateur a trouvé la combinaison de l'utilisateur.");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        		logger.info("L'ordinateur n'a pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer (value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax -tour) + proposition((tourMax-tour))+"\n");
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
	public void modeDuel (boolean value) {

    	int[] infoPlaceH;
    	int[] infoPlaceO = new int[2];
    	String reponseH;
    	String reponseO;
    	boolean verifH = false;
    	boolean verifO = false;
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secrète : ");
    	logger.info("La combinaison secrète de l'utilisateur est : " + jeu.afficheCombi(combiH));
    	jeu.combiMasterOrdi();
    	logger.info("La combinaison secrète de l'ordinateur est : " + jeu.afficheCombi(jeu.getCombiO()));
    	if (value){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.print("\n\n");
    	
    	while (!verifH && !verifO && !verifV){
    		
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		logger.info("La proposition de l'utilisateur au tour " + tour + " est : " + jeu.afficheCombi(propoH));
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		logger.info("Pour l'utilisateur au tour "+ tour + ", le nombre de chiffre bien placés est " + infoPlaceH[0] + " et le nombre de chiffre mal placés est " + infoPlaceH[1]);
    		reponseH = jeu.reponse(infoPlaceH);
    		logger.info("Le jeu nous donne comme réponse pour l'utilisateur : " + reponseH);
        	System.out.println(" -> Réponse à votre proposition : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition de l'ordinateur : ", tour);
        	logger.info("La proposition de l'ordinateur au tour " + tour + " est : " + jeu.afficheCombi(jeu.getPropoO()));
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		logger.info("Pour l'ordinateur au tour " + tour + ", le nombre de chiffre bien placés est " + infoPlaceO[0] + " et le nombre de chiffre mal placés est " + infoPlaceO[1]);
    		reponseO = jeu.reponse(infoPlaceO);
    		logger.info("Le jeu nous donne comme réponse pour l'ordinateur : " + reponseH);
        	System.out.println(" -> Réponse à la proposition de l'ordinateur : " + reponseO + "\n");
        	verifH = jeu.verifNombreBienPlace(infoPlaceH);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifH + " pour savoir si l'utilisateur a trouvé la combinaison.");
        	verifO = jeu.verifNombreBienPlace(infoPlaceO);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifO + " pour savoir si l'ordinateur a trouvé la combinaison.");
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur.");
        		logger.info("L'utilisateur a trouvé la combinaison");
        		rejouer (value);
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouvé votre combinaison. Sa combinaison était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur a trouvé la combinaison de l'utilisateur.");
        		rejouer (value);
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		logger.info("L'ordinateur et l'utilisateur ont trouvé en même temps les combinaisons secrètes.");
        		rejouer (value);
        	}
        	verifV = jeu.verifTour(tour);
        	logger.info("Au tour " + tour + ", le jeu nous donne " + verifV + " pour savoir si le nombre de tour maximum est dépassé.");
        	if (verifV){
        		System.out.print("Match nul. La combinaison n'a pas été trouvé dans le temps imparti.La combinaison de l'ordinateur était :");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		logger.info("L'ordinateur et l'utilisateur n'ont pas trouvé la combinaison secrète en " + tourMax + " tours.");
        		rejouer (value);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour))+"\n");
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
				rejouer.presentationMastermind(value);
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