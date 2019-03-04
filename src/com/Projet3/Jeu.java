package com.Projet3;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
	
	int [] combiS;
	int [] propoO;
	int size = 4;
	public int[] getCombiS() {
		return combiS;
	}

	public void setCombiS(int[] combiS) {
		this.combiS = combiS;
	}

	public int[] getPropoO() {
		return propoO;
	}

	public void setPropoO(int[] propoO) {
		this.propoO = propoO;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNbreTour() {
		return nbreTour;
	}

	public void setNbreTour(int nbreTour) {
		this.nbreTour = nbreTour;
	}

	int nbreTour = 5;

    public  void choixCombiOrdi (String str){

        Random rd = new Random();

        System.out.print(str);
        
        combiS = new int[size];
        for (int i = 0; i < size; i++) {
            combiS[i] = rd.nextInt(9);
        }
    }
    
    public void propoCombiOrdi (String [] tab, int[] com, String str){
    	
    	Random rd = new Random();
    	propoO = new int[size];
    	
    	System.out.print(str);
    	
    	for (int i = 0; i < size; i++){
    		if(tab[i] == null){
    			propoO[i] = 5;
    		}
    		if (tab[i] == "="){
    			propoO[i] = com[i];
    		}
    		if (tab[i] == "+"){
    			propoO[i] = com[i] + 2;
    		}
    		if (tab[i] == "-"){
    			propoO[i] = com[i] - 1;
    		}
    	}	
    }
    
    public void afficheCombi(int str[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < size; i++){
    		tbStr = tbStr + str[i];
    	}
    	System.out.print(tbStr);
    }

    public int [] choixCombiHumain (String str) {
    	  	
        Scanner sc = new Scanner(System.in);
        boolean testCombi = false;
        String combi = "";
        
        do{
        	System.out.print(str);
        	testCombi = sc.hasNextInt();
        	if (testCombi) {
        		combi = sc.nextLine();
        		testCombi = true;
        	}else {
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        		sc.nextLine();
        	}
        } while(testCombi != true);	
     
        int [] combiH = new int[size];
        
        for(int i = 0; i < size; i++){
        	combiH[i] = Character.digit(combi.charAt(i), 10);
        }
        
        return combiH;

    }
    
    public String [] compareProposition(int[] combi, int[] propo){
    	
    	String [] repo = new String [size];    	
    	
    	for(int i = 0; i < size; i++){
    		if(combi [i] < propo[i]){
    			repo[i] = "-";
    		}
    		if(combi [i] > propo[i]){
    			repo[i] = "+";
    		}
    		if(combi [i] == propo[i]){
    			repo[i] = "=";
    		}
    	}
    	return repo;
    }
    	
    public void afficheReponse(String[] tab) {
    	
    	String reponse = "";
    	
    	for (int i = 0; i < size; i++){
    		reponse = reponse + tab[i];
    	}
    	System.out.println(reponse);
    }
    
    public boolean verifReponse (String[] tab){
    	
    	for (int i = 0; i < size; i++){
    		if (tab[i] != "="){
    			return false;
    		}
    	}
    	return true;
    }
    
    public boolean verifTour (int tour){
    	
    	if (tour < nbreTour){
    		return true;
    	}
    	return false;
    }

    public boolean[] chBienPlace(int[] combi, int[] propo) {

        boolean[] bienPlace = new boolean[size];
        
        for (int i = 0; i < size; i++){
        	if (combi[i] == propo [i]) {
            	bienPlace[i] = true;            	
            } else {
            	bienPlace[i] = false;
            }
        }
        return bienPlace;      
    }
    
    public boolean[] chMalPlace (int[] combi, int[] propo, boolean[] bienPlace){
    	
    	boolean [] malPlace = new boolean[size];
    	
    	for (int i = 0; i < size; i++){
    		if(!bienPlace[i]){
    			malPlace[i] = nombreRecherche(combi, propo[i]);
    		}
    		else{
    			malPlace[i] = false;
    		}
    	}
    	return malPlace;
    }

    public boolean nombreRecherche (int combi[], int propo) {

        for (int i = 0 ; i < size ; i++) {
            if (combi[i] == propo) {
                return true;
            }
        }
        return false;
    }
    
    public String reponse (int[] combi, int[] propo){
    	
    	boolean[] bienPlace = chBienPlace(combi, propo);
    	boolean[] malPlace = chMalPlace(combi, propo, bienPlace);
    	int nbBienPlace = 0;
    	int nbMalPlace = 0;
    	String rep = "";
    	
    	for (int i = 0; i < size; i++){
    		if (bienPlace[i]){
    			nbBienPlace = nbBienPlace + 1;
    		}
    		if (malPlace[i]){
    			nbMalPlace = nbMalPlace + 1;
    		}
    	}
    	
    	if (nbBienPlace == 0 && nbMalPlace == 0){
    		rep = "Aucun des nombres ne correspondent à la combinaison !";
    	}
    	if (nbBienPlace == 0 && nbMalPlace != 0){
    		rep = "Vous avez " + nbMalPlace + "chiffre de mal placé.";    				
    	}
    	if (nbBienPlace != 0 && nbMalPlace == 0){
    		if (nbBienPlace == size){
    			rep = "Vous avez trouvé la combinaison. Vous avez gagné !!!";
    		} else {
    			rep = "Vous avez " + nbBienPlace + " chiffre bien placé.";
    		}
    	}
    	if (nbBienPlace !=0 && nbMalPlace != 0){
    		rep = "Vous avez " + nbMalPlace + " chiffre mal placé et " + nbBienPlace + " chiffre bien placé.";
    	}
    	return rep;
    }
    
    public boolean verifPhraseReponse (String reponse){
    	
    	if (reponse.equals("Vous avez trouvé la combinaison. Vous avez gagné !!!")){
    		return true;
    	}
    	return false;
    }

}
