package onepack.twopack.demo;

import onepack.twopack.demo.threepack.*;

/*
 Чтобы скомпилировать нужно вызвать (если находишься в директории /demo): javac ./*.java ./threepack/*.java
 ИЛИ можно вернуться в корневую для проекта директорию - там, где начинаются пакеты - SomeFolderOutOfClassPath и 
    вызвать javac onepack/twopack/demo/SomeClassWithDependency.java

 Если класс ThreePackClass не будет найдет компилятором, он выдаст - cannot find package & cannot find symbol ThreePackClass
*/

/**
 * Класс, имеющий зависимость в виде класса, находящегося в другом пакете.
 * ! Перед подключением нужно отдельно скомпилировать .class файл.
 */
public class SomeClassWithDependency {
    public SomeClassWithDependency() {  }

    private ThreePackClass inner;
    
    public void createInnerObjWithValues(int v1, int v2) {
        inner = new ThreePackClass(v1, v2);
    }

    public String getInnerValuesAsString() {
        return String.valueOf(inner.getSomeValue1()) + " " +  String.valueOf(inner.getSomeValue2());
    }
}
