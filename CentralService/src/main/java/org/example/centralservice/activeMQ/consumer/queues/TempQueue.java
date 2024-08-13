package org.example.centralservice.activeMQ.consumer.queues;

import org.example.centralservice.activeMQ.consumer.ActiveMqConsumer;
import org.example.centralservice.service.impl.TemperatureAlarmService;

import javax.jms.JMSException;

import static org.example.centralservice.constants.Constants.TEMPERATURE_QUEUE;

public class TempQueue extends ActiveMqConsumer {
    public TempQueue() throws JMSException {
        super(TEMPERATURE_QUEUE);
        alarmService = new TemperatureAlarmService();
    }
}
