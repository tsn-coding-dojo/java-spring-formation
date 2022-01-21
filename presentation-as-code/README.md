Ce projet contient les slides de la formation java.
Il utilise le framework [Marp](https://marp.app/) pour écrire des slides-as-code.

# Usage avec NPM

## Pré-requis

```powershell
npm install
```

## Usage Dev

Ci-dessous les différentes commandes pour travailler avec ce projet

```powershell
# Lance le projet en mode DEV - un navigateur avec livereload s'ouvre automagiquement
# Un export PPT et PDF est dispo depuis le navigateur
npm run start-dev

# Pour générer les slides en divers format (les sorties seront dans le répértoire /dist
npm run deck-html
npm run deck-pptx
npm run deck-pdf
```

# Usage avec docker

## Convert slide deck

```powershell
# HTML
docker run --rm -v ${PWD}:/home/marp/app/ -e LANG=$LANG marpteam/marp-cli formation-java.md --allow-local-files --html
# Powerpoint
docker run --rm -v ${PWD}:/home/marp/app/ -e LANG=$LANG marpteam/marp-cli formation-java.md --allow-local-files --pptx --html
```

## Watch mode

```powershell
docker run --rm --init -v ${PWD}:/home/marp/app/ -e LANG=$LANG -p 37717:37717 marpteam/marp-cli -w formation-java.md --allow-local-files --html
```

## Server mode (Serve current directory in http://localhost:8080/)

```powershell
docker run --rm --init -v ${PWD}:/home/marp/app -e LANG=$LANG -p 8080:8080 -p 37717:37717 marpteam/marp-cli -s . --html
```

