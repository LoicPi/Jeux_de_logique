package com.Projet3;

public class ModeJeuMaster {

	public void modeAttaquant() {

    	Jeu jeu = new Jeu();
    	int[] propoH;
    	boolean verifR = false;
    	boolean verifV = true;
    	int i = 1;
    	    	
    	jeu.choixCombiOrdi("Combinaison secrète : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = jeu.choixCombiHumain("Proposition : ");
        	System.out.println(" -> Réponse : "+ jeu.reponse(jeu.getCombiS(), propoH));
        	verifR = jeu.verifPhraseReponse(jeu.reponse(jeu.getCombiS(), propoH));
        	verifV = jeu.verifTour(i);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouvé la combinaison.");
        	}
        	i++;
    	}     
    }
	
	public void modeDefenseur() {
		Jeu jeu = new Jeu();
		int[] combiH;
		
		combiH = jeu.choixCombiHumain("Combinaison secrete :");
		System.out.println();
		System.out.println();
		
		propo
		
	}
	
}
