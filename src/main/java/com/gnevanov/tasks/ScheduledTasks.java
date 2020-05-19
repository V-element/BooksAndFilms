package com.gnevanov.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledTasks {

    @Scheduled(fixedRate = 60000)
    public void exportDataToJSON() {

    }

    @Scheduled(fixedRate = 60000)
    public void exportDataToXML() {

    }
}
