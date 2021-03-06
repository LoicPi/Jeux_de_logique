package com.Projet3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ProprieteJeu est une classe faisant appel au fichier config.properties afin d'appeler les param�tres configurer dans ce fichier
 * 
 * @author Lo�c
 * @version 1.0
 */

public class ProprieteJeu {
	
	/**
	 * M�thode pour retourner un parma�tre du fichier config.properties
	 * @param 
	 * 		Appel du parametre configurer dans le fichier config.properties
	 * @return
	 * 		Retourne la valeur du param�tre en une cha�ne de caract�re
	 */

	public String valeurPropriete (String parametre) {
		InputStream inputStream = null;
		String param = "";
		
		try {
			Properties prop = new Properties();
			String propNom = "config.properties";
			
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propNom);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Le fichier Propri�t� " + propNom + " n'a pas �t� trouv�.");
			}
			
			 param = prop.getProperty(parametre);
		} catch (Exception e){
			System.out.println("Exception : " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Exception : " + e);
			}
		}
		
		return param;
		
	}
	
}
