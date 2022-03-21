# Flotte de Vélos
Ce projet consiste à réaliser une base de données pour stocker et gérer les données nécessaires au suivi d’une flotte de vélos électriques sur une métropole.


## Structure du dépôt
Projet-SGBD  
    | -  sql             : répértoire contenant nos fichiers sql.  
    | -  Application     : répértoire contenant nos fichiers .java pour établir la connexion à l'aide de JDBC et  pour l'interface.  
    | -  rapport         : répértoire qui contient le rapport.  
    | - Makefile  

## Membres 
- Youness Dkhissi
- Mohammed Jamil
- Youssef Toumi

## Environnement
Le projet a été exécuté et testé sur des machines de l'ENSEIRB-MATMECA.
- il faut avoir Oracle Database 11g.
- changer le username et password dans Main.java présent dans src.
- créer la base et les données à l'aide de ces commandes: 
sur la machine virtuelle d'Oracle
```shell
	cd sql && sqlplus
```
```
@suppression
@creation
@peuplement
@mise-a-jour
```

## Exécution
Sur la racine du dépôt, exécuter la commande suivante:
```shell
make && make run
```
et suivre les instructions. les fichiers html seront générés  dans un répertoire html à la racine du dépôt.  Ces fichiers peuvent etre lus par n'importe quel navigateur.  
Pour nettoyer le dépôt:
```shell
make clean
```


