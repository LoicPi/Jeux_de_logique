package com.Projet3;

public class ModeJeuRech {
	
    public void modeAttaquant() {

    	Jeu jeu = new Jeu();
    	String [] reponse = new String[jeu.getSize()];
    	int[] propoH;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	    	
    	jeu.combiOrdi("Combinaison secr�te : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> R�ponse : ");
        	reponse = jeu.compareProposition(jeu.getCombiS(), propoH);
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("Vous avez trouv� la combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouv� la combinaison.");
        	}
        	tour++;
    	}     
    }
    
    public void modeDefenseur(){
    	
    	Jeu jeu = new Jeu();
    	int[] combiH;
    	String[] reponse = new String[jeu.getSize()];
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiHumain("Combinaison secr�te : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition : ", jeu.getPropoO());
    		jeu.afficheCombi(jeu.getPropoO());
        	System.out.print(" -> R�ponse : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        	}
        	tour ++;
    	}     	
    }
    
    public void modeDuel(){
    	
    	Jeu jeu = new Jeu();
    	int[] combiH;
    	int[] propoH;
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiHumain("Combinaison secr�te : ");
    	jeu.combiOrdi("Combinaison secr�te : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> R�ponse : ");
        	reponseH = jeu.compareProposition(jeu.getCombiS(), propoH);
        	jeu.afficheReponse(reponseH);
    		jeu.propoOrdi(reponseO,tour, "Proposition Ordi : ", jeu.getPropoO());
    		jeu.afficheCombi(jeu.getPropoO());
        	System.out.print(" -> R�ponse : ");
        	reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponseO);
        	verifH = jeu.verifReponse(reponseH);
        	verifO = jeu.verifReponse(reponseO);
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur. Vous �tes plus rapide que la machine.");
        	}
        	if (verifH == false && verifO == true){
        		System.out.println("L'ordinateur a trouv� votre combinaison. L'ordinateur a �t� plus rapide que vous.");
        	}
        	if (verifH == true && verifO == false){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Match nul. La combinaison n'a pas �t� trouv� dans le temps imparti.");
        	}
        	tour++;
    	}
    }

}
