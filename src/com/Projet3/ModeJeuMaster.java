package com.Projet3;

public class ModeJeuMaster {

	public void modeAttaquant() {

    	Jeu jeu = new Jeu();
    	int[] propoH;
    	int infoPlace[];
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	    	
    	jeu.choixCombiOrdi("Combinaison secr�te : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = jeu.choixCombiHumain("Proposition : ");
    		infoPlace = jeu.nombrePlacement(jeu.getCombiS(), propoH);
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> R�ponse : " + reponse);
        	verifR = jeu.verifPhraseReponse(reponse);
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouv� la combinaison.");
        	}
        	tour++;
    	}     
    }
}	