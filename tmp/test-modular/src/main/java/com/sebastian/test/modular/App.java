package com.sebastian.test.modular;

import java.lang.module.ModuleDescriptor.Version;

/**
 *
 * @author Sebastián Ávila A.
 */
public class App {
    public static void main(String[] args) {
        var md = App.class.getModule().getDescriptor();
        System.out.println(md.rawVersion().orElse("no hay versión 1"));
        System.out.println(md.version().map(Version::toString).orElse("no hay version 2"));

    }
}
