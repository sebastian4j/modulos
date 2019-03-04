package com.sebastian.modulos.segundo;

import com.sebastian.modulos.comun.MuestraInfo;
import com.sebastian.modulos.comun.Persona;
import com.sebastian.modulos.segundo.exportado.Implementable;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * java --module-path target/libs:target/modulos-2.jar --module modulos.segundo/com.sebastian.modulos.segundo.Main
 *
 * @author Sebastian Avila A.
 */
public class Main extends MuestraInfo {

    public static void main(final String[] args) throws Exception {
        Main main = new Main();
        main.separacion("acceso público:");
        main.accesoPublico();
        main.separacion("error reflexion:");
        main.errorReflection();
        main.separacion("sin error reflexion:");
        main.sinErrorReflexion();
        main.separacion("leer modulo abierto");
        main.leerDesdeModuloAbierto();
        main.separacion("handler-no-abierto");
        main.variableHandlerNoAbierto();
        main.separacion("handler-abierto");
        main.variableHandlerAbierto();
        main.separacion("info-modulo");
        main.analizarModulo();
        main.separacion("info otro modulo");
        new MuestraInfo().analizarModulo();
        main.separacion("abrir-java-lang (modulo-1)");
        main.separacion("class loaders");
        main.mostrarClassLoaderes();
        main.separacion("mostrar-layer");
        main.layerInfo();
        main.separacion("---stack-capas");
        main.buscarEnElStackDeCapas();
        //////////////////////
        main.separacion("consulta por otro modulo");
        Persona persona = new Persona();
        System.out.println(persona.isModulePresent("modulos.primero"));
        main.separacion("agregar-configuracion");
        main.registerNewService(
                new Path[]{
                    Paths.get("/home/sebastian/java/workspace/maven/modulos/mods/tercero.jar")
                });
        main.separacion("acceso-protegido");
        main.instanciarProtegido();
    }

    private void accesoPublico() {
        final Persona p = new Persona();
        p.setId(1);
        p.setNombre("primero");
        System.out.println(p);

    }

