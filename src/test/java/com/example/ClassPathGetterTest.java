package com.example;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ClassPathGetterTest {
    
    ClassPathGetter getter;

    @Before
    public void preparation() {
        getter = new ClassPathGetter();
    }

    @Test
    public void testGetClassPathFromGetProperties() {
        String result = getter.getClassPathFromGetProperties();
        assertNotNull(result);
        System.out.println("ClassPath from System.getProperty(\"java.class.path\"): " + String.join("\n", result.split(";")));
    }

    @Test
    public void testGetClassPathFromMXBean() {
        String result = getter.getClassPathFromMXBean();
        System.out.println("ClassPath from RuntimeMXBean object: " + String.join("\n", result.split(";")));
    }

    @Test 
    public void testGetLibraryPathFromMXBean() {
        String result = getter.getLibraryPathFromMXBean();
        System.out.println("Library from RuntimeMXBean object: " + String.join("\n", result.split(";")));
    }

}
