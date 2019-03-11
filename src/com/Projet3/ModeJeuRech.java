package com.Projet3;

import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * ModeJeuRech est une classe contenant toutes les m�thodes des diff�rents modes contenu dans le jeu Recherche+/-
 * Chacune des m�thodes va faire appel aux m�thodes contenu dans la classe CreationJeu.
 * @see Classe CreationJeu
 * @see Classe Jeu
 * 
 * @author Lo�c
 * @version 1.0
 */

public class ModeJeuRech {
	
	static Logger logger = Logger.getLogger(ModeJeuRech.class);
	
	/**
	 * Cr�er un objet jeu afin de pouvoir l'appeler dans les diff�rentes m�thodes
	 */
	CreationJeu jeu = new CreationJeu();
	
	/**
	 * Cr�ation d'un objet prop pour r�cup�rer les propri�t�s d�finit dans le fichier
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	
	/**
	 * Tableau d'entier utilis� dans les m�thodes pour contenir la combinaison secrete de l'humain
	 */
	int[] combiH;
	
	/**
	 * Tableau d'entier utilis� dans les m�thodes pour contenir la proposition de l'humain
	 */
	int[] propoH;

	/**
	 * D�finiton du nombre de tour, commencant � 1
	 */
	int tour = 1;
	
	/**
	 * D�finition de nombre de tour maximum	
	 */
	int tourMax = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * Tableau de chaine de caractere pour contenir la reponse � la proposition
	 */
	String [] reponse = new String[jeu.getSize()];
	
	/**
	 * D�finition de bool�en pour v�rifier si la r�ponse est bonne
	 */
	boolean verifR = false;
	
	/**
	 * D�finition d'un bool�en pour v�rifier le nombre de tour
	 */
	boolean verifV = true;
	
