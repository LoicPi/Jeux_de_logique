package com.Projet3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ProprieteJeu est une classe faisant appel au fichier config.properties afin d'appeler les paramètres configurer dans ce fichier
 * 
 * @author Loïc
 * @version 1.0
 */

public class ProprieteJeu {
	
	/**
	 * Méthode pour retourner un parmaètre du fichier config.properties
	 * @param 
	 * 		Appel du parametre configurer dans le fichier config.properties
	 * @return
	 * 		Retourne la valeur du paramètre en une chaîne de caractère
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
				throw new FileNotFoundException("Le fichier Propriété " + propNom + " n'a pas été trouvé.");
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
