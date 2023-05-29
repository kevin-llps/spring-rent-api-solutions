# Spring Rent Api Solutions

Pour exploiter la base de données : 
- `docker compose up`
- Vous pouvez vous connecter à la base de données depuis l'onglet "Database" d'IntelliJ Ultimate
- Créer le schéma de la base avec Liquibase : `mvn liquibase:update`
- Vous pouvez exécuter les requêtes "INSERT INTO" (répertoire /sql) depuis IntelliJ Ultimate

Pour la validation des requêtes (exercice 6) : 
- Plusieurs annotations sont mises à disposition dans `jakarta.validation.constraints`.
- Ici on utilisera uniquement `@NotNull` pour vérifier si les champs obligatoires sont bien présents dans le "request body".
- Puis, l'annotation `@Valid` en argument de l'endpoint et appliquée à `RentalPropertyRequestDto` permettra de vérifier les contraintes de validation (`@NotNull` dans notre cas)
- En cas de contraintes de validation non respectées, une erreur 400 sera renvoyée
- Mais nous pouvons personnaliser le message d'erreur (puisqu'une exception `MethodArgumentNotValidException` est jetée en cas de non respect des contraintes)
- Voir `ErrorHandler.java`.

Vous trouverez la correction de chaque exercice
dans une branche dédiée du repository :
- [Exercice 1](https://github.com/kevin-llps/spring-rent-api-solutions/tree/solution-exercice-1)
- [Exercice 2](https://github.com/kevin-llps/spring-rent-api-solutions/tree/solution-exercice-2)
- [Exercice 3-4](https://github.com/kevin-llps/spring-rent-api-solutions/tree/solution-exercice-3-4)
- [Exercice 5](https://github.com/kevin-llps/spring-rent-api-solutions/tree/solution-exercice-5)
- [Exercice 6](https://github.com/kevin-llps/spring-rent-api-solutions/tree/solution-exercice-6)