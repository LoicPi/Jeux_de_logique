package com.Projet3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProprieteJeu {

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
