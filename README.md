# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu où vous avez la possibilité de jouer à deux jeux :   
1. **_Recherche +/-_** : Recherche d'une combinaison, en un nombre de tour prédéfini, via des indications +, -, =	  
2. **_Mastermind_** : Recherche d'une combinaison, en un nombre de tour prédéfini et avec un nombre de couleurs prédéfini, via des indications de placements 
	 
Pour chacun des jeux trois modes sont proposés :   
1. **_Mode Challenger_** : Il faut retrouver la combinaison de l'ordinateur  
	- **_Mode Défenseur_** : L'ordinateur doit retrouver la combinaison générée par l'utilisateur   
	- **_Mode Duel_** : L'utilisateur et l'ordinateur en tour par tour pour retrouver la combinaison de l'autre   

## Configuration :  	
La configuration du jeu s'effectue via le fichie `resources/config.properties`
Voici les différents propriétés modifiables :  
	- La taille de la combinaison via `jeu.size`  
	- Le nombre de tour via `jeu.nbreTour`  
	- Le nombre de couleurs via `jeu.nbreCouleurs`  
	- Le mode développeur via `jeu.modeDeveloppeur`  
	    - La valeur `True` permet de le lancer en mode développeur  
	    - La valeur `False` permettra de ne pas le lancer en mode développeur   
