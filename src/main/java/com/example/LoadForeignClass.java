package com.example;

import java.net.URL;
import java.net.URLClassLoader;
import java.io.File;
import java.lang.reflect.Method;
import java.security.*;

/**
 * Класс предоставляющий примеры кода для загрузки классов вне classpath во время Runtime с помощью URLClassLoader и рефлексии.
 * Рефлексия достаточно мощный механизм, позволяющий работать с классом, имея только текстовую информацию о нём.
 * @see URLClassLoader
 * @see Class - в нём методы рефлексии или типа того.
 * 
 * ! Класс, который нужно загрузить должен быть скомпилирован в .class файл.
 */
public class LoadForeignClass {

    /**
     * Метод загрузки класса, вызова его конструкта и метода с параметрами, 
     * также ещё выводит его местополжение в стандартный поток вывода.
     * @param absolutePathToFile - полный путь к расположению класса - директорию.
     * @param fullJavaClassName - полное имя класс. Полное имя класса = пакет + имя_класса. Пример: com.example.LoadForeignClass.
     * @param methodName - имя метода в виде строки.
     * @param argsTypesForMethod - массив из типов, принимаемых методом аргументов.
     * @param argsForMethod - массив из аргументов на вход методу.
     */
    public void loadClass(String absolutePathToFile, String fullJavaClassName, 
                            String methodName, Class<?>[] argsTypesForMethod, Object[] argsForMethod) 
    {
        try {
            File file = new File(absolutePathToFile);
            URL url = file.toURI().toURL();
            URL[] urlsToCheck = new URL[] { url }; 

            // Загрузка .class файа по пути, где он лежит (вне classpath), путь пакета не включается в общий путь
            ClassLoader loader = new URLClassLoader(urlsToCheck);
            Class<?> foreignClass = loader.loadClass(fullJavaClassName);

            // Вывод где находится файл
            ProtectionDomain pDomain = foreignClass.getProtectionDomain(); 
		    CodeSource cSource = pDomain.getCodeSource(); 
		    URL urlfrom = cSource.getLocation();
		    System.out.println("Loaded from: " + urlfrom.getFile());
            System.out.println("");

            // Создание экземпляра класса и вызов метода
            Object foreignClassObj = foreignClass.getConstructor().newInstance();
            Method method = foreignClass.getMethod(methodName, argsTypesForMethod);
            String answer = (String)method.invoke(foreignClassObj, argsForMethod);

            System.out.println("Method Successfully Called!, it returns: [" + answer + "]");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
