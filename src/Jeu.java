import java.util.Random;
import java.util.Scanner;

public class Jeu {

    public char [] choixCombiOrdi (){

        int size = 4;
        Random rd = new Random();


        char combi[] = new char[size];
        for (int i = 0; i < size; i++) {
            combi[i] = (char) rd.nextInt(9);
        }
        return combi;
    }

    public char [] choixCombiHumain () {
    	  	
        Scanner sc = new Scanner(System.in);
        boolean testCombi = false;
        String combiS;
        
        do{
        	combiS = sc.nextLine();
        	try {
        		int combiSe = Integer.parseInt(combiS);
        		testCombi = true;
        	}catch(NumberFormatException e) {
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        	}
        } while(testCombi != true);	
     
        char [] combi = combiS.toCharArray();
        return combi;

    }

    public void modeAttaquant() {

        System.out.print("(Combinaison secrète : " + choixCombiOrdi() + " )");

        
        
/**
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();
        int combi;
        int propo =0;
        int i;
        int j;
        boolean verifInt = false;
        boolean choixOk = false;

        System.out.println("(Combinaison secrète :" + combi = rnd.nextInt(9999) + ")");

        do {
            System.out.println("Proposition : ");
            verifInt = sc.hasNextInt();
            if (verifInt) {
                propo = sc.nextInt();
                if (propo < 0 || propo > 9999) {
                    System.out.println("Merci de saisir une proposition comprise à 4 chiffres.");
                    choixOk = false;
                } else {
                    choixOk = true;
                }
            } else {
                System.out.println("Vous n'avez pas saisi un nombre.");
                sc.nextLine();
            }
        } while(!choixOk);

        String cmb = "" + combi;
        char [] tabc = cmb.toCharArray();

        String str = "" + propo;
        char [] tabp = str.toCharArray();

        char [] tabs = new char[tabc.length]

        for ( i = 0 ; i < tabp.length ; i++){
            j = tabp [i] - tabc[i];
            if (j == 0){
                tabs[i] = '=';
            }
            if (j < 0){
                tabs[i] = '+';
            }
            if (j > 0) {
                tabs[i] = '-';
            }
        }


    }
 */
}
