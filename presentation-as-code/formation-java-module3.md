---
theme: default
_class: lead paginate: true backgroundImage: url('./assets/images/hero-background.svg')
---

![bg left:40% 80%](./assets/images/thales-logo.svg)

# **Formation Java Backend**

Les fondamentaux ( et un peu plus 🚀)

## Module 3

---

# Sécurité

▌ Principe de base : n’ayez jamais confiance dans celui qui vous appelle !

▌ Quelques règles de bonne pratique*

- Par défaut, interdisez tout
- Sécurisez les point d’entrée
- Validez les données en entrée
- Authentifiez l’utilisateur
- Vérifier qu’il a le droit d’accéder / modifier la donnée

---

# Sécurité

Dans le cas d’un site internet :

- Limitez les appels CORS au minimum (appels cross domaine)
  – Quel site internet est autorisé à m’appeler ?
- Activez le CSFR si possible (besoin d’une session)
    - Jeton échangé lors des appels POST / PUT

---

# Securité - quelques notions

![bg left:50% 60%](./assets/images/auth-flow.png)

- Principaux protocols de nos jours:
  - SAML
  - OAuth 2.0

- Problématiques récurrentes :
  - Exposition du token
  - Durée de vie
  - Expiration
  - SSO

---
# Securité - Spring Security

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

Comportement de base
- Page de login `/login`, url de logout `/logout`
- Tout est sécurisé, sauf ce qui se trouve dans public

---
# Securité - Spring Security - Authentication

![auto](./assets/images/securitycontextholder.png)

<!-- 
SecurityContextHolder - The SecurityContextHolder is where Spring Security stores the details of who is authenticated.
SecurityContext - is obtained from the SecurityContextHolder and contains the Authentication of the currently authenticated user.
Authentication - Can be the input to AuthenticationManager to provide the credentials a user has provided to authenticate or the current user from the SecurityContext.
GrantedAuthority - An authority that is granted to the principal on the Authentication (i.e. roles, scopes, etc.)

AuthenticationManager - the API that defines how Spring Security’s Filters perform authentication.
ProviderManager - the most common implementation of AuthenticationManager.
AuthenticationProvider - used by ProviderManager to perform a specific type of authentication.
Request Credentials with AuthenticationEntryPoint - used for requesting credentials from a client (i.e. redirecting to a log in page, sending a WWW-Authenticate response, etc.)
-->

L'authentification repose sur:
- `Authentication Manager` - l'API
- `ProviderManager` - l'implémentation la plus commune
- Un ensemble de `AuthenticationProvider` - les providers d'authentification

---
# Securité - Spring Security - Authorization

- Tout dépend de la notion de `GrantedAuthority`, interface qui possède une simple méthode 
```java
String getAuthority();
```
- Souvent laissé à la main de l'utilisateur
- Historiquement Spring gère une notion de "rôle" et "privilèges" (avec le rôle préfixé par `ROLE_`)
  - Mais en vrai, cela est souvent mappé vers une simple string et la notion interne de `GrantedAuthority`

---
# Securité - Spring Security 

![auto](./assets/images/authorizationhierarchy.png)

---
# Securité - Spring Security
La sécurisation peut se passe à plusieurs niveaux:
- Via la configuration des endpoints
- Via des annotations
  - Dans la configuration:  `@EnableGlobalMethodSecurity` -> `@EnableMethodSecurity `
  - Dans vos controlleurs:  `@PreAuthorize(XXX)`
    - `permitAll` : public
    - `isAuthenticated()` : l’utilisateur est authentifié
    - `hasRole(‘…’)` : l’utilisateur dispose du rôle demandé
    - `hasAnyAuthority(‘…’)` : l’utilisateur dispose d’au moins un des droits demandé

---
# TP 11 - Spring Security

<!-- _class: invert -->
<!-- _backgroundImage: none -->

1. Ajoutez la dépendance
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
1. Récupérer les éléments suivants (TP11)
   - `SecurityConfiguration.java`


---
<!-- _class: invert -->
<!-- _backgroundImage: none -->

- Mettre à jour le TodoService pour associer le user au todo à la création
- Essayer de s’authentifier dans la GUI
- Sécuriser les WebServices Todo (annotation `@PreAuthorize`)
  - findAll -> Public (permitAll)
  - create -> privilège « add » ou role Admin (`hasAuthority('add') || hasRole('ROLE_ADMIN')`)
  - update, complete, delete -> Authentifié (`isAuthenticated()`)
  - deleteAll -> Admin (`hasRole(‘ROLE_ADMIN’)`)
- Vérifier que ce niveau de sécurité fonctionne (notamment le deleteAll)
- Mettre en place un contrôle du droit de modification de la donnée (todoCustomRepositoryImpl)
  - Le propriétaire peut modifier ses Todos
  - Un admin peut modifier n’importe lequel

---
# TP 11 - Spring Security

<!-- _class: invert -->
<!-- _backgroundImage: none -->
▌ **Redescendre le User dans la Gui sous forme d’une String dans TodoDto**

Ajouter l’attribut « String user » dans TodoDto

Ne pas mapper le user du Dto vers le Modèle

Mapper le user du modèle vers le Dto (sous-attribut)