package org.example.centralservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.centralservice.model.Measurement;

public abstract class AlarmService {

    public void process(String measurementStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Measurement measurement = objectMapper.readValue(measurementStr, Measurement.class);
            checkTreshold(measurement);
        } catch (JsonProcessingException e) {
            e.printStackTrace(System.out);
            System.out.println("Invalid message:" + measurementStr);
        }
    }


    public abstract void checkTreshold(Measurement measurement);
}
