package com.uber.mylib;

import org.junit.Test;

public class MyClassTest {

    private XPTest2 expt;

    @Test
    public void x() {
        expt.enableFlag(TestExperimentName2.SAMPLE_STALE_FLAG);
    }

    @Test
    public void y() {
        expt.disableFlag(TestExperimentName2.SAMPLE_STALE_FLAG);
    }
}
