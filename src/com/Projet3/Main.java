package com.Projet3;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ProprieteJeu prop = new ProprieteJeu();
		Jeu jeu = new Jeu();
		jeu.presentationJeu(prop.valeurPropriete("jeu.modeDeveloppeur"));
	}

}
