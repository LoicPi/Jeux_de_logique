package com.Projet3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * CreationJeu est une classe contenant toutes les m�thodes permettant la cr�ation de Recherche +/- et Mastermind.
 * 
 * @author Lo�c
 * @version 1.0
 */

public class CreationJeu {
	
	/**
	 * Cr�ation d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = Logger.getLogger(CreationJeu.class);
	
	/**
	 * Cr�ation d'un objet prop pour r�cup�rer les propri�t�s d�finit dans le fichier   
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	/**
	 * size d�finit la taille des combinaisons
	 */
	int size = Integer.parseInt(prop.valeurPropriete("jeu.size"));
	
	/**
	 * nbreTour d�finit le nombre de tour de jeu
	 */
	int nbreTour = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * nbreCouleurs d�finit le nombre de couleurs dans le Mastermind, il est d�finit entre 4 et 10
	 */
	int nbreCouleurs = Integer.parseInt(prop.valeurPropriete("jeu.nbreCouleurs"));
	/**
	 * combiO tableau d'entier contenant la combinaison de l'ordinateur
	 */
	int [] combiO;
	
	/**
	 * propoO tableau d'entier pr�vu pour contenir la combinaison de l'ordinateur
	 */
	int [] propoO;
	
	/**
	 * chiffreOccurence est une liste de chiffre, elle est utilis� dans la m�thode de cr�ation d'une proposition de l'Ordi pour le Mastermind
	 */
	ArrayList<Integer> chiffreOccurence = new ArrayList<Integer>();
	
