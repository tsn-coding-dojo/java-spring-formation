# Formation Java Spring

## Prérequis

### Installation des prérequis

* Système
	* Installation JDK 17 (LTS) depuis https://adoptium.net/
	* Installation Apache Maven 3.9.3 (ou latest) depuis https://maven.apache.org/download.cgi

* IDE
	* Installation IntelliJ Idea Community depuis https://www.jetbrains.com/fr-fr/idea/download/?section=windows
	
* Autres
	* Installation Node.js 18.17.0 LTS depuis https://nodejs.org/fr
	
### Configuration des prérequis

* Variables d'environnement
	* JAVA_HOME doit pointer vers le répertoire contenant la JDK
	* M2_HOME doit pointer vers le répertoire contenant le binaire `mvn` de Maven
	* PATH doit contenir les chemins vers les binaires `java` et `mvn`

* Vérification de l'installation: dans une invite de commande
	* `java -version` doit donner la version de la JDK installée
	* `mvn  -v` doit donner la version de Maven installé, ainsi que la version de la JDK configurée

## Déroulement de la Formation

### Présentation

La présentation est disponible sous forme de code et versionnée dans ce repo, se référer au [README](presentation-as-code/README.md) associé.

### TPs

Au fur et à mesure de la formation, des TPs seront proposés, chaque correction de TP est accessible via un projet Maven associé.

Il est recommandé de créer son propre projet via [Spring Initializr](https://start.spring.io/) avec les paramètres suivants :

| Parameter    | Value                |
|--------------|----------------------|
| Project      | Maven                |
| Language     | Java                 |
| Spring Boot  | 3.1.2                |
| Group        | com.thales.formation |
| Artifact     | todo-project         |
| Name         | todo-project         |
| Package name | com.thales.formation |
| Packaging    | jar                  |
| Java         | 17                   |
| Dependencies | Spring Web           |

et de l'importer en tant que projet Maven dans le repo (voire TP1).

La correction de chaque TP est disponible dans son répertoire associé.

Chaque correction TP peut être lancé via la commande `mvn spring-boot:run` exécuté dans son répertoire.

Le numéro de port est incrémenté pour chaque correctif de TP: 

| TP                       | port |
|--------------------------|------|
| votre version de travail | 8080 |
| TP1                      | 8081 |
| TP2                      | 8082 |
| ...                      | ...  |

## Pour les formateurs

Si vous souhaitez mettre à jour le frontend dans tous les TPs, exécuter le script `update-frontend.bat`

## Changelog

### 07/2023

- Mise à jour des prérequis et dépendances pour tous les TPs (Java 17, Spring Boot 3.1.2)
- Abandon du runtime PostgreSQL au profit du runtime H2 (pas de plus-value de faire installer PostgreSQL aux stagiaires en 2 jours par rapport à H2)
- Réécriture de l'app frontend en Angular 16 + mise à dispo d'un script d'update du frontend dans tous les TPs
- Diverses corrections de format, de phrasé, etc. dans les slides existantes
- Correction de l'ensemble des TPs afin qu'ils soient fonctionnels (déplacement des tests dans les bons répertoires, modification des imports, ...)
- Ajout d'une slide sur les types d'architectures alternatifs à l'architecture en couche (clean, hexagonal)
- Ajout d'un TP4 bis alternatif à Lombok pour démontrer l'utilisation des `record` en Java
- Ajout d'un TP5 bis alternatif pour démontrer l'utilisation des `record` avec Mapstruct
- Ajout d'un TP Test-Driven Development (Fizzbuzz) optionnel entre les TP 8 et 9
- Ajout d'un exemple de mutation testing dans le TP8
- Réécriture du TP 11 pour le rendre compatible avec la version de Spring Security embarquée dans Spring Boot 3.1.2 et le frontend Angular 16

TODO: 
- réactiver la protection XSRF et BREACH dans le TP11 (et +), voir les liens dans le bean `SecurityConfiguration`