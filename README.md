# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu o� vous avez la possibilit� de jouer � deux jeux :   
1. **_Recherche +/-_** : Recherche d'une combinaison, en un nombre de tour pr�d�fini, via des indications +, -, =	  
2. **_Mastermind_** : Recherche d'une combinaison, en un nombre de tour pr�d�fini et avec un nombre de couleurs pr�d�fini, via des indications de placements 
	 
Pour chacun des jeux trois modes sont propos�s :   
1. **_Mode Challenger_** : Il faut retrouver la combinaison de l'ordinateur  
	- **_Mode D�fenseur_** : L'ordinateur doit retrouver la combinaison g�n�r�e par l'utilisateur   
	- **_Mode Duel_** : L'utilisateur et l'ordinateur en tour par tour pour retrouver la combinaison de l'autre   

## Configuration :  	
La configuration du jeu s'effectue via le fichie `resources/config.properties`
Voici les diff�rents propri�t�s modifiables :  
	- La taille de la combinaison via `jeu.size`  
	- Le nombre de tour via `jeu.nbreTour`  
	- Le nombre de couleurs via `jeu.nbreCouleurs`  
	- Le mode d�veloppeur via `jeu.modeDeveloppeur`  
	    - La valeur `True` permet de le lancer en mode d�veloppeur  
	    - La valeur `False` permettra de ne pas le lancer en mode d�veloppeur   
