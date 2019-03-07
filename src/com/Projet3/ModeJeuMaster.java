package com.Projet3;

import java.util.Scanner;

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
	 * Cette m�thode cr�e le mode Challenger permettant � une personne de trouver la combinaison de l'ordinateur
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
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
        	System.out.println(" -> R�ponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifR){
        		System.out.println("Vous avez trouv� la combinaison.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouv� la combinaison dans le temps imparti. Celle-ci �tait : "); 
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
        	}
        	tour++;
    	}     
    }
	
	/**
	 * Cette m�thode cr�e le mode Defenseur permettant � l'ordinateur de trouver votre combinaison
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 */
	public void modeDefenseur () {
		
		CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	int[] infoPlace = new int[2];
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiMasterHumain("Combinaison secr�te : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> R�ponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	if (verifR){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (!verifV){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        		rejouer ();
        	}
        	tour ++;	
    	}		
	}
	
	/**
	 * Cette m�thode cr�e le mode Duel permettant � une personne de se confronter � l'ordinateur
	 * 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
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
    	
    	combiH = jeu.combiMasterHumain("Combinaison secr�te : ");
    	jeu.combiMasterOrdi();
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiMasterHumain("Proposition : ");
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiO(), propoH);
    		reponseH = jeu.reponse(infoPlaceH);
        	System.out.println(" -> R�ponse : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition : ", tour);
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponseO = jeu.reponse(infoPlaceO);
        	System.out.println(" -> R�ponse : " + reponseO);
        	verifH = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiO(), propoH));
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur.");
        		rejouer ();
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouv� votre combinaison. Sa combinaison �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        		rejouer ();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. La combinaison n'a pas �t� trouv� dans le temps imparti.La combinaison de l'ordinateur �tait :");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer ();
        	}
        	tour++;
    	}
	}
	
	/**
	 * Cette m�thode permet de questionner sur le choix de la personne � la fin d'un jeu
	 * 
	 * @see Classe Jeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci car elle renvoi vers les diff�rents jeux
	 */
	public void rejouer (){
		
		Scanner sc = new Scanner(System.in);
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
				System.out.println("A tr�s bient�t sur Jeu de Logique !");
				System.exit(0);
		}
	}
	
}	