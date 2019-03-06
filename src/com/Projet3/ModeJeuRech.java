package com.Projet3;

import java.util.Scanner;

public class ModeJeuRech {
	
    public void modeChallenger() {

    	CreationJeu jeu = new CreationJeu();
    	String [] reponse = new String[jeu.getSize()];
    	int[] propoH;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	    	
    	jeu.combiOrdi();
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponse = jeu.compareProposition(jeu.getCombiS(), propoH);
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("Vous avez trouvé la combinaison.");
        		rejouer();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouvé la combinaison. Celle-ci était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiS()));
        		rejouer();
        	}
        	tour++;
    	}     
    }
    
    public void modeDefenseur(){
    	
    	CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	String[] reponse = new String[jeu.getSize()];
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
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
    
    public void modeDuel(){
    	
    	CreationJeu jeu = new CreationJeu();
    	int[] combiH;
    	int[] propoH;
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	jeu.combiOrdi();
    	
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponseH = jeu.compareProposition(jeu.getCombiS(), propoH);
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
        		System.out.println(jeu.afficheCombi(jeu.getCombiS()));
        		rejouer();
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        		rejouer();
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. Les combinaison n'ont pas été trouvé dans le temps imparti. La combinaison de l'ordinateur était : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiS()));
        		rejouer();
        	}
        	tour++;
    	}
    }

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
				rejouer.presentationRecherche();
			case 2 :
				rejouer.presentationJeu();
			case 3 :
				System.exit(0);
		}
	}
    
}
