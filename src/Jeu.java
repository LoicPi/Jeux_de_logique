import java.util.Random;
import java.util.Scanner;

public class Jeu {
	
	int [] combiS;
	int size = 4;

    public void choixCombiOrdi (String str){

        Random rd = new Random();

        System.out.print(str);
        
        combiS = new int[size];
        for (int i = 0; i < size; i++) {
            combiS[i] = rd.nextInt(9);
        }
    }
    
    public void afficheCombi(int str[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < str.length; i++){
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
     
        int [] combiH = new int[combi.length()];
        
        for(int i = 0; i < combi.length(); i++){
        	combiH[i] = Character.digit(combi.charAt(i), 10);
        }
        
        return combiH;

    }
    
    public void compareProposition(int[] combi, int[] propo, String str){
    	
    	String [] repo = new String [combi.length];
    	String reponse = "";
    	
    	
    	for(int i = 0; i < combi.length; i++){
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
    	
    	for (int i = 0; i < repo.length; i++){
    		reponse = reponse + repo[i];
    	}
    	System.out.print(str + reponse);
    }

    public void modeAttaquant() {

    	int[] propoH;
    	
    	choixCombiOrdi("Combinaison Secr�te : ");
    	afficheCombi(combiS);
    	System.out.println();
    	System.out.println();
    	propoH = choixCombiHumain("Proposition : ");
    	compareProposition(combiS, propoH, " -> R�ponse : ");
    	     
    }
 
}
