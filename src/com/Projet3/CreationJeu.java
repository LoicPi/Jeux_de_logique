package com.Projet3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * CreationJeu est une classe contenant toutes les méthodes permettant la création de Recherche +/- et Mastermind.
 * 
 * @author Loïc
 * @version 1.0
 */

public class CreationJeu {
	
	/**
	 * Création d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = Logger.getLogger(CreationJeu.class);
	
	/**
	 * Création d'un objet prop pour récupérer les propriétés définit dans le fichier   
	 */
	ProprieteJeu prop = new ProprieteJeu();
	
	/**
	 * size définit la taille des combinaisons
	 */
	int size = Integer.parseInt(prop.valeurPropriete("jeu.size"));
	
	/**
	 * nbreTour définit le nombre de tour de jeu
	 */
	int nbreTour = Integer.parseInt(prop.valeurPropriete("jeu.nbreTour"));
	
	/**
	 * nbreCouleurs définit le nombre de couleurs dans le Mastermind, il est définit entre 4 et 10
	 */
	int nbreCouleurs = Integer.parseInt(prop.valeurPropriete("jeu.nbreCouleurs"));
	/**
	 * combiO tableau d'entier contenant la combinaison de l'ordinateur
	 */
	int [] combiO;
	
	/**
	 * propoO tableau d'entier prévu pour contenir la combinaison de l'ordinateur
	 */
	int [] propoO;
	
	/**
	 * chiffreOccurence est une liste de chiffre, elle est utilisé dans la méthode de création d'une proposition de l'Ordi pour le Mastermind
	 */
	ArrayList<Integer> chiffreOccurence = new ArrayList<Integer>();
	
	/**
	 * Permet de créer un objet Scanner qui va récupérer les entrées des utilisateurs
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
	 * Met à jour la combinaison de l'ordinateur
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
	 * Met à jour la proposition de l'ordinateur
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
	 * Met à jour la taille de la combinaison
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
	 * Met à jour le nombre de tour de jeu
	 * @param nbreTour
	 * Le nouveau nombre de tour de jeu
	 */
	public void setNbreTour(int nbreTour) {
		this.nbreTour = nbreTour;
	}

