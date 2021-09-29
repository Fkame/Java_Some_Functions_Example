package com.example;

import org.junit.Test;
import java.io.File;

public class LoadForeignClassTest {
    
    @Test
    public void testLoadClass() {
        //String subDirectory = "\\src\\SomeFolderOutOfClassPath\\onepack\\twopack\\demo\\";
        String subDirectory = "\\src\\SomeFolderOutOfClassPath\\";
        String absolutePathToFile = new File("").getAbsolutePath() + subDirectory;
        String fullJavaClassName = "onepack.twopack.demo.SomeForeignClass";
        String methodName = "convertIntToArray";
        Class<?>[] argsTypesForMethod = {int.class, int.class, char.class };
        Object[] argsForMethod = { (Object)5, (Object)3, (Object)'+' };

        LoadForeignClass loader = new LoadForeignClass();
        loader.loadClass(absolutePathToFile, fullJavaClassName, methodName, argsTypesForMethod, argsForMethod);
    }

    @Test 
    public void testLoadClassWithDependency() {
        String subDirectory = "\\src\\SomeFolderOutOfClassPath\\";
        String absolutePathToFile = new File("").getAbsolutePath() + subDirectory;
        String fullJavaClassName = "onepack.twopack.demo.SomeClassWithDependency";

        String methodName = "createInnerObjWithValues";
        Class<?>[] argsTypesForMethod = {int.class, int.class };
        Object[] argsForMethod = { (Object)5, (Object)3, };

        LoadForeignClass loader = new LoadForeignClass();
        loader.loadClass(absolutePathToFile, fullJavaClassName, methodName, argsTypesForMethod, argsForMethod);
    }
}
