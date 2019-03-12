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
		// TODO Auto-generated method stub
		
		logger.info("Test 1");
		ProprieteJeu prop = new ProprieteJeu();
		Jeu jeu = new Jeu();
		jeu.presentationJeu(prop.valeurPropriete("jeu.modeDeveloppeur"));
	}

}
