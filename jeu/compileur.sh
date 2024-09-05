#!/bin/bash


rm -r class

# Compilation Java
javac -encoding utf8 Controleur.java metier/*.java ihm/*.java -d class > >(tee compileErrors.log) 2> >(tee compileErrors.log >&2)

# Vérification du statut de la compilation
if [ $? -ne 0 ]; then
    echo "Erreurs de compilation. Veuillez consulter le fichier compileErrors.log pour plus de détails."
    exit 1
fi

# Copie des fichiers source dans le répertoire de sortie
cp -r "src" "class/jeu/"

cd class 

java jeu.Controleur  

# sed -i -e 's/\r$//'
