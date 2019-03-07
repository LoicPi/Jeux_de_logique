package com.Projet3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * CreationJeu est une classe contenant toutes les m�thodes permettant la cr�ation de Recherche +/- et Mastermind.
 * 
 * @author Lo�c
 * @version 1.0
 */

public class CreationJeu {
	
	/**
	 * combiO tableau d'entier contenant la combinaison de l'ordinateur 
	 * propoO tableau d'entier pr�vu pour contenir la combinaison de l'ordinateur
	 * size d�finit la taille des combinaisons
	 * nbreTour d�finit le nombre de tour de jeu 
	 */
	
	int [] combiO;
	int [] propoO;
	int size = 4;
	int nbreTour = 10;
	int nbreCouleurs = 5;
	ArrayList<Integer> chiffreOccurence = new ArrayList<Integer>();
	
	/**
	 * Retourne la combinaison de l'ordinateur
	 * @return la combinaison de l'ordinateur
	 */
	public int[] getCombiO() {
		return combiO;
	}
	/**
	 * Met � jour la combinaison de l'ordinateur
	 * @param combiO
	 * La nouvelle combinaison de l'ordinateur
	 */
	public void setCombiO(int[] combiO) {
		this.combiO = combiO;
	}

	/**
	 * Retourne la proposition de l'ordinateur
	 * @return la proposition de l'ordinateur
	 */
	public int[] getPropoO() {
		return propoO;
	}

	/**
	 * Met � jour la proposition de l'ordinateur
	 * @param propoO
	 * La nouvelle proposition de l'ordinateur
	 */
	public void setPropoO(int[] propoO) {
		this.propoO = propoO;
	}

	/**
	 * Retourne la taille de la combinaison
	 * @return la taille de la proposition
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Met � jour la taille de la combinaison
	 * @param size
	 * La nouvelle taille de la proposition
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Retourne le nombre de tour de jeu
	 * @return le nombre de tour de jeu
	 */
	public int getNbreTour() {
		return nbreTour;
	}

	/**
	 * Met � jour le nombre de tour de jeu
	 * @param nbreTour
	 * Le nouveau nombre de tour de jeu
	 */
	public void setNbreTour(int nbreTour) {
		this.nbreTour = nbreTour;
	}