	/**
	 * Créer une combinaison secrète par l'ordinateur via un random
	 * Remplit le tableau combiO de chiffre aléatoire entre 0 et 9
	 */
    public  void combiOrdi (){
    	logger.debug("On crée une combinaison.");
        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(10);
        }
    }
    
    /**
     * Créer une proposition faite par l'odinateur en tenant compte de la proposition précédente
     * @param tab 
     * 		La réponse à l'ancienne propositon
     * @param tour 
     * 		Le nomnbre de tour sur lesquels on se trouve
     * @param str
     * 		La phrase proposition
     * @param com
     * 		La proposition précédente
     */  
    public void propoOrdi (String [] tab, int tour, String str, int[] com){
    	
    	propoO = new int[size];
    	
    	System.out.print(str);
    	
    	for (int i = 0; i < size; i++){
    		//Si on se trouve au tour 1 dans ce cas la combinaison est composé que du chiffre 7 pour départ
    		if(tour == 1){
    			propoO[i] = 7;
    		}
    		//Si le tableau de réponse à la position i est "=" dans ce cas il garde la valeur de sa précédente proposition
    		if (tab[i] == "="){
    			logger.debug("La position " + i + "du tableau de réponse est =");
    			propoO[i] = com[i];
    		}
    		//Si le tableau de réponse à la position i est "+" dans ce cas suivant la proposition s'adapte au numéro de tour auquel on est
    		if (tab[i] == "+"){
    			logger.debug("La position" + i + "du tableau de réponse est +");
    			logger.debug("On est au tour n° " + tour);
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
    		//Si le tableau de réponse à la position i est "-" dans ce cas suivant la proposition s'adapte au numéro de tour auquel on est
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
     * Transforme un tableau d'entier en chaîne de caractère
     * @param str
     * 		Tableau d'entier
     * @return
     * 		Retourne une chaîne de caractère
     */
    public String afficheCombi(int tab[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < size; i++){
    		tbStr = tbStr + tab[i];
    	}
    	
    	return tbStr;
    }

    /**
     * Renvoi un tableau d'entier suite à une proposition saisi par l'utilisateur
     * @param str
     * 		Le paremètre permet d'afficher une chaîne de caractère avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier composé des chiffres tapées par la personne
     */
    public int [] combiHumain (String str) {

        boolean testCombi = false;
        String combi = "";
        
        //Tant que la chaîne de caractère n'est pas un entier ou de taille de "size" alors il réitère la demande de propositon ou combinaison
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
        
        //Transforme chacun des caractères de la chaîne de caractère en un entier sur une base 10
        for(int i = 0; i < size; i++){
        	combiH[i] = Character.digit(combi.charAt(i), 10);
        }
        return combiH;
    }
    
    /**
     * Renvoi un tableau de chaîne de caractère suite à la comparaison de 2 tableaux d'entiers dans le jeu Recherche +/-
     * @param combi
     * 		Le tableau de combinaison secrète de l'humain ou de l'ordinateur
     * @param propo
     * 		Le tableau de proposition soit de l'humain ou de l'ordinateur
     * @return
     * 		Retourne le tableau de type chaîne de caractère de réponse pour le jeu Recherche 
     */
    public String [] compareProposition(int[] combi, int[] propo){
    	
    	String [] repo = new String [size];    	
    	
    	//On compare pour les deux tableaux à la position i afin de renvoyer une réponse en chaîne de caractère. Cette réponse est mise à la position i dans le tableau de chaîne de caractère
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
     * Transforme un tableau de chaîne de caractère en une chaîne de caractère
     * Renvoi la chaine de caractère en sortie
     * @param tab
     * 		Tableau de chaîne de caractère
     */
    public void afficheReponse(String[] tab) {
    	
    	String reponse = "";
    	
    	for (int i = 0; i < size; i++){
    		reponse = reponse + tab[i];
    	}
    	System.out.println(reponse);
    }
    
    /**
     * Vérifie si le tableau de réponse dans Recherche +/- contient un élèment différent de =
     * Cela permet de savoir si la combinaison secrète a été trouvé
     * @param tab
     * 		Tableau de réponse en chaîne de caractère
     * @return
     * 		Retourne un booléen vrai si tous les éléments du tableau sont de type = sinon faux
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
     * Vérifie si le tour actuel est bien inférieur au nombre de tour max
     * Cela permet de savoir si l'on ne dépasse le nombre de tour maximal autorisé
     * @param tour
     * 		numéro du tour actuel
     * @return
     * 		Retourne un booléen vrai si le tour actuel est inférieur au nombre de tour max sinon faux
     */
    public boolean verifTour (int tour){
    	
    	if (tour < nbreTour){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Créer une combinaison secrète par l'ordinateur via un méthode aléatoire
	 * Remplit le tableau combiO de chiffre aléatoire entre 0 et le nombre de couleurs prédéterminé.
     * @param nbreCouleurs
     * 		Le nombre de couleurs est prédéterminé dans le fichier de configuration, il est compris entre 0 et 9
     */
    public  void combiMasterOrdi (){

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
        	//On met nbreCouleurs+1 car le chiffre de couleurs doit être compris entre 4 et 10 et nbreCouleurs est compris entre 0 et 9 
            combiO[i] = rd.nextInt(nbreCouleurs+1);
        }
    }
    
    /**
     * Créer une proposition suite à une saisi utilisateur et renvoi un tableau d'entier
     * @param str
     * 		Le paramètre permet d'afficher une chaîne de caractère avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier composé des chiffres tapées par la personne
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
        		//Tant que la chaîne de caractère n'est pas de taille de "size" alors il réitère la demande de propositon ou combinaison
        		if (combi.length() != size){
        			System.out.println("Merci de proposer une combinaison de taille " + size +".");
                    testCombi = false;
        		} else {
        			//Il transforme chacun des caractères de la chaîne de caractère en entier de base 10 et vérifie que cet entier n'est pas strictement supérieur à nbreCouleurs
        	        for(int i = 0; i < size; i++){
        	        	combiH[i] = Character.digit(combi.charAt(i), 10);
        	        	if (combiH[i] > nbreCouleurs){
        	        		System.out.println("Merci de proposer une combinaison où les chiffres sont compris entre 0 et " + nbreCouleurs +".");
        	        		testCombi = false;
        	        		break;
        	        	}
        	        }
        		}
        	}else {
        		//Tant que la chaîne de caractère n'est pas un entier alors il réitère la demande de propositon ou combinaison
        		System.out.println("La combinaison n'est pas bonne, merci de rentrer un entier.");
        		sc.nextLine();
        	}
        } while(testCombi != true);	
     
        return combiH;
    }
    
    /**
     * Créer un tableau d'entier contenant le nombre de chiffres bien placés et mal placés pour le jeu Mastermind
     * Retourne ensuite ce tableau
     * @param combi
     * 		Tableau d'entier de la combinaison
     * @param propo
     * 		Tableau d'entier de la proposition
     * @return
     * 		Retourne un tableau d'entier contenant le nombre de chiffres bien placés en 1er élèment
     * 		et le nombre de chiffres mal placés en 2ème élèment. 
     */
    
    public int[] nombrePlacement (int[] combi, int[] propo){
    	
    	int[] combiPrim = new int[size];
    	int[] propoPrim = new int[size];
    	int nbBienPlace = 0;
    	int nbMalPlace = 0;
    	int nbPlace [] = new int[2];
    	
    	for (int i = 0; i < size; i++){
    		// Compare à la position i les valeurs des tableaux de combinaison et de proposition et suivant si elles sont égales ou non 
    		// Il va remplir différement les tableaux combinaison Prim et proposition Prim
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
    	// Si il s'y trouve dans ce cas le nombre de chiffre mal placé augmente
    	for (int i = 0; i < size; i++){
    		if (nombreRecherche (combiPrim, propoPrim[i])){
    			nbMalPlace = nbMalPlace + 1;    			
    		}
       	}
    	
    	// On incrémente alors le tableau d'entier des valeurs de chiffres bien placés et mal placés
    	nbPlace[0] = nbBienPlace;
    	nbPlace[1] = nbMalPlace;
    	
    	return nbPlace;
    }
    
    /**
     * Vérifie si un nombre se trouve dans un tableau d'entier
     * @see nombrePlacement
     * 		Pour son utilisation
     * @param combi
     * 		Tableau d'entier de la combinaison
     * @param propo
     * 		Tableau d'entier de la proposition
     * @return
     * 		Retourne un booléen vrai si le chiffre est bien présent dans le tableau, 
     * 		modifie l'élément du tableau par la valeur -1 et retourne faux si le chiffre n'est pas présent dans le tableau
     */
    public boolean nombreRecherche (int combi[], int propo) {

        for (int i = 0 ; i < size ; i++) {
        	// Pour chaque élément à la position i on regarde si il est égale à l'entier et si cela s'avère vrai
        	// Si cela s'avère vrai, on modifie la valeur dans le tableau à la position i
        	// Cela permet d'éviter les doublons de présence si deux chiffres identiques dans la proposition mais un seul présent dans la combinaison
        	if (combi[i] == propo) {
        		combi[i] = -1;
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Créer une phrase de réponse en prenant en compte less chiffres bien et mal placés
     * @param nbPlace
     * 		Tableau d'entier indiquant le nombre de chiffres bien placés et le nombre de chiffres mal placés
     * @return
     * 		Retourne une chaîne de caractère contenant la phrase de réponse
     */
    public String reponse (int [] nbPlace){
    	String rep = "";
    	
    	if (nbPlace[0] == 0 && nbPlace[1] == 0){
    		rep = "Aucun des nombres ne correspondent à la combinaison !";
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
     * Permet de mettre le mot présent au singulier ou au pluriel
     * @param nb
     * 		Nombre de chiffres mal placés
     * @return
     * 		Retourne le mot "présent(s)" au singulier ou au pluriel
     */
    public String present (int nb){
    	
    	if (nb>1){
    		return "présents";
    	} else {
    		return "présent";
    	}
    }
    
    /**
     * Permet de mettre le mot placé au singulier ou au pluriel
     * @param nb
     * 		Nombre de chiffres bien placés
     * @return
     * 		Retourne le mot "placé(s)" au singulier ou au pluriel
     */
    public String place (int nb){
    	
    	if (nb>1){
    		return "placés";
    	} else {
    		return "placé";
    	}
    }
    
    /**
     * Vérifie le nombre de chiffre bien placés dans la proposition
     * @param nbPlace
     * 		Tableau d'entier contant le nombre de chiffres bien placés et le nombre de chiffres mal placés
     * @return
     * 		Retourne un booléen vrai si le nombre de chiffre de bien placés est égale à la taille définit tout en haut de la classe
     */
    public boolean verifNombreBienPlace (int [] nbPlace){
    	
    	if (nbPlace[0] == size) {
    		return true;
    	}
    	return false;
    }

    /**
     * Créer une proposition de combinaison par l'ordinateur
     * Elle renvoi un tableau d'entier
     * @param propo
     * 		Tableau d'entier contenant la dernière proposition de l'ordinateur
     * @param nbPlace
     * 		Tableau d'entier contenant le nombre de chiffres bien placés et mal placés de la dernière proposition
     * @param combi
     * 		Tableau d'entier contenant la combinaison secrète
     * @param str
     * 		Chaine de caractère pour renvoi du mot "Proposition"
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
    	
    	// Si on se trouve au tour dans ce cas on crée une proposition aléatoire entre 0 et 1+nbreCouleurs (définit dans le fichier properties)
    	if (tour == 1){
    		for (int i = 0; i < size; i++){
    			propoO[i] = rd.nextInt(nbreCouleurs+1);
    		}
		}
		else {
			
			// Si le nbre de chiffre mal placés est différent de 0
			if (nbPlace[1] != 0){
				// Vérification à chacune des positions i des valeurs du tableau de combinaison et proposition 
				for (int i = 0; i < size; i++){
					// Si elles sont différentes alors il ajoute à une liste la valeur à la position i du tableau de proposition 
					if (propo[i] != combi[i]) {
						rot.add(propo [i]);
					}
				}
			// Il effectue alors une rotation des valeurs de la liste crée via la boucle for 	
			tro = rotation(rot);
			// Il crée la proposition de l'ordinateur
			propoO = creationPropoOrdi(tro, combi, propo);
			} else {
				// Sinon pour chacune des valeurs à la position i
				for (int i = 0; i < size; i++){
					if (nbPlace[0] != 0){
						// Si le le nombre de chiffres bien placés est différents de 0 et que les valeurs à la position i 
						// de la combinaison et de la proposition sont égales alors on garde la valeur de la proposition à la position i
						if(propo[i] == combi[i]){
							propoO[i] = propo[i];
						} else {
							if (nbPlace[0] != (size-1)){
								// Sinon si le nombre de chiffres bien placés est différent de la (taille-1) on prend un chiffre aux hasards tant que ce chiffre est différent aux chiffres de la proposition précédente
								do{
									propoO[i] = rd.nextInt(nbreCouleurs+1);
								}while (propoO[i] == propo[i]);
							} else {
								// Sinon on choisit aléatoirement un chiffre on vérifie que celui ci n'est pas présent dans une liste qui contient les chiffres déjà testé et si il n'est pas présent on le rajoute à la list et on met cette valeur dans la proposition
								// Cela permet à l'ordinateur de ne pas proposer toujours la même valeur pour le dernier chiffre de la combinaison à trouver
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
						// Sinon si le chiffre de bien placés est nul alors on crée une combinaison de chiffre aléatoire
						do{
							propoO[i] = rd.nextInt(nbreCouleurs+1);
						}while (propoO[i] == propo[i]);
					}
				}
			}
		}
    }
    
    /**
     * Vérifie si un chiffre est présent dans un tableau.
     * @see propoOrdiMaster
     * 		Renvoi à cette méthode pour son utilisation
     * @param combi
     * 		Tableau d'entier parcouru
     * @param propo
     * 		Chiffre à recherche
     * @return
     * 		Retourne un booléen vrai si le chiffre est contenu dans le tableau sinon faux
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
     * Effectue la rotation des éléments d'une liste d'entier
     * Renvoi la liste suite à la rotation
     * @see propoOrdiMaster
     * 		Renvoi à cette méthode pour son utilisation
     * @param rot
     * 		Liste d'entier dont les éléments vont effectuer une rotation
     * @return
     */
    public ArrayList<Integer> rotation (ArrayList<Integer> rot){
    	
    	// On affecte à un entier la valeur du dernier élémént de la liste
    	int temp = rot.get(rot.size()-1);
    	
    	for (int i = (rot.size()-1); i > 0; i--){
    		//Pour tous les éléments de la liste d'entier on affecte à la place i l'élément situé à la place i-1 en partant de l'avant dernier élément et en descendant
    		rot.set(i,rot.get(i-1));
    	}
    	// On affecte à la position 0 l'entier définit au début de la méthode
    	rot.set(0, temp);
    	
    	return rot;	
    }
    
    /**
     * Créer un tableau d'entier qu'elle retourne
     * @see propoOrdiMaster
     * 		Renvoi à cette méthode pour son utilisation
     * @param rot
     * 		Liste d'entier où la rotation a été effectué au préalable
     * @param combi
     * 		Tableau d'entier faisant référence à la combinaison secrète
     * @param propo
     * 		Tableau d'entier faisant référence à la proposition de l'ordinateur
     * @return
     * 		Retourne un tableau d'entier pour la nouvelle proposition
     */
    public int [] creationPropoOrdi (ArrayList<Integer> rot, int[]combi, int[] propo){
    	
    	int[] creationPropo = new int[size];
    	
    	for (int i = 0; i < size; i++){
    		// On compare les éléments à la position i des deux tableaux d'entier
    		if (combi[i] == propo[i]){
    			// Si ils sont égaux, on ajoute à la liste d'entier à la position i, la valeur à la position i du tableau de proposition
    			rot.add(i, propo[i]);
    		}		
    	}
    	
    	// On effectue ensuite une boucle afin de créer le tableau d'entier en ajoutant à la position i la valeur de la liste d'entier à la même position
    	for (int i = 0; i < size; i++){
    		creationPropo[i] = rot.get(i);
    	}
    	
    	return creationPropo;
    }
}
