package com.example;

import com.example.MemoryGetters.*;
import com.example.ClassesForTests.*;
import java.util.*;

/**
 * Класс предоставляющий ряд примеров кода работы с памятью в Java c помощью средств Java Management Extension (JMX).
 * Класс использует объект типа MemoryDataGetterFromMXBean, в котором происходит получение всей информации о памяти.
 * @see MemoryDataGetterFromMXBean
 * Для вывода информации о сборщиках мусора используется класс GarbageCollectorsInfoGetter
 * @see GarbageCollectorsInfoGetter
 */
public class PerformanceTestingByMXBean {
    
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
        MemoryDataGetterFromMXBean r = new MemoryDataGetterFromMXBean();

        System.out.println(String.format("Garbage Collectors status before load:\n%s", gc.getGCsFullInfo()));
        System.out.println("\nMemory status before load:");
        showFullMemoryInfo(r);

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
     * Метод, осуществляющий вывод в стандартный поток вывода информации о памяти  кучи и стека (Heap & Non-heap).
     */
    public void showFullMemoryInfo(MemoryDataGetterFromMXBean r) {
        String fullNonHeap = r.getFullNonHeapInfoInKB();
        String fullHeap = r.getFullHeapInfoInKB();
        System.out.println(String.format("NonHeap status: [%s]\nHeap status: [%s]", fullNonHeap, fullHeap));
    }

    /**
     * Метод формирования строки с данными о свободной и занятой памяти в килобайтах в куче и в стеке.
     * @param r - объект, позвляющий получить информацию о состоянии памяти.
     * @return - строка, представляющая собой собранную информацию в килобайтах.
     */
    public String prepareMemoryDataInKB(MemoryDataGetterFromMXBean r) {
        return String.format(
            "NonHeap: free=%d, used=%d\nHeap: free=%d, used=%d",
            r.convertBytesToKB(r.getNonHeapFree()),
            r.convertBytesToKB(r.getNonHeapUsed()),
            r.convertBytesToKB(r.getHeapFree()),
            r.convertBytesToKB(r.getHeapUsed())
        );
    }

    /**
     * Метод, осуществляющий вывод в стандартный поток вывода информации о состоянии памяти и информации о сборщиках мусора.
     * @param r - объект, позвляющий получить информацию о состоянии памяти.
     * @param gc - объект, позвляющий получить информацию о сборщиках мусора.
     */
    public void showGCandMemoryStatus(MemoryDataGetterFromMXBean r, GarbageCollectorsInfoGetter gc) {
        System.out.println("Memory status:\n" + this.prepareMemoryDataInKB(r));
        System.out.println("GC status:\n" + gc.getGCsWorkInfo());
    }
}
