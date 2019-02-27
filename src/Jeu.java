import java.util.Random;
import java.util.Scanner;

public class Jeu {
	
	int [] combiS;
	int [] propoO;
	int size = 4;
	int nbreTour = 5;

    public void choixCombiOrdi (String str){

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
    			propoO[i] = rd.nextInt(9);
    		}
    		if (tab[i] == "="){
    			propoO[i] = com[i];
    		}
    		if (tab[i] == "+"){
    			propoO[i] = (rd.nextInt((9-com[i]))) + (com[i]+1);
    		}
    		if (tab[i] == "-"){
    			propoO[i] = (rd.nextInt((com[i]-1)));
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
    	System.out.print(reponse);
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

    public void modeAttaquant() {

    	int[] propoH;
    	String [] reponse = new String[size];
    	boolean verifR = false;
    	boolean verifV = true;
    	int i = 1;
    	
    	choixCombiOrdi("Combinaison secrète : ");
    	afficheCombi(combiS);
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoH = choixCombiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponse = compareProposition(combiS, propoH);
        	afficheReponse(reponse);
        	verifR = verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("Vous avez trouvé la combinaison.");
        	}
        	verifV = verifTour(i);
        	if (verifV == false){
        		System.out.println("Vous n'avez pas trouvé la combinaison.");
        	}
        	i++;
    	}     
    }
    
    public void modeDefenseur(){
    	
    	int[] combiH;
    	String[] reponse = new String[size];
    	boolean verifR = false;
    	boolean verifV = true;
    	int i = 1;
    	
    	combiH = choixCombiHumain("Combinaison secrète : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		propoCombiOrdi(reponse, propoO, "Proposition : ");
    		afficheCombi(propoO);
        	System.out.print(" -> Réponse : ");
        	reponse = compareProposition(combiH, propoO);
        	afficheReponse(reponse);
        	verifR = verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison.");
        	}
        	verifV = verifTour(i);
        	if (verifV == false){
        		System.out.println("L'ordinateur n'a pas trouvé votre combinaison.");
        	}
        	i++;
    	}     	
    }
    
    public void modeDuel(){
    	
    	int[] combiH;
    	int[] propoH;
    	String[] reponseH = new String[size];
    	String[] reponseO = new String[size];
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	int i = 1;
    	
    	combiH = choixCombiHumain("Combinaison secrète : ");
    	choixCombiOrdi("Combinaison secrète : ");
    	afficheCombi(combiS);
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = choixCombiHumain("Proposition : ");
        	System.out.print(" -> Réponse : ");
        	reponseH = compareProposition(combiS, propoH);
        	afficheReponse(reponseH);
    		propoCombiOrdi(reponseO, propoO, "Proposition Ordi : ");
    		afficheCombi(propoO);
        	System.out.print(" -> Réponse : ");
        	reponseO = compareProposition(combiH, propoO);
        	afficheReponse(reponseO);
        	verifH = verifReponse(reponseH);
        	verifO = verifReponse(reponseO);
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouvé la combinaison de l'ordinateur. Vous êtes plus rapide que la machine.");
        	}
        	if (verifH == false && verifO == true){
        		System.out.println("L'ordinateur a trouvé votre combinaison. L'ordinateur a été plus rapide que vous.");
        	}
        	if (verifH == true && verifO == false){
        		System.out.println("Match nul. L'ordinateur et vous avez trouvé la combinaison en même temps.");
        	}
        	verifV = verifTour(i);
        	if (verifV == false){
        		System.out.println("Match nul. La combinaison n'a pas été trouvé dans le temps imparti.");
        	}
        	i++;
    	}
    }
}