	/**
	 * Permet de cr�er un objet Scanner qui va r�cup�rer les entr�es des utilisateurs
	 */
	Scanner sc = new Scanner(System.in);
	
	
	
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
    	logger.debug("On cr�e une combinaison.");
        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(10);
        }
    }
    
    /**
     * Cr�er une proposition faite par l'odinateur en tenant compte de la proposition pr�c�dente
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
    		//Si on se trouve au tour 1 dans ce cas la combinaison est compos� que du chiffre 7 pour d�part
    		if(tour == 1){
    			propoO[i] = 7;
    		}
    		//Si le tableau de r�ponse � la position i est "=" dans ce cas il garde la valeur de sa pr�c�dente proposition
    		if (tab[i] == "="){
    			logger.debug("La position " + i + "du tableau de r�ponse est =");
    			propoO[i] = com[i];
    		}
    		//Si le tableau de r�ponse � la position i est "+" dans ce cas suivant la proposition s'adapte au num�ro de tour auquel on est
    		if (tab[i] == "+"){
    			logger.debug("La position" + i + "du tableau de r�ponse est +");
    			logger.debug("On est au tour n� " + tour);
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
    		//Si le tableau de r�ponse � la position i est "-" dans ce cas suivant la proposition s'adapte au num�ro de tour auquel on est
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
     * Transforme un tableau d'entier en cha�ne de caract�re
     * @param str
     * 		Tableau d'entier
     * @return
     * 		Retourne une cha�ne de caract�re
     */
    public String afficheCombi(int tab[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < size; i++){
    		tbStr = tbStr + tab[i];
    	}
    	
    	return tbStr;
    }

    /**
     * Renvoi un tableau d'entier suite � une proposition saisi par l'utilisateur
     * @param str
     * 		Le parem�tre permet d'afficher une cha�ne de caract�re avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier compos� des chiffres tap�es par la personne
     */
    public int [] combiHumain (String str) {

        boolean testCombi = false;
        String combi = "";
        
        //Tant que la cha�ne de caract�re n'est pas un entier ou de taille de "size" alors il r�it�re la demande de propositon ou combinaison
        do{
        	System.out.print(str);
        	testCombi = sc.hasNextInt();
        	if (testCombi) {
        		combi = sc.nextLine();
        		if (combi.length() != size){
        			logger.error("La taille n'est pas bonne, elle est de " + combi.length() + " au lieu de " + size + ".");
        			System.out.println("Merci de proposer une combinaison de taille " + size +".");
                    testCombi = false;
        		}
        	}else {
        		logger.error("La combinaison n'est pas bonne car ce n'est pas un entier.");
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        		sc.nextLine();
        	}
        } while(testCombi != true);	
     
        int [] combiH = new int[size];
        
        //Transforme chacun des caract�res de la cha�ne de caract�re en un entier sur une base 10
        for(int i = 0; i < size; i++){
        	combiH[i] = Character.digit(combi.charAt(i), 10);
        }
        return combiH;
    }
    
    /**
     * Renvoi un tableau de cha�ne de caract�re suite � la comparaison de 2 tableaux d'entiers dans le jeu Recherche +/-
     * @param combi
     * 		Le tableau de combinaison secr�te de l'humain ou de l'ordinateur
     * @param propo
     * 		Le tableau de proposition soit de l'humain ou de l'ordinateur
     * @return
     * 		Retourne le tableau de type cha�ne de caract�re de r�ponse pour le jeu Recherche 
     */
    public String [] compareProposition(int[] combi, int[] propo){
    	
    	String [] repo = new String [size];    	
    	
    	//On compare pour les deux tableaux � la position i afin de renvoyer une r�ponse en cha�ne de caract�re. Cette r�ponse est mise � la position i dans le tableau de cha�ne de caract�re
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
     * Transforme un tableau de cha�ne de caract�re en une cha�ne de caract�re
     * Renvoi la chaine de caract�re en sortie
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
     * V�rifie si le tableau de r�ponse dans Recherche +/- contient un �l�ment diff�rent de =
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
     * Cr�er une combinaison secr�te par l'ordinateur via un m�thode al�atoire
	 * Remplit le tableau combiO de chiffre al�atoire entre 0 et le nombre de couleurs pr�d�termin�.
     * @param nbreCouleurs
     * 		Le nombre de couleurs est pr�d�termin� dans le fichier de configuration, il est compris entre 0 et 9
     */
    public  void combiMasterOrdi (){

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
        	//On met nbreCouleurs+1 car le chiffre de couleurs doit �tre compris entre 4 et 10 et nbreCouleurs est compris entre 0 et 9 
            combiO[i] = rd.nextInt(nbreCouleurs+1);
        }
    }
    
    /**
     * Cr�er une proposition suite � une saisi utilisateur et renvoi un tableau d'entier
     * @param str
     * 		Le param�tre permet d'afficher une cha�ne de caract�re avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier compos� des chiffres tap�es par la personne
     */
    public int [] combiMasterHumain (String str) {

        boolean testCombi = false;
        String combi = "";
        int [] combiH = new int[size];
        
        
        do{
        	System.out.print(str);
        	testCombi = sc.hasNextInt();
        	if (testCombi) {
        		combi = sc.nextLine();
        		//Tant que la cha�ne de caract�re n'est pas de taille de "size" alors il r�it�re la demande de propositon ou combinaison
        		if (combi.length() != size){
        			System.out.println("Merci de proposer une combinaison de taille " + size +".");
                    testCombi = false;
        		} else {
        			//Il transforme chacun des caract�res de la cha�ne de caract�re en entier de base 10 et v�rifie que cet entier n'est pas strictement sup�rieur � nbreCouleurs
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
        		//Tant que la cha�ne de caract�re n'est pas un entier alors il r�it�re la demande de propositon ou combinaison
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        		sc.nextLine();
        	}
        } while(testCombi != true);	
     
        return combiH;
    }
    
    /**
     * Cr�er un tableau d'entier contenant le nombre de chiffres bien plac�s et mal plac�s pour le jeu Mastermind
     * Retourne ensuite ce tableau
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
    		// Compare � la position i les valeurs des tableaux de combinaison et de proposition et suivant si elles sont �gales ou non 
    		// Il va remplir diff�rement les tableaux combinaison Prim et proposition Prim
    		if (combi[i] == propo[i]){
    			nbBienPlace = nbBienPlace + 1;
    			combiPrim[i] = -1;
    			propoPrim[i] = -2;
    		} else {
    			combiPrim[i] = combi[i];
    			propoPrim[i] = propo[i];
    		}
    	}
    	
    	// Ensuite pour chacune des valeurs aux positions i du tableau de proposition Prim on recherche cette valeur dans le tableau de combinaison Prim
    	// Si il s'y trouve dans ce cas le nombre de chiffre mal plac� augmente
    	for (int i = 0; i < size; i++){
    		if (nombreRecherche (combiPrim, propoPrim[i])){
    			nbMalPlace = nbMalPlace + 1;    			
    		}
       	}
    	
    	// On incr�mente alors le tableau d'entier des valeurs de chiffres bien plac�s et mal plac�s
    	nbPlace[0] = nbBienPlace;
    	nbPlace[1] = nbMalPlace;
    	
    	return nbPlace;
    }
    
    /**
     * V�rifie si un nombre se trouve dans un tableau d'entier
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
        	// Pour chaque �l�ment � la position i on regarde si il est �gale � l'entier et si cela s'av�re vrai
        	// Si cela s'av�re vrai, on modifie la valeur dans le tableau � la position i
        	// Cela permet d'�viter les doublons de pr�sence si deux chiffres identiques dans la proposition mais un seul pr�sent dans la combinaison
        	if (combi[i] == propo) {
        		combi[i] = -1;
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Cr�er une phrase de r�ponse en prenant en compte less chiffres bien et mal plac�s
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
     * Cr�er une proposition de combinaison par l'ordinateur
     * Elle renvoi un tableau d'entier
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
    	
    	// Si on se trouve au tour dans ce cas on cr�e une proposition al�atoire entre 0 et 1+nbreCouleurs (d�finit dans le fichier properties)
    	if (tour == 1){
    		for (int i = 0; i < size; i++){
    			propoO[i] = rd.nextInt(nbreCouleurs+1);
    		}
		}
		else {
			
			// Si le nbre de chiffre mal plac�s est diff�rent de 0
			if (nbPlace[1] != 0){
				// V�rification � chacune des positions i des valeurs du tableau de combinaison et proposition 
				for (int i = 0; i < size; i++){
					// Si elles sont diff�rentes alors il ajoute � une liste la valeur � la position i du tableau de proposition 
					if (propo[i] != combi[i]) {
						rot.add(propo [i]);
					}
				}
			// Il effectue alors une rotation des valeurs de la liste cr�e via la boucle for 	
			tro = rotation(rot);
			// Il cr�e la proposition de l'ordinateur
			propoO = creationPropoOrdi(tro, combi, propo);
			} else {
				// Sinon pour chacune des valeurs � la position i
				for (int i = 0; i < size; i++){
					if (nbPlace[0] != 0){
						// Si le le nombre de chiffres bien plac�s est diff�rents de 0 et que les valeurs � la position i 
						// de la combinaison et de la proposition sont �gales alors on garde la valeur de la proposition � la position i
						if(propo[i] == combi[i]){
							propoO[i] = propo[i];
						} else {
							if (nbPlace[0] != (size-1)){
								// Sinon si le nombre de chiffres bien plac�s est diff�rent de la (taille-1) on prend un chiffre aux hasards tant que ce chiffre est diff�rent aux chiffres de la proposition pr�c�dente
								do{
									propoO[i] = rd.nextInt(nbreCouleurs+1);
								}while (propoO[i] == propo[i]);
							} else {
								// Sinon on choisit al�atoirement un chiffre on v�rifie que celui ci n'est pas pr�sent dans une liste qui contient les chiffres d�j� test� et si il n'est pas pr�sent on le rajoute � la list et on met cette valeur dans la proposition
								// Cela permet � l'ordinateur de ne pas proposer toujours la m�me valeur pour le dernier chiffre de la combinaison � trouver
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
						// Sinon si le chiffre de bien plac�s est nul alors on cr�e une combinaison de chiffre al�atoire
						do{
							propoO[i] = rd.nextInt(nbreCouleurs+1);
						}while (propoO[i] == propo[i]);
					}
				}
			}
		}
    }
    
    /**
     * V�rifie si un chiffre est pr�sent dans un tableau.
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
     * Renvoi la liste suite � la rotation
     * @see propoOrdiMaster
     * 		Renvoi � cette m�thode pour son utilisation
     * @param rot
     * 		Liste d'entier dont les �l�ments vont effectuer une rotation
     * @return
     */
    public ArrayList<Integer> rotation (ArrayList<Integer> rot){
    	
    	// On affecte � un entier la valeur du dernier �l�m�nt de la liste
    	int temp = rot.get(rot.size()-1);
    	
    	for (int i = (rot.size()-1); i > 0; i--){
    		//Pour tous les �l�ments de la liste d'entier on affecte � la place i l'�l�ment situ� � la place i-1 en partant de l'avant dernier �l�ment et en descendant
    		rot.set(i,rot.get(i-1));
    	}
    	// On affecte � la position 0 l'entier d�finit au d�but de la m�thode
    	rot.set(0, temp);
    	
    	return rot;	
    }
    
    /**
     * Cr�er un tableau d'entier qu'elle retourne
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
    		// On compare les �l�ments � la position i des deux tableaux d'entier
    		if (combi[i] == propo[i]){
    			// Si ils sont �gaux, on ajoute � la liste d'entier � la position i, la valeur � la position i du tableau de proposition
    			rot.add(i, propo[i]);
    		}		
    	}
    	
    	// On effectue ensuite une boucle afin de cr�er le tableau d'entier en ajoutant � la position i la valeur de la liste d'entier � la m�me position
    	for (int i = 0; i < size; i++){
    		creationPropo[i] = rot.get(i);
    	}
    	
    	return creationPropo;
    }
}
