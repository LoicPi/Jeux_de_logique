package com.Projet3;

public class ModeJeuRech {
	
    public void modeAttaquant() {

    	Jeu jeu = new Jeu();
    	String [] reponse = new String[jeu.getSize()];
    	int[] propoH;
    	boolean verifR = false;
    	boolean verifV = true;
    	int tour = 1;
    	    	
    	jeu.combiOrdi("Combinaison secrète : ");
    	jeu.afficheCombi(jeu.getCombiS());
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
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouvé la combinaison.");
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
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition : ", jeu.getPropoO());
    		jeu.afficheCombi(jeu.getPropoO());
        	System.out.print(" -> Réponse : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponse);
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison dans le temps imparti.");
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
    	
    	combiH = jeu.combiHumain("Combinaison secrète : ");
    	jeu.combiOrdi("Combinaison secrète : ");
    	jeu.afficheCombi(jeu.getCombiS());
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponseH = jeu.compareProposition(jeu.getCombiS(), propoH);
        	jeu.afficheReponse(reponseH);
    		jeu.propoOrdi(reponseO,tour, "Proposition Ordi : ", jeu.getPropoO());
    		jeu.afficheCombi(jeu.getPropoO());
        	System.out.print(" -> Réponse : ");
        	reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponseO);
        	verifH = jeu.verifReponse(reponseH);
        	verifO = jeu.verifReponse(reponseO);
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur. Vous êtes plus rapide que la machine.");
        	}
        	if (verifH == false && verifO == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison. L'ordinateur a été plus rapide que vous.");
        	}
        	if (verifH == true && verifO == false){
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
