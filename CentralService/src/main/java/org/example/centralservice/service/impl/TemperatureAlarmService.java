package org.example.centralservice.service.impl;

import org.example.centralservice.model.Measurement;
import org.example.centralservice.service.AlarmService;

public class TemperatureAlarmService extends AlarmService {

    public static final int TEMP_TRESHOLD = 35;

    @Override
    public void checkTreshold(Measurement measurement) {
        if(measurement.getValue() > TEMP_TRESHOLD) {
            System.out.println("ALARM!! Temperature=" + measurement.getValue() + " in " + measurement.getSensorId());
        }
    }
}
