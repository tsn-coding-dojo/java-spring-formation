Ce projet contient les slides de la formation java.
Il utilise le framework [Marp](https://marp.app/) pour écrire des slides-as-code.

# Usage avec NPM

//TODO add package JSON for local dev without docker

# Usage avec docker

## Convert slide deck into HTML

```powershell
docker run --rm -v ${PWD}:/home/marp/app/ -e LANG=$LANG marpteam/marp-cli formation-java.md --allow-local-files
```

## Watch mode

```powershell
docker run --rm --init -v ${PWD}:/home/marp/app/ -e LANG=$LANG -p 37717:37717 marpteam/marp-cli -w formation-java.md --allow-local-files
```

## Server mode (Serve current directory in http://localhost:8080/)

```powershell
docker run --rm --init -v ${PWD}:/home/marp/app -e LANG=$LANG -p 8080:8080 -p 37717:37717 marpteam/marp-cli -s .
```