	/**
	 * Permet de cr�er un objet Scanner qui va r�cup�rer les entr�es des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Cr�e le mode Challenger	 * 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 */
    public void modeChallenger(String str) {
    	
    	jeu.combiOrdi();
    	
    	if (str.equals("true")){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    		System.out.println();
    		System.out.println();
    	}
    	
    	while (!verifR && verifV){
    		propoH = jeu.combiHumain("Votre proposition : ");
        	System.out.print(" -> R�ponse � votre proposition : ");
        	reponse = jeu.compareProposition(jeu.getCombiO(), propoH);
        	jeu.afficheReponse(reponse);
        	System.out.println();
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("Vous avez trouv� la combinaison.");
        		rejouer(str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Vous n'avez pas trouv� la combinaison. Celle-ci �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer(str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)));
        	System.out.println();
        	tour++;
    	}     
    }
    
    /**
	 * Cr�e le mode Defenseur
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 */
    public void modeDefenseur(String str){
    	
    	combiH = jeu.combiHumain("Votre combinaison secr�te : ");
    	System.out.println();
    	System.out.println();
    	
    	while (!verifR && verifV){
    		jeu.propoOrdi(reponse, tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> R�ponse � sa proposition : ");
        	reponse = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponse);
        	System.out.println();
        	verifR = jeu.verifReponse(reponse);
        	if (verifR == true){
        		System.out.println("L'ordinateur a trouv� votre combinaison.");
        		rejouer(str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.println("L'ordinateur n'a pas trouv� votre combinaison dans le temps imparti.");
        		rejouer(str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)));
        	System.out.println();
        	tour ++;
    	}     	
    }
    
    /**
	 * Cr�e le mode Duel 
	 * @see Classe CreationJeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci ainsi que certains param�tres
	 */
    public void modeDuel(String str){
    	
    	String[] reponseH = new String[jeu.getSize()];
    	String[] reponseO = new String[jeu.getSize()];
    	boolean verifH = false;
    	boolean verifO = false;
    	boolean verifV = true;
    	
    	combiH = jeu.combiHumain("Votre combinaison secr�te : ");
    	jeu.combiOrdi();
    	if (str.equals("true")){
    		System.out.println("Combinaison secr�te de l'ordinateur : " + jeu.afficheCombi(jeu.getCombiO()));
    	}
    	System.out.println();
    	System.out.println();
    	
    	while (!verifH && !verifO && verifV){
    		
    		propoH = jeu.combiHumain("Votre proposition : ");
        	System.out.print(" -> R�ponse � votre proposition : ");
        	reponseH = jeu.compareProposition(jeu.getCombiO(), propoH);
        	jeu.afficheReponse(reponseH);
    		jeu.propoOrdi(reponseO,tour, "Proposition de l'ordinateur : ", jeu.getPropoO());
    		System.out.print(jeu.afficheCombi(jeu.getPropoO()));
        	System.out.print(" -> R�ponse � sa proposition : ");
        	reponseO = jeu.compareProposition(combiH, jeu.getPropoO());
        	jeu.afficheReponse(reponseO);
        	System.out.println();
        	verifH = jeu.verifReponse(reponseH);
        	verifO = jeu.verifReponse(reponseO);
        	if (verifH == true && verifO == false){
        		System.out.println("Vous avez trouv� la combinaison de l'ordinateur.");
        		rejouer(str);
        	}
        	if (verifH == false && verifO == true){
        		System.out.print("L'ordinateur a trouv� votre combinaison. La combinaison de l'ordinateur �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer(str);
        	}
        	if (verifH == true && verifO == true){
        		System.out.println("Match nul. L'ordinateur et vous avez trouv� la combinaison en m�me temps.");
        		rejouer(str);
        	}
        	verifV = jeu.verifTour(tour);
        	if (verifV == false){
        		System.out.print("Match nul. Les combinaison n'ont pas �t� trouv� dans le temps imparti. La combinaison de l'ordinateur �tait : ");
        		System.out.println(jeu.afficheCombi(jeu.getCombiO()));
        		rejouer(str);
        	}
        	System.out.println ("Il vous reste encore " + (tourMax-tour) + proposition((tourMax-tour)));
        	System.out.println();
        	tour++;
    	}
    }
    
    /**
     * Permet de mettre le mot plac� au singulier ou au pluriel
     * @param nb
     * 		Nombre de proposition restante
     * @return
     * 		Retourne le mot " proposition(s) " au singulier ou au pluriel
     */
    public String proposition (int nb){
    	
    	if (nb>1){
    		return " propositions.";
    	} else {
    		return " proposition.";
    	}
    }
    
    /**
     * M�thode pour le choix utilisateur en fin de jeu 
	 * @see Classe Jeu
	 * 		Pour les diff�rentes m�thodes utilis�s dans celle-ci car elle renvoi vers les diff�rents jeux
	 */
    public void rejouer (String str){
	
		boolean testRejouer = false;
		int choixRejouer = 0;
		Jeu rejouer = new Jeu();
		
		System.out.println();
		System.out.println("Que voulez vous faire ?");
		System.out.println("	1 - Rejouer au m�me jeu");
		System.out.println("	2 - Revenir au menu principal");
		System.out.println("	3 - Quitter le jeu");
		
		do {
            System.out.println("Quel est votre choix ?");
            testRejouer = sc.hasNextInt();
            if(testRejouer){
                choixRejouer = sc.nextInt();
                if (choixRejouer != 1 && choixRejouer != 2 && choixRejouer != 3){
                    System.out.println("Merci de choisir entre les choix 1, 2, 3.");
                    testRejouer = false;
                }
            } else{
                System.out.println("Votre saisi est incorrect. Merci de choisir entre les choix 1, 2, 3.");
                sc.nextLine();
            }
        }while (!testRejouer );
		
		switch(choixRejouer){
			case 1 :
				System.out.println();
				rejouer.presentationRecherche(str);
			case 2 :
				System.out.println();
				rejouer.presentationJeu(str);
			case 3 :
				System.out.println();
				System.out.println("A tr�s bient�t sur Jeu de Logique !");
				System.exit(0);
		}
	}
    
}
