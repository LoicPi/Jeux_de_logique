package com.Projet3;

import java.io.IOException;
import org.apache.log4j.Logger;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		logger.debug("Test 1");
		ProprieteJeu prop = new ProprieteJeu();
		Jeu jeu = new Jeu();
		jeu.presentationJeu(prop.valeurPropriete("jeu.modeDeveloppeur"));
	}

}
