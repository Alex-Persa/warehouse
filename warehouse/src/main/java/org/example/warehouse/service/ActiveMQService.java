package org.example.warehouse.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.warehouse.mappers.MeasurementMapper;
import org.example.warehouse.model.Measurement;
import org.example.warehouse.utils.activemq.ActiveMqProducer;
import org.example.warehouse.exception.ActiveMQException;

public abstract class ActiveMQService {

    ActiveMqProducer activeMqProducer;
    MeasurementMapper measurementMapper;

    public void send(String measurementStr) {
        String toSend;
        Measurement measurement;

        //input validation
        try {
            measurement = measurementMapper.toMeasurement(measurementStr);
        } catch (IllegalArgumentException e) {
            System.out.println("will not send. " + e.getMessage());
            return;
        }

        //serialize
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            toSend = objectMapper.writeValueAsString(measurement);
        } catch (JsonProcessingException e) {
            e.printStackTrace(System.out);
            return;
        }

        //send to queue
        try {
            activeMqProducer.send(toSend);
        } catch (ActiveMQException e) {
            e.printStackTrace(System.out);
        }
    }

    public void closeSessions() {
        try {
            activeMqProducer.stop();
        } catch (ActiveMQException e) {
            System.out.println("failed to stop activeMqProducer sessions");
            e.printStackTrace(System.out);
        }
    }
}