	/**
	 * Cr�er une combinaison secr�te par l'ordinateur via un random
	 * Remplit le tableau combiO de chiffre al�atoire entre 0 et 9
	 */
    public  void combiOrdi (){

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(10);
        }
    }
    
    /**
     * Cr�er une proposition faite par l'odinateur en prenant en compte des param�tres
     * Il tient compte des tours pour cr�er sa proposition.
     * Cette m�thode permet de s'assurer que l'ordinateur trouve la solution en 5 tours maximum 
     * La m�thode permet le remplissage du tableau propoO via des conditions de tour,
     * et en tenant compte de la r�ponse pr�c�dente ainsi que du chiffre de la propositon pr�c�dente
     * @param tab 
     * 		La r�ponse � l'ancienne propositon
     * @param tour 
     * 		Le nomnbre de tour sur lesquels on se trouve
     * @param str
     * 		La phrase proposition
     * @param com
     * 		La proposition pr�c�dente
     */  
    public void propoOrdi (String [] tab, int tour, String str, int[] com){
    	
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
    
    /**
     * Affiche un tableau d'entier en cha�ne de caract�re
     * Cela permet d'afficher sur une console la combinaison ou la proposition g�n�rer par l'ordinateur
     * @param str
     * 		Tableau d'entier
     * @return
     * 		Retourne une cha�ne de caract�re
     */
    public String afficheCombi(int str[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < size; i++){
    		tbStr = tbStr + str[i];
    	}
    	
    	return tbStr;
    }

    /**
     * Cr�er une proposition faite par une personne en tenant compte de la taille de la proposition
     * et si elle ne contient que des entiers
     * Cette proposition est soumise � une v�rification sur le type de donn�es rentr�s et sur la taille de l'entr�e
     * Ensuite cette proposition est mise dans un tableau d'entier qui sera renvoy�
     * @param str
     * 		Le parem�tre permet d'afficher une cha�ne de caract�re avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier compos� des chiffres tap�es par la personne
     */
    public int [] combiHumain (String str) {
    	  	
        Scanner sc = new Scanner(System.in);
        boolean testCombi = false;
        String combi = "";
        
        do{
        	System.out.print(str);
        	testCombi = sc.hasNextInt();
        	if (testCombi) {
        		combi = sc.nextLine();
        		if (combi.length() != size){
        			System.out.println("Merci de proposer une combinaison de taille " + size +".");
                    testCombi = false;
        		}
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
    
    /**
     * Compare la proposition et la combinaison dans le jeu Recherche.
     * A savoir chacun des chiffres des deux tableaux est compar�s 
     * et suivant la comparaison une cha�ne de caract�re (+, -, =) est mise dans le tableau de r�ponse.
     * Ce tableau de cha�ne de caract�re est ensuite renvoy�.
     * @param combi
     * 		Le tableau de combinaison secr�te de l'humain ou de l'ordinateur
     * @param propo
     * 		Le tableau de proposition soit de l'humain ou de l'ordinateur
     * @return
     * 		Retourne le tableau de type cha�ne de caract�re de r�ponse pour le jeu Recherche 
     */
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

    /**
     * Affiche un tableau de cha�ne de caract�re en une cha�ne de caract�re
     * Renvoi la chaine de caract�re affich� par la console.
     * @param tab
     * 		Tableau de cha�ne de caract�re
     */
    public void afficheReponse(String[] tab) {
    	
    	String reponse = "";
    	
    	for (int i = 0; i < size; i++){
    		reponse = reponse + tab[i];
    	}
    	System.out.println(reponse);
    }
    
    /**
     * V�rifie si le tableau de r�ponse contient un �l�ment diff�rent de =
     * Cela permet de savoir si la combinaison secr�te a �t� trouv�
     * @param tab
     * 		Tableau de r�ponse en cha�ne de caract�re
     * @return
     * 		Retourne un bool�en vrai si tous les �l�ments du tableau sont de type = sinon faux
     */
    public boolean verifReponse (String[] tab){
    	
    	for (int i = 0; i < size; i++){
    		if (tab[i] != "="){
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * V�rifie si le tour actuel est bien inf�rieur au nombre de tour max
     * Cela permet de savoir si l'on ne d�passe le nombre de tour maximal autoris�
     * @param tour
     * 		num�ro du tour actuel
     * @return
     * 		Retourne un bool�en vrai si le tour actuel est inf�rieur au nombre de tour max sinon faux
     */
    public boolean verifTour (int tour){
    	
    	if (tour < nbreTour){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Cr�er une combinaison secr�te par l'ordinateur via un random
	 * Remplit le tableau combiO de chiffre al�atoire entre 0 et le nombre de couleurs d�termin� par le joueur.
     * @param nbreCouleurs
     * 		Le nombre de couleurs est d�termin� par le joueur, ce nombre est compris entre 4 et 10
     */
    public  void combiMasterOrdi (){

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(nbreCouleurs+1);
        }
    }
    
    /**
     * Cr�er une proposition faite par une personne en tenant compte de la taille de la proposition
     * et si elle ne contient que des entiers
     * Cette proposition est soumise � une v�rification sur le type de donn�es rentr�s et sur la taille de l'entr�e
     * Ensuite cette proposition est mise dans un tableau d'entier qui sera renvoy�
     * @param str
     * 		Le param�tre permet d'afficher une cha�ne de caract�re avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier compos� des chiffres tap�es par la personne
     */
    public int [] combiMasterHumain (String str) {
    	  	
        Scanner sc = new Scanner(System.in);
        boolean testCombi = false;
        String combi = "";
        int [] combiH = new int[size];
        
        do{
        	System.out.print(str);
        	testCombi = sc.hasNextInt();
        	if (testCombi) {
        		combi = sc.nextLine();
        		if (combi.length() != size){
        			System.out.println("Merci de proposer une combinaison de taille " + size +".");
                    testCombi = false;
        		} else {        	        
        	        for(int i = 0; i < size; i++){
        	        	combiH[i] = Character.digit(combi.charAt(i), 10);
        	        	if (combiH[i] > nbreCouleurs){
        	        		System.out.println("Merci de proposer une combinaison o� les chiffres sont compris entre 0 et " + nbreCouleurs +".");
        	        		testCombi = false;
        	        		break;
        	        	}
        	        }
        		}
        	}else {
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        		sc.nextLine();
        	}
        } while(testCombi != true);	
     
        return combiH;
    }
    
    /**
     * Cr�er un tableau d'entier contenant le nombre de chiffres bien plac�s et mal plac�s pour le jeu Mastermind
     * On compare les tableaux combinaisons et propositions.
     * Suivant des conditions le nombre de chiffres bien plac�s augmentent comme celui des nombres mal plac�s.
     * On utilise des tableaux d'entiers Proposition Prim et Combinaison Prim d�finit dans cette m�thode
     * D�s qu'un chiffre est bien plac�, on modifie les chiffres bien plac�s dans combiPrim et propoPrim.
     * On utilise la m�thode nombreRecherche pour trouver si un chiffre de la proposition Prim se trouve dans la combinaison Prim
     * cela permet de d�terminer le nombre de chiffres mal plac�s.
     * On incr�mente le tableau des nombres plac�s par ceux deux chiffres obtenus.     * 
     * @param combi
     * 		Tableau d'entier de la combinaison
     * @param propo
     * 		Tableau d'entier de la proposition
     * @return
     * 		Retourne un tableau d'entier contenant le nombre de chiffres bien plac�s en 1er �l�ment
     * 		et le nombre de chiffres mal plac�s en 2�me �l�ment. 
     */
    
    public int[] nombrePlacement (int[] combi, int[] propo){
    	
    	int[] combiPrim = new int[size];
    	int[] propoPrim = new int[size];
    	int nbBienPlace = 0;
    	int nbMalPlace = 0;
    	int nbPlace [] = new int[2];
    	
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
    	
    	nbPlace[0] = nbBienPlace;
    	nbPlace[1] = nbMalPlace;
    	
    	return nbPlace;
    }
    
    /**
     * V�rifie si un nombre se trouve dans un tableau d'entier
     * Cette m�thode est utilis� dans la m�thode nombrePlacement
     * @see nombrePlacement
     * 		Pour son utilisation
     * @param combi
     * 		Tableau d'entier de la combinaison
     * @param propo
     * 		Tableau d'entier de la proposition
     * @return
     * 		Retourne un bool�en vrai si le chiffre est bien pr�sent dans le tableau, 
     * 		modifie l'�l�ment du tableau par la valeur -1 et retourne faux si le chiffre n'est pas pr�sent dans le tableau
     */
    public boolean nombreRecherche (int combi[], int propo) {

        for (int i = 0 ; i < size ; i++) {
        	if (combi[i] == propo) {
        		combi[i] = -1;
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Cr�er la phrase de r�ponse en prenant en compte les �l�ments du tableau d'entier des chiffres bien et mal plac�s
     * @param nbPlace
     * 		Tableau d'entier indiquant le nombre de chiffres bien plac�s et le nombre de chiffres mal plac�s
     * @return
     * 		Retourne une cha�ne de caract�re contenant la phrase de r�ponse
     */
    public String reponse (int [] nbPlace){
    	String rep = "";
    	
    	if (nbPlace[0] == 0 && nbPlace[1] == 0){
    		rep = "Aucun des nombres ne correspondent � la combinaison !";
    	}
    	
    	if (nbPlace[0] == 0 && nbPlace[1] != 0){
    		rep =  nbPlace[1] + " " + present(nbPlace[1]);
    	}
    	
    	if (nbPlace[0] != 0 && nbPlace[1] == 0){
    		rep = nbPlace[0] + " bien " + place(nbPlace[0]);
    	}
    	
    	if (nbPlace[0] !=0 && nbPlace[1] != 0){
    		rep = nbPlace[1] + " " + present(nbPlace[1]) + ", " + nbPlace[0] + " bien " + place(nbPlace[0]);
    	}
    	
    	return rep;
    }
    
    /**
     * Permet de mettre le mot pr�sent au singulier ou au pluriel
     * @param nb
     * 		Nombre de chiffres mal plac�s
     * @return
     * 		Retourne le mot "pr�sent(s)" au singulier ou au pluriel
     */
    public String present (int nb){
    	
    	if (nb>1){
    		return "pr�sents";
    	} else {
    		return "pr�sent";
    	}
    }
    
    /**
     * Permet de mettre le mot plac� au singulier ou au pluriel
     * @param nb
     * 		Nombre de chiffres bien plac�s
     * @return
     * 		Retourne le mot "plac�(s)" au singulier ou au pluriel
     */
    public String place (int nb){
    	
    	if (nb>1){
    		return "plac�s";
    	} else {
    		return "plac�";
    	}
    }
    
    /**
     * V�rifie le nombre de chiffre bien plac�s dans la proposition
     * @param nbPlace
     * 		Tableau d'entier contant le nombre de chiffres bien plac�s et le nombre de chiffres mal plac�s
     * @return
     * 		Retourne un bool�en vrai si le nombre de chiffre de bien plac�s est �gale � la taille d�finit tout en haut de la classe
     */
    public boolean verifNombreBienPlace (int [] nbPlace){
    	
    	if (nbPlace[0] == size) {
    		return true;
    	}
    	return false;
    }

    /**
     * Cr�er une proposition de chiffres par l'ordinateur
     * On commence par v�rifier le tour sur lesquels on se trouve.
     * Ensuite on v�rifie le nombre de chiffres mal plac�s.
     * Si on en a on va effectuer une rotation des chiffres autres que ceux bien plac�s afin de retrouver le bon emplacement du ou des chiffres mal plac�s
     * Si on n'a pas de chiffre mal plac�s, on regarde le nombre de chiffre bien plac�s et on g�n�re des chiffres al�atoires
     * @param propo
     * 		Tableau d'entier contenant la derni�re proposition de l'ordinateur
     * @param nbPlace
     * 		Tableau d'entier contenant le nombre de chiffres bien plac�s et mal plac�s de la derni�re proposition
     * @param combi
     * 		Tableau d'entier contenant la combinaison secr�te
     * @param str
     * 		Chaine de caract�re pour renvoi du mot "Proposition"
     * @param tour
     * 		Nombre du tour actuel
     */
    public void propoOrdiMaster (int [] propo, int [] nbPlace, int [] combi, String str, int tour){
    	
    	Random rd = new Random();
    	propoO = new int[size];
    	ArrayList<Integer> rot = new ArrayList<Integer>();
    	ArrayList<Integer> tro = new ArrayList<Integer>();
    	int verifRandom;
    	boolean testRandom = true;
    	
    	System.out.print(str);
    	
    	if (tour == 1){
    		for (int i = 0; i < size; i++){
    			propoO[i] = rd.nextInt(nbreCouleurs+1);
    		}
		}
		else {
			if (nbPlace[1] != 0){
				for (int i = 0; i < size; i++){
					if (propo[i] != combi[i]) {
						rot.add(propo [i]);
					}
				}
			tro = rotation(rot);
			propoO = creationPropoOrdi(tro, combi, propo);
			} else {
				for (int i = 0; i < size; i++){
					if (nbPlace[0] != 0){
						if(propo[i] == combi[i]){
							propoO[i] = propo[i];
						} else {
							if (nbPlace[0] != (size-1)){
								do{
									propoO[i] = rd.nextInt(nbreCouleurs+1);
								}while (propoO[i] == propo[i]);
							} else {
								do {
									verifRandom = rd.nextInt(nbreCouleurs+1);
									testRandom = chiffreOccurence.contains(verifRandom);
									if (!testRandom){
										chiffreOccurence.add(verifRandom);
										propoO[i] = verifRandom;
									}					
								} while (testRandom == true);
							}
						}
					} else {
						do{
							propoO[i] = rd.nextInt(nbreCouleurs+1);
						}while (propoO[i] == propo[i]);
					}
				}
			}
		}
    }
    
    /**
     * V�rifie si un chiffre est pr�sent dans un tableau. Cette m�thode est utilis� dans la construction de la proposition de l'ordinateur
     * @see propoOrdiMaster
     * 		Renvoi � cette m�thode pour son utilisation
     * @param combi
     * 		Tableau d'entier parcouru
     * @param propo
     * 		Chiffre � recherche
     * @return
     * 		Retourne un bool�en vrai si le chiffre est contenu dans le tableau sinon faux
     */
    public boolean doublonNombre (int combi[], int propo) {

        for (int i = 0 ; i < size ; i++) {
        	if (combi[i] == propo) {
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Effectue la rotation des �l�ments d'une liste d'entier
     * Cette m�thode est utilis� dans la m�thode de cr�ation de la proposition par l'ordinateur
     * @see propoOrdiMaster
     * 		Renvoi � cette m�thode pour son utilisation
     * @param rot
     * 		Liste d'entier dont les �l�ments vont effectuer une rotation
     * @return
     */
    public ArrayList<Integer> rotation (ArrayList<Integer> rot){
    	
    	int temp = rot.get(rot.size()-1);
    	
    	for (int i = (rot.size()-1); i > 0; i--){
    		rot.set(i,rot.get(i-1));
    	}
    	rot.set(0, temp);
    	
    	return rot;	
    }
    
    /**
     * Cr�er un tableau d'entier de proposition pour un des cas de la m�thode propoOrdiMaster
     * @see propoOrdiMaster
     * 		Renvoi � cette m�thode pour son utilisation
     * @param rot
     * 		Liste d'entier o� la rotation a �t� effectu� au pr�alable
     * @param combi
     * 		Tableau d'entier faisant r�f�rence � la combinaison secr�te
     * @param propo
     * 		Tableau d'entier faisant r�f�rence � la proposition de l'ordinateur
     * @return
     * 		Retourne un tableau d'entier pour la nouvelle proposition
     */
    public int [] creationPropoOrdi (ArrayList<Integer> rot, int[]combi, int[] propo){
    	
    	int[] creationPropo = new int[size];
    	
    	for (int i = 0; i < size; i++){
    		if (combi[i] == propo[i]){
    			rot.add(i, propo[i]);
    		}		
    	}
    	
    	for (int i = 0; i < size; i++){
    		creationPropo[i] = rot.get(i);
    	}
    	
    	return creationPropo;
    }
}
