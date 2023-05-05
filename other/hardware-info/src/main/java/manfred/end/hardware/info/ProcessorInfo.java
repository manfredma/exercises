package manfred.end.hardware.info;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

public class ProcessorInfo {
    public static void main(String[] args) throws InterruptedException {
        // Initialize
        SystemInfo si = new SystemInfo();
        CentralProcessor processor = si.getHardware().getProcessor();

        // Record cpu ticks and calculate usage in 1 second
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Thread.sleep(1000);
        int cpuUsage = (int) (processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100);
        System.out.println("CPU usage: " + cpuUsage + "%");
    }
}
