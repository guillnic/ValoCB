# ValoCB

## Environnement de développement

- Maven 3.6.3
- Java 17.0.3
- au.com.bytecode.opencsv : 2.4

To install opencsv
>```
>mvn install:install-file \
>-Dfile=opencsv-2.4.jar \
>-DgroupId=au.com.bytecode \
>-DartifactId=opencsv \
>-Dversion=2.4 \
>-Dpackaging=jar
>```

## Lancement de l'application

- IDE (Intellij pour ma part) pour lancer l'application
```
mvn clean package
java -jar target/ValoCB-1.0.0.jar src/main/resources/Forex.csv src/main/resources/Prices.csv src/main/resources/Product.csv
```

Les résultats apparaitront dans le dossier outputs.


