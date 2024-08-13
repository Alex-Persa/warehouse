package org.example.warehouse.service;

import org.example.warehouse.mappers.MeasurementMapper;
import org.example.warehouse.utils.activemq.ActiveMqProducer;
import org.example.warehouse.exception.ActiveMQException;

import static org.example.warehouse.constants.Constants.HUMIDITY_QUEUE;

public class HumidityMQService extends ActiveMQService {

    public HumidityMQService() throws ActiveMQException {
        activeMqProducer = new ActiveMqProducer(HUMIDITY_QUEUE);
        measurementMapper = new MeasurementMapper();
    }
}
