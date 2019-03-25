# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu où vous avez la possibilité de jouer à deux jeux :   
	- Recherche +/- : Recherche d'une combinaison, en un nombre de tour prédéfini, via des indications +, -, =  
	- Mastermind : Recherche d'une combinaison, en un nombre de tour prédéfini et avec un nombre de couleurs prédéfini, via des indications de placements  
Pour chacun des jeux trois modes sont proposés :  
	- Mode Challenger : Il faut retrouver la combinaison de l'ordinateur
	- Mode Défenseur : L'ordinateur doit retrouver la combinaison générée par l'utilisateur
	- Mode Duel : L'utilisateur et l'ordinateur en tour par tour pour retrouver la combinaison de l'autre  

## Configuration :  	
La configuration du jeu s'effectue via le fichier 'resources/config.properties'
Voici les différents propriétés modifiables :
	- La taille de la combinaison via '_jeu.size_'  
	- Le nombre de tour via 'jeu.nbreTour'
	- Le nombre de couleurs via '_jeu.nbreCouleurs_'
	- Le mode développeur via '_jeu.modeDeveloppeur_'  
		- La valeur _True_ permet de le lancer en mode développeur  
		- La valeur _False_ permettra de ne pas le lancer en mode développeur   
