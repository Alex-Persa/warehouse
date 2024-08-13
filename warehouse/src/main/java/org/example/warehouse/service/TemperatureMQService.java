package org.example.warehouse.service;

import org.example.warehouse.mappers.MeasurementMapper;
import org.example.warehouse.utils.activemq.ActiveMqProducer;
import org.example.warehouse.exception.ActiveMQException;

import static org.example.warehouse.constants.Constants.TEMPERATURE_QUEUE;


public class TemperatureMQService extends ActiveMQService {

    public TemperatureMQService() throws ActiveMQException {
        activeMqProducer = new ActiveMqProducer(TEMPERATURE_QUEUE);
        measurementMapper = new MeasurementMapper();
    }
}
