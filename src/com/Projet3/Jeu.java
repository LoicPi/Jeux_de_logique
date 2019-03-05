package com.Projet3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
	
	int [] combiS;
	int [] propoO;
	int size = 4;
	int nbreTour = 5;
	
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

    public  void choixCombiOrdi (String str){

        Random rd = new Random();

        System.out.print(str);
        
        combiS = new int[size];
        for (int i = 0; i < size; i++) {
            combiS[i] = rd.nextInt(9);
        }
    }
    
    public void propoCombiOrdi (String [] tab, int[] com, String str){
    	
    	propoO = new int[size];
    	
    	System.out.print(str);
    	
    	for (int i = 0; i < size; i++){
    		if(tab[i] == null){
    			propoO[i] = 7;
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
 
    public int[] nombrePlacement (int[] combi, int[] propo){
    	
    	int[] combiPrim = new int[size];
    	int[] propoPrim = new int[size];
    	int nbBienPlace = 0;
    	int nbMalPlace = 0;
    	int nbPlace [] = {nbBienPlace, nbMalPlace};
    	
    	for (int i = 0; i < size; i++){
    		if (combi[i] == propo[i]){
    			nbBienPlace = nbBienPlace + 1;
    			combiPrim[i] = -1;
    			propoPrim[i] = -2;
    		} else {
    			combiPrim[i] = combi[i];
    			propoPrim[i] = propo[i];
    		}
    	}
    	
    	for (int i = 0; i < size; i++){
    		if (nombreRecherche (combiPrim, propoPrim[i])){
    			nbMalPlace = nbMalPlace + 1;    			
    		}
    		
       	}
    	
    	return nbPlace;
    }
    
    public boolean nombreRecherche (int combi[], int propo) {

        for (int i = 0 ; i < size ; i++) {
        	if (combi[i] == propo) {
        		combi[i] = -1;
                return true;
            } 
        }
        return false;
    }
    	
    public String reponse (int [] nbPlace){
    	String rep = "";
    	
    	if (nbPlace[0] == 0 && nbPlace[1] == 0){
    		rep = "Aucun des nombres ne correspondent à la combinaison !";
    	}
    	
    	if (nbPlace[0] == 0 && nbPlace[1] != 0){
    		rep =  nbPlace[1] + " " + present(nbPlace[1]);
    	}
    	
    	if (nbPlace[0] != 0 && nbPlace[1] == 0){
    		if (nbPlace[0] == size){
    			rep = "Vous avez trouvé la combinaison. Vous avez gagné !!!";
    		} else {
    			rep = nbPlace[0] + " bien " + place(nbPlace[0]);
    		}
    	}
    	
    	if (nbPlace[0] !=0 && nbPlace[1] != 0){
    		rep = nbPlace[1] + " " + present(nbPlace[1]) + ", " + nbPlace[0] + " bien " + place(nbPlace[0]);
    	}
    	
    	return rep;
    }
    
    public String present (int nb){
    	
    	if (nb>1){
    		return "présents";
    	} else {
    		return "présent";
    	}
    }
    
    public String place (int nb){
    	
    	if (nb>1){
    		return "placés";
    	} else {
    		return "placé";
    	}
    }
    
    public boolean verifPhraseReponse (String reponse){
    	
    	if (reponse.equals("Vous avez trouvé la combinaison. Vous avez gagné !!!")){
    		return true;
    	}
    	return false;
    }
    
    public void propoCombiOrdi2 (String [] tab, int tour, String str, int[] com){
    	
    	propoO = new int[size];
    	
    	System.out.print(str);
    	
    	for (int i = 0; i < size; i++){
    		if(tour == 1){
    			propoO[i] = 7;
    		}
    		if (tab[i] == "="){
    			propoO[i] = com[i];
    		}
    		if (tab[i] == "+"){
    			switch(tour){
    				case 2 : propoO[i] = com[i] + 1;
    				break;
    				case 3 : if (com[i] == 8){
    							propoO[i] = com[i] + 1;
    						} else {
    							propoO[i] = 5;
    						}
    				break;
    				case 4 : propoO[i] = com[i] + 1;
    				break;
    			}
    		}
    		if (tab[i] == "-"){
    			if (tour == 2){
    				propoO[i] = 2;
    			} else {
    				propoO[i] = com[i] -1;
    			}
    		}
    	}	
    }
}
