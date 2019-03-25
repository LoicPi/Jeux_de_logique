# Jeux de logique  
  
## Presentation :   
Jeux de Logique est un jeu o� vous avez la possibilit� de jouer � deux jeux :   
1. **Recherche +/-** : Recherche d'une combinaison, en un nombre de tour pr�d�fini, via des indications +, -, =	  
2. **Mastermind** : Recherche d'une combinaison, en un nombre de tour pr�d�fini et avec un nombre de couleurs pr�d�fini, via des indications de placements 
	 
Pour chacun des jeux trois modes sont propos�s :   
1. **Mode Challenger** : Il faut retrouver la combinaison de l'ordinateur  
2. **Mode D�fenseur** : L'ordinateur doit retrouver la combinaison g�n�r�e par l'utilisateur   
3. **Mode Duel** : L'utilisateur et l'ordinateur en tour par tour tentent de retrouver la combinaison de l'autre   

## Configuration :  	
La configuration du jeu s'effectue via le fichier `resources/config.properties`
Voici les diff�rents propri�t�s modifiables :  
1. La taille de la combinaison via `jeu.size`  
2. Le nombre de tour via `jeu.nbreTour`  
3. Le nombre de couleurs via `jeu.nbreCouleurs`  
4. Le mode d�veloppeur via `jeu.modeDeveloppeur`  
	- La valeur `True` permet de le lancer en mode d�veloppeur  
	- La valeur `False` permet de ne pas le lancer en mode d�veloppeur  

Vous pouvez �galement lanc� le jeu en mode d�veloppeur en inscrivant la ligne de commande suivante :  
**_java -jar Jeux_de_Logique.jar dev_**   

## Lancement :
Le lancement de l'application s'effectue via le fichier `Jeux_de_Logique.jar`.  
Il faut aller dans la console et se mettre dans le dossier contentant le fichier pr�c�demment nomm�.  
Pour lancer le jeu, il vous suffit d'�crire la ligne de commande suivante :  
**_java -jar Jeux_de_Logique.jar_**  
Il ne vous reste plus qu'� profiter du jeu !!! 

