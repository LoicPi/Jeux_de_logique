package com.Projet3;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * CreationJeu est une classe contenant toutes les méthodes permettant la création de Recherche +/- et Mastermind.
 * 
 * @author Loïc
 * @version 1.0
 */

public class CreationJeu {
	
	/**
	 * combiO tableau d'entier contenant la combinaison de l'ordinateur 
	 * propoO tableau d'entier prévu pour contenir la combinaison de l'ordinateur
	 * size définit la taille des combinaisons
	 * nbreTour définit le nombre de tour de jeu 
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

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(10);
        }
    }
    
    /**
     * Créer une proposition faite par l'odinateur en prenant en compte des paramètres
     * Il tient compte des tours pour créer sa proposition.
     * Cette méthode permet de s'assurer que l'ordinateur trouve la solution en 5 tours maximum 
     * La méthode permet le remplissage du tableau propoO via des conditions de tour,
     * et en tenant compte de la réponse précédente ainsi que du chiffre de la propositon précédente
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
     * Affiche un tableau d'entier en chaîne de caractère
     * Cela permet d'afficher sur une console la combinaison ou la proposition générer par l'ordinateur
     * @param str
     * 		Tableau d'entier
     * @return
     * 		Retourne une chaîne de caractère
     */
    public String afficheCombi(int str[]){
    	
    	String tbStr = "";
    	
    	for (int i = 0; i < size; i++){
    		tbStr = tbStr + str[i];
    	}
    	
    	return tbStr;
    }

    /**
     * Créer une proposition faite par une personne en tenant compte de la taille de la proposition
     * et si elle ne contient que des entiers
     * Cette proposition est soumise à une vérification sur le type de données rentrés et sur la taille de l'entrée
     * Ensuite cette proposition est mise dans un tableau d'entier qui sera renvoyé
     * @param str
     * 		Le paremètre permet d'afficher une chaîne de caractère avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier composé des chiffres tapées par la personne
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
     * A savoir chacun des chiffres des deux tableaux est comparés 
     * et suivant la comparaison une chaîne de caractère (+, -, =) est mise dans le tableau de réponse.
     * Ce tableau de chaîne de caractère est ensuite renvoyé.
     * @param combi
     * 		Le tableau de combinaison secrète de l'humain ou de l'ordinateur
     * @param propo
     * 		Le tableau de proposition soit de l'humain ou de l'ordinateur
     * @return
     * 		Retourne le tableau de type chaîne de caractère de réponse pour le jeu Recherche 
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
     * Affiche un tableau de chaîne de caractère en une chaîne de caractère
     * Renvoi la chaine de caractère affiché par la console.
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
     * Vérifie si le tableau de réponse contient un élèment différent de =
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
     * Créer une combinaison secrète par l'ordinateur via un random
	 * Remplit le tableau combiO de chiffre aléatoire entre 0 et le nombre de couleurs déterminé par le joueur.
     * @param nbreCouleurs
     * 		Le nombre de couleurs est déterminé par le joueur, ce nombre est compris entre 4 et 10
     */
    public  void combiMasterOrdi (){

        Random rd = new Random();
        
        combiO = new int[size];
        for (int i = 0; i < size; i++) {
            combiO[i] = rd.nextInt(nbreCouleurs+1);
        }
    }
    
    /**
     * Créer une proposition faite par une personne en tenant compte de la taille de la proposition
     * et si elle ne contient que des entiers
     * Cette proposition est soumise à une vérification sur le type de données rentrés et sur la taille de l'entrée
     * Ensuite cette proposition est mise dans un tableau d'entier qui sera renvoyé
     * @param str
     * 		Le paramètre permet d'afficher une chaîne de caractère avant la proposition taper par la personne
     * @return
     * 		Retourne un tableau d'entier composé des chiffres tapées par la personne
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
        	        		System.out.println("Merci de proposer une combinaison où les chiffres sont compris entre 0 et " + nbreCouleurs +".");
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
     * Créer un tableau d'entier contenant le nombre de chiffres bien placés et mal placés pour le jeu Mastermind
     * On compare les tableaux combinaisons et propositions.
     * Suivant des conditions le nombre de chiffres bien placés augmentent comme celui des nombres mal placés.
     * On utilise des tableaux d'entiers Proposition Prim et Combinaison Prim définit dans cette méthode
     * Dès qu'un chiffre est bien placé, on modifie les chiffres bien placés dans combiPrim et propoPrim.
     * On utilise la méthode nombreRecherche pour trouver si un chiffre de la proposition Prim se trouve dans la combinaison Prim
     * cela permet de déterminer le nombre de chiffres mal placés.
     * On incrémente le tableau des nombres placés par ceux deux chiffres obtenus.     * 
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
     * Vérifie si un nombre se trouve dans un tableau d'entier
     * Cette méthode est utilisé dans la méthode nombrePlacement
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
        	if (combi[i] == propo) {
        		combi[i] = -1;
                return true;
            } 
        }
        return false;
    }
    
    /**
     * Créer la phrase de réponse en prenant en compte les éléments du tableau d'entier des chiffres bien et mal placés
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
     * Créer une proposition de chiffres par l'ordinateur
     * On commence par vérifier le tour sur lesquels on se trouve.
     * Ensuite on vérifie le nombre de chiffres mal placés.
     * Si on en a on va effectuer une rotation des chiffres autres que ceux bien placés afin de retrouver le bon emplacement du ou des chiffres mal placés
     * Si on n'a pas de chiffre mal placés, on regarde le nombre de chiffre bien placés et on génère des chiffres aléatoires
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
     * Vérifie si un chiffre est présent dans un tableau. Cette méthode est utilisé dans la construction de la proposition de l'ordinateur
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
     * Cette méthode est utilisé dans la méthode de création de la proposition par l'ordinateur
     * @see propoOrdiMaster
     * 		Renvoi à cette méthode pour son utilisation
     * @param rot
     * 		Liste d'entier dont les éléments vont effectuer une rotation
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
     * Créer un tableau d'entier de proposition pour un des cas de la méthode propoOrdiMaster
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
