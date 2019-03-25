# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu o� vous avez la possibilit� de jouer � deux jeux :   
	- Recherche +/- : Recherche d'une combinaison, en un nombre de tour pr�d�fini, via des indications +, -, =  
	- Mastermind : Recherche d'une combinaison, en un nombre de tour pr�d�fini et avec un nombre de couleurs pr�d�fini, via des indications de placements  
Pour chacun des jeux trois modes sont propos�s :  
	- Mode Challenger : Il faut retrouver la combinaison de l'ordinateur
	- Mode D�fenseur : L'ordinateur doit retrouver la combinaison g�n�r�e par l'utilisateur
	- Mode Duel : L'utilisateur et l'ordinateur en tour par tour pour retrouver la combinaison de l'autre  

## Configuration :  	
La configuration du jeu s'effectue via le fichier 'resources/config.properties'
Voici les diff�rents propri�t�s modifiables :
	- La taille de la combinaison via '_jeu.size_'  
	- Le nombre de tour via 'jeu.nbreTour'
	- Le nombre de couleurs via '_jeu.nbreCouleurs_'
	- Le mode d�veloppeur via '_jeu.modeDeveloppeur_'  
		- La valeur _True_ permet de le lancer en mode d�veloppeur  
		- La valeur _False_ permettra de ne pas le lancer en mode d�veloppeur   
