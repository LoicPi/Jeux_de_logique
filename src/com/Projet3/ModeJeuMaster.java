package com.Projet3;

public class ModeJeuMaster {

	public void modeAttaquant() {

    	Jeu jeu = new Jeu();
    	int[] propoH;
    	int[] infoPlace;
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
        	if (verifR){
        		System.out.println("Vous avez trouvé la combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouvé la combinaison dans le temps imparti.");
        	}
        	tour++;
    	}     
    }
	
	public void modeDefenseur () {
		Jeu jeu = new Jeu();
    	int[] combiH;
    	int[] infoPlace = new int[2];
    	String reponse;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	
    	combiH = jeu.combiHumain("( Combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdiMaster(jeu.getPropoO(), infoPlace, combiH, "Proposition : ", tour);
    		jeu.afficheCombi(jeu.getPropoO());
    		infoPlace = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponse = jeu.reponse(infoPlace);
        	System.out.println(" -> Réponse : " + reponse);
        	verifR = jeu.verifNombreBienPlace(infoPlace);
        	if (verifR){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (!verifV){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
        	}
        	tour ++;	
    	}		
	}
	
	public void modeDuel () {
		
		Jeu jeu = new Jeu();
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
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	jeu.combiOrdi("Combinaison secrète : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
    		infoPlaceH = jeu.nombrePlacement(jeu.getCombiS(), propoH);
    		reponseH = jeu.reponse(infoPlaceH);
        	System.out.println(" -> Réponse : " + reponseH);
        	jeu.propoOrdiMaster(jeu.getPropoO(), infoPlaceO, combiH, "Proposition : ", tour);
    		jeu.afficheCombi(jeu.getPropoO());
    		infoPlaceO = jeu.nombrePlacement(combiH, jeu.getPropoO());
    		reponseO = jeu.reponse(infoPlaceO);
        	System.out.println(" -> Réponse : " + reponseO);
        	verifH = jeu.verifNombreBienPlace(jeu.nombrePlacement(jeu.getCombiS(), propoH));
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur. Vous êtes plus rapide que la machine.");
        	}
        	if (verifH == false && verifO == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison. L'ordinateur a été plus rapide que vous.");
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Match nul. La combinaison n'a pas été trouvé dans le temps imparti.");
        	}
        	tour++;
    	}
	}
	
}	