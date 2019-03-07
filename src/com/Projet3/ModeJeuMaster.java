package com.Projet3;

import java.util.Scanner;

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
	 * Cette méthode crée le mode Challenger permettant à une personne de trouver la combinaison de l'ordinateur
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les différentes méthodes utilisés dans celle-ci ainsi que certains paramètres
	 */
	public void modeChallenger() {

    	CreationJeu jeu = new CreationJeu();
    	int[] propoH;
    	int[] infoPlace;
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	jeu.combiMasterOrdi();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiMasterHumain("Proposition : ");
    		infoPlace = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifR){
        		System.out.println("Vous avez trouvé la combinaison.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouvé la combinaison dans le temps imparti. Celle-ci était : "); 
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
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
	public void modeDefenseur () {
		
		CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	int[] infoPlace = new int[2];
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiMasterHumain("Combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	if (verifR){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (!verifV){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        		rejouer ();
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
	public void modeDuel () {
		
		CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	int[] propoH;
    	int[] infoPlaceH;
    	int[] infoPlaceO = new int[2];
    	String reponseH;
    	String reponseO;
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiMasterHumain("Combinaison secrète : ");
    	jeu.combiMasterOrdi();
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiMasterHumain("Proposition : ");
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		reponseH = jeu.reponse(infoPlaceH);
        	System.out.println(" -> Réponse : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponseO = jeu.reponse(infoPlaceO);
        	System.out.println(" -> Réponse : " + reponseO);
        	verifH = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur.");
        		rejouer ();
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouvé votre combinaison. Sa combinaison était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. La combinaison n'a pas été trouvé dans le temps imparti.La combinaison de l'ordinateur était :");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
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
		
		Scanner sc = new Scanner(System.in);
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
				rejouer.presentationMastermind();
			case 2 :
				System.out.println();
				rejouer.presentationJeu();
			case 3 :
				System.out.println("A très bientôt sur Jeu de Logique !");
				System.exit(0);
		}
	}
	
}	