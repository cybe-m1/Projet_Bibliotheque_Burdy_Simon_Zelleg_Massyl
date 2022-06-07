# *Library Project*

# Table of contents
1. [Description](#description)
2. [List Of Services](#list-of-services)
    - [User Service](#user-service)
    - [Authentication](#authentication)
    - [Book Service](#book-service)
    - [Comment Service](#comment-service)
    - [Eureka Server](#eureka-server)
    - [Gateway Service](#gateway-service)
3. [Deploy](#deploy) 
4. [Uri For Testing](#uri-for-testing)


## Description 
Projet de micro services simulant une bibliothèque.


## List of Services
### User Service
Un utilisateur peut emprunter des livres.

Les utilisateurs sont categorisés par leur age Enfant < 13, 13 < Ado < 18, 18 < Adulte.

Un utilisateur ne peut emprunter que 3 livres maximum.

Un enfant ne peut pas emprunter un livre "ado" ou "adulte".

Un ado ne peut pas emprunter un livre "adulte".
### Authentication
On peut créer un compte sans être authentifié.

Mais le reste des actions doivent être réservées aux utilisateurs authentifiés.
### Book Service
Un livre a une catégorie (enfant (0-13 ans), ado (13-18 ans), adulte).
### Comment Service
Un utilisaeur peut laisser un commentaire une fois qu'il a rendu le livre,
Il est associé au livre et à l'utilisateur
### Eureka Sever
### Gateway Service

## Deploy

## Uri For Testing









 

