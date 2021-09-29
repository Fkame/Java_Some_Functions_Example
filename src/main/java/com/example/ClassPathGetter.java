package com.example;

import java.net.URL;
import java.net.URLClassLoader;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Класс предоставляющий примеры кода для получения различной информации о classpath текущего приложения.
 */
public class ClassPathGetter {  

    /**
     * Метод для получения classpath из Properties приложения.
     * @see System#getProperty(String)
     * @return - все пути, включенные в classpath
     */
    public String getClassPathFromGetProperties() {
        String cp = System.getProperty("java.class.path");
        return cp;
    }

    /**
     * Метод для получения classpath из JMX.
     * @see ManagementFactory#getRuntimeMXBean()
     * @see RuntimeMXBean
     * @return - все пути, включенные в classpath.
     */
    public String getClassPathFromMXBean() {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String cp = bean.getClassPath();
        return cp;
    }

    /**
     * Метод для получения путей библиотек(?).
     * @see RuntimeMXMBean
     * @return - пути библиотек.
     */
    public String getLibraryPathFromMXBean() {
        RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
        String cp = bean.getLibraryPath();
        return cp;
    }

    /*
    Начиная с Java 11 уже не работает
    */
    /**
     * Получение classpath путем преобразования SystemLoader к URLClassLoader и вызова его метода.
     * Начиная где-то с Java 11 уже не работает.
     * @see URLClassLoader#getURLs()
     * @return - все пути, включенные в classpath.
     */
    @Deprecated
    public String getClassPathFromSystemLoader() {
        StringBuilder builder = new StringBuilder();
        try {
            ClassLoader cl = ClassLoader.getSystemClassLoader();
            URL[] urls = ((URLClassLoader)cl).getURLs();
            for(URL url: urls) {
                String file = url.getFile();
                builder.append(file).append(" ");
            }
        } catch (Exception e) { 
            System.out.println("This sh*t doesnt works!"); 
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
