# Présentation
Créée en 2006, Equida est une société spécialisée dans la vente aux enchères de chevaux de course. Pour être plus proche de sa clientèle étrangère, elle s’appuie sur une quinzaine de correspondants répartis dans de nombreux pays comme l’Irlande, la Turquie, ou encore le Japon.

Il s'agit de créer une application unique permettant de gérer :

- les informations sur les chevaux (caractéristiques, courses et jockeys, entraîneurs, etc.)
- le catalogue des ventes et l'envoi de courriels aux différents clients intéressés par les ventes.
- 
L'application devra également prendre en compte :
- la consultation du catalogue des ventes ;
- la consultation des résultats d’une vente ;
- la consultation en ligne de statistiques sur les ventes passées.

# Structuration du projet

Le projet comporte 3 dossiers :
- doc → Regroupe les différents documents importants du projet
- sql → Contient l'historique de tous les scripts (SQL ou PHP) qui ont modifié la BDD. "bdd.sql" contient le script SQL de la BDD final.
- src → Contient le code source de l'application

# Modifications souhaitées

## Modification niveau 0 : ajout des classes métier (partie modele du modèle MVC)
Ajouter les classe Lieu, Courriel et PièceJointe ainsi que les relations nécessaires entre ces classes.
Tester les classes.

##  Modification niveau 0 (bis) : ajout des données
Etablir la correspondance en base de données des classes ajoutées ci-dessus : structure + data.
Dans un premier temps, vous réaliserez le schéma relationnel ou conceptuel.
Vous ajouterez ensuite des données.
Le script de création des tables et d’insertion des données sera sauvegardé.

##  Modification niveau 1 : 
Fonctionnellement, les informations concernant l’adresse postale des clients sont manquantes. On souhaite les faire apparaître dans la vue listant les clients.

##  Modification niveau  2: 
Fonctionnellement, l’information concernant l’adresse mail des clients est manquante. On souhaite la faire apparaître dans la vue listant les clients.

##  Modification niveau  3 :
On souhaite connaître la liste des ventes associées à une catégorie de vente : nom, date de début de la vente, ville.

## Modification niveau  4 :
Pour chaque vente, on souhaite afficher la liste des courriels envoyés aux acheteurs avec les pièces jointes.

## Modification niveau 5 : 
- Vous devez mettre en place l’héritage relatif aux informations concernant les intervenants (acheteurs et vendeurs).
- Dans un premier temps, lister toutes les actions à entreprendre pour implémenter ces modifications en séparant bien les différentes couches applicatives.