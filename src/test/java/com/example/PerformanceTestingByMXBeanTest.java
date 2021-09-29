package com.example;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PerformanceTestingByMXBeanTest {

    @Test
    public void runBasicTest()
    {
        new PerformanceTestingByMXBean().startBasicTest();
    }
}
