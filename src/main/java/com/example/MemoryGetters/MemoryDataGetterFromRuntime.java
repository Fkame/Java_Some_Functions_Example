package com.example.MemoryGetters;

/**
 * Класс, предоставляющий методы для получения информации о состоянии памяти. Использует класс Runtime для получения информации.
 * @see Runtime
 */
public class MemoryDataGetterFromRuntime {
     /*
                    runtime.maxMemory()
     ------------------------^---------------------------
    |                                                    |
    |        runtime.totalMemory()                       |
     -----------------^---------------                   |
    |                                 |                  |
    |           runtime.freeMemory()  |                  |
    |                  --------^------                   |
    |                 |               |                  |
    ------------------------------------------------------
    | Used memory.    | Free memory.  | Unlocated memory |
    | --------------- | ------------- | ---------------- |
    | currently used  | ready for     | designed for     |
    | by instatiated  | new objects   | future objects   |
    | objects         |               |                  |
    ------------------------------------------------------

    usedMemory = totalMemory - freeMemory();
    totalFree = maxMemory - usedMemory
    */

    /**
     * Сколько байт в килобайте. Используется для перевода.
     */
    public static final long BYTES_IN_KILOBYTE = 1024L;

    /**
     * Сколько байт в мегабайте. Используется для перевода.
     */
    public static final long BYTES_IN_MEGABYTE = BYTES_IN_KILOBYTE * 1024L;

    /**
     * Метод получения количества занятой памяти
     * @return = используемая память.
     */
    public long getUsedMemory() {
        Runtime r = Runtime.getRuntime();
        return  r.totalMemory() - r.freeMemory();
    }

    /**
     * Метод получения общего числа свободной памяти.
     * @return - общее количество свободной памяти.
     */
    public long getTotalFreeMemory() {
        Runtime r = Runtime.getRuntime();
        return  r.maxMemory() - this.getUsedMemory();
    }

    /**
     * Метод перевода байты в килобайты.
     * @param bytes - байты.
     * @return - килобайтов в байтах.
     */
    public long convertBytesToKiloBytes(long bytes) {
        return bytes / BYTES_IN_KILOBYTE;
    }

    /**
     * Метод перевода байты в мегабайты.
     * @param bytes - байты.
     * @return - мегабайтов в байтах.
     */
    public long convertBytesToMegaBytes(long bytes) {
        return bytes / BYTES_IN_MEGABYTE;
    }
}
