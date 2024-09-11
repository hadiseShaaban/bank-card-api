package com.example.bank_card_api.monitor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MemoryUsageReporter {

    @Scheduled(fixedRate = 20000)//every 20s
    public void reportMemoryUsage() {

        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();

        System.out.printf("Memory Usage - Used: %d MB, Free: %d MB, Total: %d MB, Max: %d MB%n",
                usedMemory / (1024 * 1024),
                freeMemory / (1024 * 1024),
                totalMemory / (1024 * 1024),
                maxMemory / (1024 * 1024));
    }
}