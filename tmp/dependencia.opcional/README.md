dependencia.opcional

## no lo carga:

java --module-path target/classes/ --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main

java --module-path target/classes/:/home/sebastian/java/workspace/maven/modulos/com.sebastian.modulos.primero/target/com.sebastian.modulos.primero-1.2-SNAPSHOT.jar  --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main

## si lo carga:

java --module-path target/classes/:/home/sebastian/java/workspace/maven/modulos/com.sebastian.modulos.primero/target/com.sebastian.modulos.primero-1.2-SNAPSHOT.jar --add-modules com.sebastian.modulos.primero --module com.sebastian.dependencia.opcional/com.sebastian.dependencia.opcional.Main
