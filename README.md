# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu où vous avez la possibilité de jouer à deux jeux :   
1. **Recherche +/-** : Recherche d'une combinaison, en un nombre de tour prédéfini, via des indications +, -, =	  
2. **Mastermind** : Recherche d'une combinaison, en un nombre de tour prédéfini et avec un nombre de couleurs prédéfini, via des indications de placements 
	 
Pour chacun des jeux trois modes sont proposés :   
1. **Mode Challenger** : Il faut retrouver la combinaison de l'ordinateur  
2. **Mode Défenseur** : L'ordinateur doit retrouver la combinaison générée par l'utilisateur   
3. **Mode Duel** : L'utilisateur et l'ordinateur en tour par tour tentent de retrouver la combinaison de l'autre   

## Configuration :  	
La configuration du jeu s'effectue via le fichier `resources/config.properties`
Voici les différents propriétés modifiables :  
1. La taille de la combinaison via `jeu.size`  
2. Le nombre de tour via `jeu.nbreTour`  
3. Le nombre de couleurs via `jeu.nbreCouleurs`  
4. Le mode développeur via `jeu.modeDeveloppeur`  
	- La valeur `True` permet de le lancer en mode développeur  
	- La valeur `False` permet de ne pas le lancer en mode développeur  

Vous pouvez également lancé le jeu en mode développeur en inscrivant la ligne de commande suivante :  
**_java -jar Jeux_de_Logique.jar dev_**   

## Lancement :
Le lancement de l'application s'effectue via le fichier `Jeux_de_Logique.jar`.  
Il faut aller dans la console et se mettre dans le dossier contentant le fichier précédemment nommé.  
Pour lancer le jeu, il vous suffit d'écrire la ligne de commande suivante :  
**_java -jar Jeux_de_Logique.jar_**  
Il ne vous reste plus qu'à profiter du jeu !!! 

