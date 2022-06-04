# Projet bibliothèque
## Projet de micro services simulant une bibliothèque.

### Liste des services
- Livre
- Utilisateurs
- Commentaires
- Authentification
- Eureka Server
- API Gateway



### Livre
Un livre a une catégorie (enfant (0-13 ans), ado (13-18 ans), adulte).

### Utilisateur 
Un utilisateur peut emprunter des livres.

Les utilisateurs sont categorisés par leur age Enfant < 13, 13 < Ado < 18, 18 < Adulte.

Un utilisateur ne peut emprunter que 3 livres maximum.

Un enfant ne peut pas emprunter un livre "ado" ou "adulte".

Un ado ne peut pas emprunter un livre "adulte".

### Authentification
On peut créer un compte sans être authentifié.

Mais le reste des actions doivent être réservées aux utilisateurs authentifiés.

### Commentaire 
Un utilisaeur peut laisser un commentaire une fois qu'il a rendu le livre, 
Il est associé au livre et à l'utilisateur 
