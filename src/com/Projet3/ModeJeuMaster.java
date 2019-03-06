package com.Projet3;

import java.util.Scanner;

public class ModeJeuMaster {

	public void modeChallenger() {

    	CreationJeu jeu = new CreationJeu();
    	Jeu retour = new Jeu();
    	int[] propoH;
    	int[] infoPlace;
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	    	
    	jeu.combiOrdi();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Proposition : ");
    		infoPlace = jeu.nombrePlacement(jeu.getCombiS(), propoH);
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> R�ponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiS(), propoH));
        	if (verifR){
        		System.out.println("Vous avez trouv� la combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouv� la combinaison dans le temps imparti. Celle-ci �tait : "); 
        		jeu.afficheCombi(jeu.getCombiS());
        	}
        	tour++;
    	}     
    }
	
	public void modeDefenseur () {
		
		CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	int[] infoPlace = new int[2];
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiHumain("( Combinaison secr�te : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition : ", tour);
    		jeu.afficheCombi(jeu.getPropoO());
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> R�ponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	if (verifR){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (!verifV){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        	}
        	tour ++;	
    	}		
	}
	
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
    	
    	combiH = jeu.combiHumain("Combinaison secr�te : ");
    	jeu.combiOrdi();
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiS(), propoH);
    		reponseH = jeu.reponse(infoPlaceH);
        	System.out.println(" -> R�ponse : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition : ", tour);
    		jeu.afficheCombi(jeu.getPropoO());
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponseO = jeu.reponse(infoPlaceO);
        	System.out.println(" -> R�ponse : " + reponseO);
        	verifH = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiS(), propoH));
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur. Vous �tes plus rapide que la machine.");
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouv� votre combinaison. Sa combinaison �tait : ");
        		jeu.afficheCombi(jeu.getCombiS());
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. La combinaison n'a pas �t� trouv� dans le temps imparti.La combinaison de l'ordinateur �tait :");
        		jeu.afficheCombi(jeu.getCombiS());
        	}
        	tour++;
    	}
	}
	
	public void rejouer (){
		
		Scanner sc = new Scanner(System.in);
		boolean testRejouer = false;
		int choixRejouer = 0;
		Jeu rejouer = new Jeu();
		
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
				rejouer.presentationMastermind();
			case 2 :
				rejouer.presentationJeu();
			case 3 :
				System.exit(0);
		}
	}
	
}	