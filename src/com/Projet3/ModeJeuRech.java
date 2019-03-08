package com.Projet3;

import java.util.Scanner;

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
	 * Créer un objet jeu afin de pouvoir l'appeler dans les différentes méthodes
	 */
	CreationJeu jeu = new CreationJeu();
	
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
	boolean verifV = true;
	
	/**
	 * Permet de créer un objet Scanner qui va récupérer les entrées des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Cette méthode crée le mode Challenger permettant à une personne de trouver la combinaison de l'ordinateur
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
    public void modeChallenger() {
   	
    	jeu.combiOrdi();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponse = jeu.compareProposition(jeu.getCombiO(), propoH);
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("Vous avez trouvé la combinaison.");
        		rejouer();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouvé la combinaison. Celle-ci était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer();
        	}
        	tour++;
    	}     
    }
    
    /**
	 * Cette méthode crée le mode Defenseur permettant à l'ordinateur de trouver votre combinaison
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
    public void modeDefenseur(){
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition : ", jeu.getPropoO());
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> Réponse : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        		rejouer();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        		rejouer();
        	}
        	tour ++;
    	}     	
    }
    
    /**
	 * Cette méthode crée le mode Duel permettant à une personne de se confronter à l'ordinateur
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
    public void modeDuel(){
    	
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	jeu.combiOrdi();
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponseH = jeu.compareProposition(jeu.getCombiO(), propoH);
        	jeu.afficheReponse(reponseH);
    		jeu.propoOrdi(reponseO,tour, "Proposition Ordi : ", jeu.getPropoO());
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> Réponse : ");
        	reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponseO);
        	verifH = jeu.verifReponse(reponseH);
        	verifO = jeu.verifReponse(reponseO);
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur.");
        		rejouer();
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouvé votre combinaison. La combinaison de l'ordinateur était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer();
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		rejouer();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. Les combinaison n'ont pas été trouvé dans le temps imparti. La combinaison de l'ordinateur était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer();
        	}
        	tour++;
    	}
    }
    
    /**
	 * Cette méthode permet de questionner sur le choix de la personne à la fin d'un jeu
	 * 
	 * @see Classe Jeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci car elle renvoi vers les différents jeux
	 */
    public void rejouer (){
	
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
				rejouer.presentationRecherche();
			case 2 :
				System.out.println();
				rejouer.presentationJeu();
			case 3 :
				System.out.println();
				System.exit(0);
		}
	}
    
}
