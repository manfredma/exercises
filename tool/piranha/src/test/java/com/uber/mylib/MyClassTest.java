package com.uber.mylib;

import org.junit.Test;

public class MyClassTest {

    private XPTest expt;

    @Test
    public void x() {
        expt.enableFlag(TestExperimentName.SAMPLE_STALE_FLAG);
    }

    @Test
    public void y() {
        expt.disableFlag(TestExperimentName.SAMPLE_STALE_FLAG);
    }
}