    private void errorReflection() {
        try {
            final Class<?> c = Class.forName("com.sebastian.modulos.comun.privado.Auto");
            final Constructor<?>[] vs = c.getConstructors();
            for (Constructor ccc : vs) {
//                ccc.setAccessible(true); // runtime exception
                if (ccc.trySetAccessible()) {
                    System.out.println("<<<<<<<<<<<<<fue posible acceder a la clase");
                    ccc.setAccessible(true);
                    Object inst = ccc.newInstance();
                    System.out.println(inst);
                } else {
                    System.out.println(">>>>>>>>>>>>>no fue posible acceder a la clase");
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    private void separacion(String txt) {
        System.out.println("_________________________________________________________ " + txt);
    }

    private void sinErrorReflexion() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.Persona");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                ccc.setAccessible(true);
                var inst = ccc.newInstance();
                mostrarDatos(c);
                c.getMethod("setId", int.class).invoke(inst, 2);
                c.getMethod("setNombre", String.class).invoke(inst, "segundo");
                System.out.println(inst);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarDatos(Class<?> c) {
        System.out.println("===========todos los metodos");
        mostrarMetodos(c.getDeclaredMethods());
        System.out.println("===========todos metodos publicos");
        mostrarMetodos(c.getMethods());
        System.out.println("===========todos los fields");
        mostrarCampos(c.getDeclaredFields());
        System.out.println("===========fields publicos");
        mostrarCampos(c.getFields());
    }

    private void leerDesdeModuloAbierto() {
        try {
            final var c = Class.forName("com.sebastian.modulos.comun.abierto.Anemona");
            final var vs = c.getConstructors();
            for (var ccc : vs) {
                if (ccc.trySetAccessible()) {
                    System.out.println("<<<<<<<<<<<<<fue posible acceder a la clase");
                    var inst = ccc.newInstance();
                    mostrarDatos(c);
                    c.getMethod("setId", int.class).invoke(inst, 2);
                    c.getMethod("setNombre", String.class).invoke(inst, "segundo");
                    System.out.println(inst);
                    System.out.println("obtener properties:" 
                    + new String(
                            c.getResourceAsStream("/com/sebastian/modulos/comun/abierto/comun.properties")
                                    .readAllBytes()));
                            
                } else {
                    System.out.println(">>>>>>>>>>>>>no fue posible acceder a la clase");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void mostrarMetodos(Method[] metodos) {
        try {
            Arrays.asList(metodos).stream().forEach(f -> {
                f.setAccessible(true);
                System.out.println(f.getName());
            });
        } catch (Exception e) {
            System.out.println("!!!ERROR!!! " + e.getMessage());
        }
    }

    private void mostrarCampos(Field[] campos) {
        try {
            Arrays.asList(campos).stream().forEach(f -> {
                f.setAccessible(true);
                System.out.println(f.getName());
            });
        } catch (Exception e) {
            System.out.println("!!!ERROR!!! " + e.getMessage());
        }
    }

    private void variableHandlerNoAbierto() {
        try {
            final var clase = Class.forName("com.sebastian.modulos.comun.Persona");
            var instancia = clase.getConstructor().newInstance();
            Persona persona = (Persona) instancia;
            Lookup lp = persona.lookupPrivado(); // privado pero con los permisos desde el modulo
            Lookup lookupPublico = MethodHandles.lookup();
            var handler2 = lookupPublico.findVarHandle(clase, "noEncapsulado", String.class);
            System.out.println(handler2.get(instancia));
            // Lookup lookupPrivado = MethodHandles.privateLookupIn(clase, MethodHandles.lookup()); // error, es privado y no tengo acceso!!!
            var handler1 = lp.findVarHandle(clase, "nombre", String.class);
            System.out.println(handler1.coordinateTypes().size());
            System.out.println(handler1.get(instancia));
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException
                | InstantiationException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void variableHandlerAbierto() {
        try {
            final var clase = Class.forName("com.sebastian.modulos.comun.abierto.Anemona");
            var instancia = clase.getConstructor().newInstance();
            Lookup lookupPublico = MethodHandles.lookup();
            var handler2 = lookupPublico.findVarHandle(clase, "anm", String.class);
            System.out.println(handler2.coordinateTypes().size());
            System.out.println(handler2.get(instancia));
            Lookup lookupPrivado = MethodHandles.privateLookupIn(clase, MethodHandles.lookup());
            var handler1 = lookupPrivado.findVarHandle(clase, "nombre", String.class);
            System.out.println(handler1.coordinateTypes().size());
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException
                | InstantiationException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void mostrarClassLoaderes() {
        System.out.println("platform classloader: " + ClassLoader.getPlatformClassLoader().getName());
        System.out.println("padre de platform classloader: " + ClassLoader.getPlatformClassLoader().getParent());
        System.out.println("padre de system/application classloader: " + getClass().getClassLoader().getSystemClassLoader().getParent().getName());
        System.out.println("nombre del actual class loader: " + getClass().getClassLoader().getName());
        System.out.println("nombre del padre del actual class loader: " + getClass().getClassLoader().getParent().getName());
        System.out.println("system/application classloader: " + getClass().getClassLoader().getSystemClassLoader().getName());
        System.out.println("classloader: " + this.getClass().getClassLoader());
        System.out.println("module class loader: " + this.getClass().getModule().getClassLoader());
        System.out.println("platform classloader: " + ClassLoader.getPlatformClassLoader());

    }

    private void layerInfo() {
        Module modulo = getClass().getModule();
        if (modulo.getLayer() != null) { // tiene layer si no viene del unnamed o el dynamic
            ModuleLayer layer = modulo.getLayer();
            mostrarInfoLayer(layer, modulo);
        } else {
            System.out.println("<<<<<<<<<<<<<<<<<<sin informacion de layer UNNAMED MODULE");
        }
    }

    private void mostrarInfoLayer(ModuleLayer layer, Module modulo) {
        layer.modules().stream().forEach(this::analizarModulo);
        System.out.println("es boot layer?? " + (modulo.getLayer() == ModuleLayer.boot()));
        System.out.println("esta modulo.primero presente en esta layer (y padres): " + layer.findModule("modulos.primero").isPresent());
        System.out.println("esta modulo.comun presente en esta layer (y padres): " + layer.findModule("modulos.comun").isPresent());
    }

    public void analizarModulo(Module modulo) {
        var anotaciones = Arrays.stream(modulo.getDeclaredAnnotations()).map(Annotation::annotationType)
                .map(Object::toString).collect(Collectors.joining(", "));
        System.out.println("anotaciones del modulo: " + anotaciones);
        var md = modulo.getDescriptor();
        if (md == null) {
            System.out.println("UNNAMED MODULE");
        } else {
            System.out.println("modificadores de modulos: " + md.modifiers());
            System.out.println("nombre del modulo: " + md.name());
            System.out.println("raw version: " + md.rawVersion());
            System.out.println("requires: " + md.requires());
            System.out.println("exports: " + md.exports());
            System.out.println("opens: " + md.opens());
            System.out.println("is open: " + md.isOpen());
            System.out.println("paquetes contenidos: " + md.packages());
            System.out.println("main: " + md.mainClass());
        }
    }

    public void buscarEnElStackDeCapas() {
        try {
            StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                    .walk(s -> {
                        s.forEach(d -> {
                            System.out.println("<< " + d.getClassName());
                        });
                        return s;
                    });

            Class<?> c
                    = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                            .walk(s -> s.filter(f -> f.getDeclaringClass() != this.getClass()).findFirst()
                            .map(StackWalker.StackFrame::getDeclaringClass).orElseThrow(IllegalStateException::new));
            System.out.println("clase que es distinta a la Main: " + c);
            Module moduloOtroLayer = c.getModule();
            if (moduloOtroLayer.getLayer() != null) {
                final ModuleLayer ml = moduloOtroLayer.getLayer();
                mostrarInfoLayer(ml, moduloOtroLayer);
            }
        } catch (Exception e) {
            System.out.println("no hay otras capas para buscar otros modulos");
            e.printStackTrace();
        }
    }

    /////////////////////////
    private Configuration createConfiguration(Path[] modulePaths) {
        return getThisLayer()
                .configuration()
                .resolveAndBind(
                        ModuleFinder.of(),
                        ModuleFinder.of(modulePaths),
                        Collections.emptyList()
                );
    }

    private ModuleLayer getThisLayer() {
        System.out.println("get-layer: " + this.getClass().getModule().getLayer());
        return this.getClass().getModule().getLayer();
    }

    private ClassLoader getThisLoader() {
        return this.getClass().getModule().getClassLoader();
    }

    private ModuleLayer createLayer(Path[] modulePaths) {
        Configuration configuration = createConfiguration(modulePaths);
        ClassLoader thisLoader = getThisLoader();
        return getThisLayer()
                .defineModulesWithOneLoader(configuration, thisLoader);
    }

    private void registerNewService(Path... modulePaths) {
        try {
            ModuleLayer layer = createLayer(modulePaths);
            for (Path p : modulePaths) {
                System.out.println("existe: " + p.toFile().exists());
            }
            Stream<Implementable> imp = ServiceLoader
                    .load(layer, Implementable.class).stream()
                    .map(Provider::get);

            System.out.println("implementable: " + imp);

            imp.forEach(i -> {
                System.out.println(i.saludar());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void instanciarProtegido() {
        try {
            Constructor<?> cns = Class.forName("com.sebastian.modulos.comun.Protegido").getDeclaredConstructor();
            cns.setAccessible(true);
            System.out.println(cns.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
