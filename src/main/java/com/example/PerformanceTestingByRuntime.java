package com.example;

import java.util.*;
import com.example.MemoryGetters.*;
import com.example.ClassesForTests.*;

/**
 * Класс предоставляющий ряд примеров кода работы с памятью в Java с помощью методов класса Runtime.
 * Класс использует объект типа MemoryDataGetterFromRuntime, в котором происходит получение всей информации о памяти.
 * @see MemoryDataGetterFromRuntime
 * Для вывода информации о сборщиках мусора используется класс GarbageCollectorsInfoGetter
 * @see GarbageCollectorsInfoGetter
 */
public class PerformanceTestingByRuntime {
    
    /*
    Суть теста в демонстрации заполнения памяти объектами, и их последующая очистка сборщиком мусора.

    Также наглядно то, что имеется 2 сборщика: один для неглубокой очистки, второй для глубокой.
    Сборщики имеют следующие области: EDEN, Survivor Space, Tenured Gen. 
    Видимо, там хранятся объекты в порядке повышения возраста. Чем дольше хранится объект, тем меньше вероятность, 
    что его понадобится удалять. Поэтому Tenured очищается только вторым.

    Чтобы понять, что происходит, лучше почитать о принципах работы сборщика мусора.
    */

    /**
     * Метод пример использования методов вывода информации об используемой памяти и вызова сборщика мусора.
     */
    public void startBasicTest() {
        GarbageCollectorsInfoGetter gc = new GarbageCollectorsInfoGetter();
        MemoryDataGetterFromRuntime r = new MemoryDataGetterFromRuntime();

        System.out.println(String.format("Garbage Collectors status before load:\n%s", gc.getGCsFullInfo()));
        System.out.println(String.format("\nMemory status before load:\n%s", this.prepareMemoryDataInKB(r)));

        List<Person> list = new ArrayList<Person>();
        for (int i = 0; i <= 100000; i++) {
            list.add(new Person("Jimmmmmmmm", "Knopffffffffffffff"));
            if (i % 10000 == 0) 
                System.out.println(String.format("\nIts [%d] iteration, status:\n%s", i, this.prepareMemoryDataInKB(r)));
        }
        System.out.println("\nAfter all iterations:");
        showGCandMemoryStatus(r, gc);

        System.out.println("\nRunning garbage cleaner without removing references...");
        Runtime.getRuntime().gc();
        showGCandMemoryStatus(r, gc);

        System.out.println("\nRunning garbage cleaner after removing references...");
        list.clear();
        list = null;
        Runtime.getRuntime().gc();
        showGCandMemoryStatus(r, gc);
    }

    /**
     * Метод формирования строки с данными о свободной и занятой памяти в килобайтах.
     * @param r - объект, из которого можно получить информацию о памяти.
     * @return - строка, объединяющую полученную информацию о памяти в Килобайтах.
     */
    public String prepareMemoryDataInKB(MemoryDataGetterFromRuntime r) {
        return String.format("FreeMemory=%d KB, UsedMemory=%d KB", 
            r.convertBytesToKiloBytes(r.getTotalFreeMemory()), 
            r.convertBytesToKiloBytes(r.getUsedMemory())
        );
    }

    /**
     * Метод формирования строки с данными о: свободной, занятой, максимальной, подготовленной 
     * к размещению объектов памяти в Килобайтах.
     * Подготовленная память является частью свободной памяти.
     * @param r - объект, из которого можно получить информацию о памяти.
     * @return - строка, объединяющую полученной информацию о памяти в Килобайтах.
     */
    public String prepareFullMemoryDataInKB(MemoryDataGetterFromRuntime r) {
        return String.format("FreeMemory=%d KB, UsedMemory=%d KB, MaxMemory= %d KB, PreparedMemory=%d Kb", 
            r.convertBytesToKiloBytes(Runtime.getRuntime().maxMemory()), 
            r.convertBytesToKiloBytes(r.getTotalFreeMemory()), 
            r.convertBytesToKiloBytes(r.getUsedMemory()),
            r.convertBytesToKiloBytes(Runtime.getRuntime().maxMemory()), 
            r.convertBytesToKiloBytes(Runtime.getRuntime().freeMemory())
        );
    }

    /**
     * Метод, выводящий в стандартный выходной поток информацию об используемой памяти, а также информацию о сборщиках мусора.
     * @param r - объект, позволяющий получить информацию о состоянии памяти.
     * @param gc - объект, позволяющий получить информацию о сборщиках мусора.
     */
    public void showGCandMemoryStatus(MemoryDataGetterFromRuntime r, GarbageCollectorsInfoGetter gc) {
        System.out.println("Memory status:" + this.prepareMemoryDataInKB(r));
        System.out.println("gc status:" + gc.getGCsWorkInfo());
    }
}
