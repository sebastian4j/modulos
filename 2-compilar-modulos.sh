rm -rf classes
javac --module-path mods:libs --module-source-path "./*/src/main/java" -d classes @file.list --module com.sebastian.modulos.segundo
