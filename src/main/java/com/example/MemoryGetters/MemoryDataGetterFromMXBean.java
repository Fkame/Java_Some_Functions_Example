package com.example.MemoryGetters;

import java.lang.management.*;

/*
        +----------------------------------------------+
        +////////////////           |                  +
        +////////////////           |                  +
        +----------------------------------------------+

        |--------|
           init
        |---------------|
               used
        |---------------------------|
                  committed
        |----------------------------------------------|
                            max

        init = represents the initial amount of memory (in bytes) 
            that the Java virtual machine requests from the operating system for memory management during startup
        used = represents the amount of memory currently used (in bytes). 
        committed = represents the amount of memory (in bytes) that is guaranteed to be available for use 
            by the Java virtual machine. The amount of committed memory may change over time (increase or decrease). 
            The Java virtual machine may release memory to the system and committed could be less than init. committed will 
            always be greater than or equal to used. 
        max = represents the maximum amount of memory (in bytes) that can be used for memory management. Its value may be undefined. 

        Comparison with Runtime methods:
        used == Runtime.totalMemory - Runtime.freeMemory
        commited == totalMemory
        max == max

*/

/**
 * Класс, предоставляющий методы для получения информации о состоянии памяти. Использует JMX для получения информации.
 * @see MemoryMXBean
 */
public class MemoryDataGetterFromMXBean {

    /**
     * Сколько байт в килобайте. Используется для перевода.
     */
    public static final long BYTES_IN_KILOBYTE = 1024L;

    /**
     * Сколько байт в мегабайте. Используется для перевода.
     */
    public static final long BYTES_IN_MEGABYTE = BYTES_IN_KILOBYTE * 1024L;

    /**
     * Метод для получения полной информации о состоянии памяти в стеке в килобайтах.
     * @return - информация о памяти в некуче (возможно, стеке) в килобайтах.
     */
    public String getFullNonHeapInfoInKB() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getNonHeapMemoryUsage();
        return String.format("init=%d, used=%d, commited=%d, max=%d", 
            memory.getInit() / BYTES_IN_KILOBYTE, 
            memory.getUsed() / BYTES_IN_KILOBYTE, 
            memory.getCommitted() / BYTES_IN_KILOBYTE, 
            memory.getMax() / BYTES_IN_KILOBYTE
        );
    }

    /**
     * Метод для получения полной информации о состоянии памяти в кучи в килобайтах.
     * @return - информация о памяти в куче в килобайтах.
     */
    public String getFullHeapInfoInKB() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getHeapMemoryUsage();
        return String.format("init=%d, used=%d, commited=%d, max=%d", 
            memory.getInit() / BYTES_IN_KILOBYTE, 
            memory.getUsed() / BYTES_IN_KILOBYTE, 
            memory.getCommitted() / BYTES_IN_KILOBYTE, 
            memory.getMax() / BYTES_IN_KILOBYTE
        );
    }

    /**
     * Метод для получения информации о том, сколько памяти занято в стеке.
     * @return - количество занятой памяти в стеке в байтах.
     */
    public long getNonHeapUsed() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getNonHeapMemoryUsage();
        return memory.getUsed();
    }

    /**
     * Метод для получения информации о том, сколько памяти свободно в стеке.
     * @return - количество свободной памяти в стеке в байтах.
     */
    public long getNonHeapFree() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getNonHeapMemoryUsage();
        return memory.getMax() - memory.getUsed();
    }

    /**
     * Метод для получения информации о том, сколько памяти занято в куче.
     * @return - количество занятой памяти в куче в байтах.
     */
    public long getHeapUsed() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getHeapMemoryUsage();
        return memory.getUsed();
    }

     /**
     * Метод для получения информации о том, сколько памяти свободно в куче.
     * @return - количество свободной памяти в куче в байтах.
     */
    public long getHeapFree() {
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        MemoryUsage memory = mem.getHeapMemoryUsage();
        return memory.getMax() - memory.getUsed();
    }

    /**
     * Метод перевода байты в килобайты.
     * @param bytes - байты.
     * @return - килобайтов в байтах.
     */
    public long convertBytesToKB(long bytes) {
        return bytes / BYTES_IN_KILOBYTE;
    }

    /**
     * Метод перевода байты в мегабайты.
     * @param bytes - байты.
     * @return - мегабайтов в байтах.
     */
    public long convertBytesToMB(long bytes) {
        return bytes / BYTES_IN_MEGABYTE;
    }
}
