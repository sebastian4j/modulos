# Servicios con JPMS

Ejemplo de uso de servicios con modulos, incluye compatibilidad con servicios anteriores pre modulos.

compilar con:

mvn clean package

lanzar con:

java --module-path mods --module services.consumidor/com.sebastian.servicios.Consumidor

Cargar servicios antiguos (pre modulos):

java --class-path mods/servicios.productor.c.jar --show-module-resolution --module-path mods --module servicios.consumidor/com.sebastian.servicios.Consumidor