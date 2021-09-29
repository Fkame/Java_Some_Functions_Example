package com.example.MemoryGetters;

import java.lang.management.*;
import java.util.*;

/**
 * Класс, представляющий методы получения информации о сброщиках мусора. Сам он получает информацию с помощью Java Management 
 * Extensions (JXM).
 */
public class GarbageCollectorsInfoGetter {
    
    /**
     * Метод получения полной информации о сброщиках мусора.
     * @return - строка - информация о сборщиках мусора.
     */
    public String getGCsFullInfo() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        StringBuilder builder = new StringBuilder();

        for (GarbageCollectorMXBean gc : gcList) {
            builder.append(String.format(
                "GC_name=%s, GC_ObjectName={%s}, CollectionsCount=%d, CollectionTime=%d isValid=%b, memoryPoolNames=[%s]", 
                gc.getName(), 
                gc.getObjectName(),
                gc.getCollectionCount(),
                gc.getCollectionTime(), 
                gc.isValid(),
                String.join(" / ", gc.getMemoryPoolNames())
                )
            );
            builder.append('\n');
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * Метод, позволяюзий получить сокращенную информацию о сборщиках мусора.
     * @return - строка - информация о сборщиках мусора.
     */
    public String getGCsWorkInfo() {
        List<GarbageCollectorMXBean> gcList = ManagementFactory.getGarbageCollectorMXBeans();
        StringBuilder builder = new StringBuilder();

        for (GarbageCollectorMXBean gc : gcList) {
            builder.append(String.format(
                "GC_name=%s, CollectionsCount=%d, CollectionTime=%d isValid=%b", 
                gc.getName(), 
                gc.getCollectionCount(),
                gc.getCollectionTime(), 
                gc.isValid()
                )
            );
            builder.append('\n');
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
