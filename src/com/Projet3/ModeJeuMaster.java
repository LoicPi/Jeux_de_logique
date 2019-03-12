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
	static Logger logger = LogManager.getLogger(Main.class.getName());
	
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
	boolean verifV = true;
	
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
	 */
	public void modeChallenger(String str) {

    	jeu.combiMasterOrdi();
    	if (str.equals("true")){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.println();
    		System.out.println();
    	}
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		infoPlace = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse à votre proposition : " + reponse);
        	System.out.println();
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifR){
        		System.out.println("Vous avez trouvé la combinaison.");
        		rejouer (str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouvé la combinaison dans le temps imparti. Celle-ci était : "); 
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer (str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)));
        	System.out.println ();
        	tour++;
    	}     
    }
	
	/**
	 * Crée le mode Defenseur 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
	public void modeDefenseur (String str) {
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition de l'ordinateur : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse à sa proposition : " + reponse);
        	System.out.println();
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	if (verifR){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        		rejouer (str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (!verifV){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        		rejouer (str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax -tour) + proposition((tourMax-tour)));
        	System.out.println ();
        	tour ++;	
    	}		
	}
	
	/**
	 * Crée le mode Duel
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
	public void modeDuel (String str) {

    	int[] infoPlaceH;
    	int[] infoPlaceO = new int[2];
    	String reponseH;
    	String reponseO;
    	boolean verifH = false;
    	boolean verifO = false;
    	
    	combiH = jeu.combiMasterHumain("Votre combinaison secrète : ");
    	jeu.combiMasterOrdi();
    	if (str.equals("true")){
    		System.out.println("Combinaison secrète de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiMasterHumain("Votre proposition : ");
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		reponseH = jeu.reponse(infoPlaceH);
        	System.out.println(" -> Réponse à votre proposition : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition de l'ordinateur : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponseO = jeu.reponse(infoPlaceO);
        	System.out.println(" -> Réponse à la proposition de l'ordinateur : " + reponseO);
        	System.out.println();
        	verifH = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur.");
        		rejouer (str);
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouvé votre combinaison. Sa combinaison était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer (str);
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		rejouer (str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. La combinaison n'a pas été trouvé dans le temps imparti.La combinaison de l'ordinateur était :");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer (str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)));
        	System.out.println ();
        	tour++;
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
	 */
	public void rejouer (String str){
		
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
                    System.out.println("Merci de choisir entre les choix 1, 2, 3.");
                    testRejouer = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3.");
                sc.nextLine();
            }
        }while (!testRejouer );
		
		switch(choixRejouer){
			case 1 :
				System.out.println();
				rejouer.presentationMastermind(str);
			case 2 :
				System.out.println();
				rejouer.presentationJeu(str);
			case 3 :
				System.out.println();
				System.out.println("A très bientôt sur Jeu de Logique !");
				System.exit(0);
		}
	}
	
}	