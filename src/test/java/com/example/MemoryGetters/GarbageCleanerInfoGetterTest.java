package com.example.MemoryGetters;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GarbageCleanerInfoGetterTest 
{
    @Test
    public void testGetGCCollectorInfo()
    {
        GarbageCollectorsInfoGetter gcInfo = new GarbageCollectorsInfoGetter();
        String info = gcInfo.getGCsFullInfo();
        System.out.println(info);
    }
}