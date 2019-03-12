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
		
		boolean modeDev = false;
		
		if (args.length != 0 && args[0].equals("dev")) {
				modeDev = true;
		}
		if (prop.valeurPropriete("jeu.modeDeveloppeur").equals("true")) {
				modeDev = true;
		} 
		
		jeu.presentationJeu(modeDev);
		
		if (modeDev){
			logger.info("Le jeu est lancé en mode développeur.");
		} else {
			logger.info("Le jeu n'est pas lancé en mode développeur.");
		}
	}
}
