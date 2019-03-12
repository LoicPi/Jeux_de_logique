package com.Projet3;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

	/**
	 * Cr�ation d'un objet logger pour retranscrire les infos dans le fichier de log
	 */
	static Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException {
		/**
		 * Cr�ation d'un objet prop afin d'appeler les caract�ristiques de propri�t� du jeu
		 */
		ProprieteJeu prop = new ProprieteJeu();
		
		/**
		 * Cr�ation d'un objet jeu afin de lancer le jeu 
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
			logger.info("Le jeu est lanc� en mode d�veloppeur.");
		} else {
			logger.info("Le jeu n'est pas lanc� en mode d�veloppeur.");
		}
	}
}
