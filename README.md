# Delivuniv #

## Introduction ##

Ce projet a été réalisé dans le cadre de l'UE Fullstack de l'université de Rouen 2023/2024. Ce projet consiste à faire une application web permettant de gérer des livreurs. Les fonctionnalités sont d'ajouter, de modifier et chercher des livreurs sur l'application. 

## Stack technique ##

### Frontend ###

Pour le client, nous sommes partis sur le framework <strong>Angular</strong> qui est un framework javascript. Il permet de faire une application SPA (Single Page Application).
L'architecture mise en place est que dans le dossier <strong>components</strong> qui est constitué des principales composants de l'application. Et dans le dossier <strong>pages</strong>,
nous avons les différents composants qui constiture les différentes pages de l'application. Dans le dossier <strong>models</strong>, nous avons les principaux models de données
qui permet definir les types complexes que l'application va manipuler (types des résultats des requêtes, ...).
Et pour fournir dans le dossier <strong>services</strong>,
nous aurons différents permettant d'intéragir avec un domaine (par exemple : delivery-person)
et qui permettra d'implémenter le requêtage de divers API mais aussi d'appliquer de la logique métier. 

### Backend ###

Pour l'API, nous sommes partis sur le framework <strong>Spring boot</strong> qui est un framework Java et nous utilisons Maven pour la gestion des dépendances du projet.
Il permet pour ce projet de créer une API REST pour être utiliser par notre application cliente. 
Cet API permet la gestion des livreurs  avec les différents suivantes : ajout, modification, suprresion et recherche de livreurs avec filtres et pagination. 
Pour l'architecture, nous sommes partie sur une architecture basique, dont le package <strong>controllers</strong> contiendra les différents controllers. Le dossier <strong>dto</strong>, contiendra les différents DTO que l'application renverra à travers les différents endpoints. 
Le dossier <strong>services</strong> contiendra tous les services de l'application. Et ensuite, nous avons le dossier <strong>repositories</strong> qui contiendra les différents repository du projet. Et pour terminer, dans le dossier <strong>entities</strong> nous retrouvons nos différents entités qui représente notre schéma de base de données. 

### Base de données ###

Pour la base de données, nous sommes partis sur PostgreSQL qui est un système de gestion de base de données relationnelles et objet. Nous l'avons choisis car pour nous, c'est le système le plus robuste pour la gestion des bases de données.

## Lancement du projet ##

Pour lancer, le projet il suffit de lancer la commande :

```
docker compose up -d
```
Et l'application sera disponible sur :

```http://localhost:4200```
