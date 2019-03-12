package com.Projet3;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

	/**
	 * Création d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException {
		/**
		 * Création d'un objet prop afin d'appeler les caractéristiques de propriété du jeu
		 */
		ProprieteJeu prop = new ProprieteJeu();
		
		/**
		 * Création d'un objet jeu afin de lancer le jeu 
		 */
		Jeu jeu = new Jeu();
		
		// Lancement du jeu via la méthode presentationJeu de la classe Jeu
		jeu.presentationJeu(prop.valeurPropriete("jeu.modeDeveloppeur"));
		logger.info("Le jeu est lancé en mode debug : " + prop.valeurPropriete("jeu.modeDeveloppeur"));
	}

}
