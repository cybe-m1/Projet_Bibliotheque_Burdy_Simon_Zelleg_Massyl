# Library Project

# *Table of contents*
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

## *Description*
* Membres du Binôme
    - Simon BURDY
    - Massyl ZELLEG<br>

Micro services project simulating an online library.
- *Technologies used*
    * *Back:* Java Spring Boot
    * *Front:* React/NextJS
    * [*BACK REPO*](https://github.com/cybe-m1/Projet_Bibliotheque_Burdy_Simon_Zelleg_Massyl/tree/main)
    * [*FRONT REPO*](https://github.com/SimonBurdy/ProjetBiblioReact/tree/main)

## *List of Services*
### *User Service*
- A user can borrow books.
- Users are categorized by age Children < 13, 13 < Teen < 18, 18 < Adult.
- A user can only borrow a maximum of 3 books.
- A child cannot borrow a "teen" or "adult" book.
- A teenager cannot borrow an "adult" book.
### *Authentication*
- Authentication config inside of user service
- Authentication (Register/login) : Spring Security
- You can create an account without being authenticated.
- Once registered, the user receives an access token that he uses to perform all other actions
  the user also receives a refresh token in order to update it
- All other actions should be reserved for authenticated users.
### *Book Service*
- A book has a category (children (0-13 years), teen (13-18 years), adult).
- A book is associated with the user when it is borrowed
### *Comment Service*
- A user can leave a comment after returning the book.
- The commentary also includes a score from 0 to 5
- It is associated with the book and the user
### *Eureka Sever*
Eureka server to see the status of the different microservices
### *Gateway Service*
A gateway has been set up on port 9191, so that all microservices can be reached on this same port
## *Deploy*
- Creation of images with *Docker*
- Setting up pipelines on *GithubAction*
- Deployment on a VM with public ip *35.195.216.63*
## *Uri For Testing*
A collection file *Biblio_Project.json* has been added to the directory, it contains the most important API calls of the different microservices









 

