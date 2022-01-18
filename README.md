# pre requis
UN PC AVEC FULL ACCES INTERNET :)

Installation JDK >= 8 + PATH dans les variables d'environement

https://jdk.java.net/

Installation d'un postgresql

https://www.enterprisedb.com/downloads/postgres-postgresql-downloads >= 11

password admin

Installation de DBeaver IDE et paramétrage de connexion
 
https://dbeaver.io/

localhost:5432  postgres:admin  et CREATE DATABASE todos;

Installation liquibase ou utilisation du TP 10 ( maven plugin liquibase integré )

https://www.liquibase.org/download + driver postgresql dans liquibase/lib à ajouter  https://jdbc.postgresql.org/download.html

Installation postman pour manipulation REST avec un IDE :)

https://www.postman.com/downloads/

Installation d'un eclipse STS 4

https://spring.io/tools

Installation de lombok

https://projectlombok.org/download

Installation d'un maven 

https://maven.apache.org/download.cgi

Ajouter le PATH dans les variables d'environement  maven/bin


# Source angular
L'objectif des TP est sur Java, l'angular à été "build" et mis à disposition dans chaque TP dans /src/main/resources/public

les sources sont disponible dans front-todos 

# Application
!! Ne pas oublié de lancer email-project dans certains TP

Faire un run de l'application

TP 1 -> 16

http://localhost:8080

TP 17 -> 18 
Générer les fichiers swagger il faut faire un maven install 

http://localhost:8080/index.html  => modification comportement GUI

# Liquibase
Chaque TP à partir du TP 7.1 est compliant Postgresql >= 11 ou H2 ( désactivation à faire des properties postgresql dans le fichier /src/main/resources/application.properties ) 

Utilisation du DSL XML => possibilité de faire des rollback par tag / date / count ...

Command maven pour un rollback UNIQUEMENT SUR LE TP 10 => exemple  mvn liquibase:rollback -Dliquibase.rollbackTag=tp-7.1

sinon via install liquibase all TP depuis le TP-7.1 => exemple   liquibase --defaultsFile=db/changelog/liquibase.properties rollback tp-7.1

se deplacer dans le repertoire src/main/resources/ avant


# Utilisation de swagger

TP 17 -> 18

http://localhost:8080/swagger-ui.html


# CHANGE LOG 

## V2 06/05/2021 CMA (T0206457) :

Global review:

1.  présentation powerpoint:
* ajout Bonus Design Pattern 
* Divers corrections ( @GetMapping , Notion Zip / jar / ear  / war,  Notion criteria JPA, Update Swagger code-gen et non plus ennonciate ...)

2. Projet email-project:
*  Ajout logback pour trace des appels

3. Front-todos
* extraction du front dans un projet à part

4. Global TP
* suppression du webapp pour injection "buildé" du front dans /public
* Modification des pom pour uniformisation spring 2.4.4 , liquibase , postgresql ...
* Liquibase compliant Postgresql ou H2 in memory
* UserService.java Check if user présent => évite un SQL not unique result
* User MODELE => Ajout @Table(name="users") => user est un mot clef pour POSTGRESQL => error
* Ajout readme + prerequis liquibase Postgresql
* Ajout logback extraction  vers c:/logs-formation
** 2 fichiers app.log et error.log 


5. TP 7.1 -> 18:
* ajout du plugin liquibase pour présentation du rollback =>  liquibase --defaultsFile=db/changelog/liquibase.properties rollback tp-7.1
* Ajout changelog TP-7.1 -> TP-13 plus besoin ensuite car le mcd ne bouge pas

6. TP 17 et 18
* Ajout swagger code gen à la place de ennonciate
* Génération automatique à partir d'un fichier swagger.json
* configuration springfox pour interface http://localhost:8080/swagger-ui.html
* scan package des controller pour documentation automatique
* StaticResourceConfiguration.java  et SwaggerConfig.java  
* Ajout d'un fichier todos-v1.api dans le TP 17 en génération ( copie du PET example de swagger-editor )

7. Collection postman 
* Ajout d'une collection postman de test à la racine 

## V3 todo 