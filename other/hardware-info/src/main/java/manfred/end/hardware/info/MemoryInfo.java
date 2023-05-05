package manfred.end.hardware.info;

import oshi.SystemInfo;

public class MemoryInfo {
    public static void main(String[] args) {
        // Initialize
        SystemInfo si = new SystemInfo();
        System.out.println(
                "available = " + si.getHardware().getMemory().getAvailable() / 1024 / 1024 / 1024 +
                        " G");
        System.out.println("Physical Memory = " + si.getHardware().getMemory().getPhysicalMemory());
        System.out.println("Virtual Memory = " + si.getHardware().getMemory().getVirtualMemory());
        System.out.println(
                "Total = " + si.getHardware().getMemory().getTotal() / 1024 / 1024 / 1024 + " G");
        System.out.println(
                "pageSize = " + si.getHardware().getMemory().getPageSize() / 1024 + " K");
    }
}
