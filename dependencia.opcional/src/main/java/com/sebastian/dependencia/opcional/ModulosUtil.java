package com.sebastian.dependencia.opcional;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastián Ávila A.
 */
public class ModulosUtil {

    private static final Logger LOGGER = Logger.getLogger(ModulosUtil.class.getName());

    public static boolean isModulePresent(String moduleName) {
        return searchRootModuleLayer() == null ? false : searchRootModuleLayer()
                .findModule(moduleName)
                .isPresent();
    }

    private static ModuleLayer searchRootModuleLayer() {
        return getCallerClass() == null ? null : getCallerClass()
                .getModule() == null ? null : getCallerClass()
                                .getModule()
                                .getLayer();
    }

    private static Class<?> getCallerClass() {
        Class<?> clase = null;
        try {
            clase = StackWalker
                    .getInstance(RETAIN_CLASS_REFERENCE)
                    .walk(stack
                            -> {
                        return stack.distinct().peek(s -> {
                            LOGGER.log(Level.FINEST, "file-name: {0}", s.getFileName());
                            LOGGER.log(Level.FINEST, "class-name: {0}", s.getClassName());
                            LOGGER.log(Level.FINEST, "method-name: {0}", s.getMethodName());
                            LOGGER.log(Level.FINEST, "modulo: {0}", s.getClass().getModule().getName());
                            LOGGER.log(Level.FINEST, "class loader: {0}",
                                     s.getClass().getModule().getClassLoader() != null
                                    ? s.getClass().getModule().getClassLoader()
                                    : null);
                        })
                                .filter(frame
                                        -> frame.getDeclaringClass() != ModulosUtil.class)
                                .findFirst()
                                .map(StackWalker.StackFrame::getDeclaringClass)
                                .orElseThrow(IllegalStateException::new);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clase;
    }
}
