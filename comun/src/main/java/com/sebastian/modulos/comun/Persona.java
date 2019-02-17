package com.sebastian.modulos.comun;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import java.lang.StackWalker.StackFrame;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

/**
 *
 * @author Sebastian Avila A.
 */
public class Persona {

    private int id;
    private String nombre;
    public String noEncapsulado = "valor-no-encapsulado";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + '}';
    }

    public Lookup lookupPrivado() throws IllegalAccessException {
        return MethodHandles.privateLookupIn(this.getClass(), MethodHandles.lookup());
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public boolean isModulePresent(String moduleName) {
        return searchRootModuleLayer() == null ? false : searchRootModuleLayer()
                .findModule(moduleName)
                .isPresent();
    }

    private ModuleLayer searchRootModuleLayer() {
        return getCallerClass() == null ? null : getCallerClass()
                .getModule() == null ? null : getCallerClass()
                                .getModule()
                                .getLayer();
    }

    private Class<?> getCallerClass() {
        Class<?> clase = null;
        try {
            clase = StackWalker
                    .getInstance(RETAIN_CLASS_REFERENCE)
                    .walk(stack
                            -> {
                        return stack.distinct().peek(s -> {
                            System.out.println("file-name: " + s.getFileName());
                            System.out.println("class-name: " + s.getClassName());
                            System.out.println("descriptor: " + s.getDescriptor());
                            System.out.println("method-name: " + s.getMethodName());
                            System.out.println("modulo: " + s.getClass().getModule().getName());
                            System.out.println("class loader: " + 
                                    s.getClass().getModule().getClassLoader() != null ? 
                                    s.getClass().getModule().getClassLoader() : 
                                    null);
                        })
                            .filter(frame
                                    -> frame.getDeclaringClass() != this.getClass())
                            .findFirst()
                            .map(StackFrame::getDeclaringClass)
                            .orElseThrow(IllegalStateException::new);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clase;
    }
}
