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
    	    	
    	jeu.combiOrdi("Combinaison secrète : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Proposition : ");
    		infoPlace = jeu.nombrePlacement(jeu.getCombiS(), propoH);
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiS(), propoH));
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouvé la combinaison dans le temps imparti.");
        	}
        	tour++;
    	}     
    }
}	