package org.example.centralservice.service.impl;

import org.example.centralservice.model.Measurement;
import org.example.centralservice.service.AlarmService;

public class HumidityAlarmService extends AlarmService {

    public static final int HUMIDITY_TRESHOLD = 50;

    @Override
    public void checkTreshold(Measurement measurement) {
        if(measurement.getValue() > HUMIDITY_TRESHOLD) {
            System.out.println("ALARM!! HUMIDITY=" + measurement.getValue() + " in " + measurement.getSensorId());
        }
    }
}
