package com.uber.mylib;

public class MyClassTest {

    private XPTest expt;

    public void x() {
        expt.enableFlag(TestExperimentName.SAMPLE_STALE_FLAG);
    }

    public void y() {
        expt.disableFlag(TestExperimentName.SAMPLE_STALE_FLAG);
    }
}
