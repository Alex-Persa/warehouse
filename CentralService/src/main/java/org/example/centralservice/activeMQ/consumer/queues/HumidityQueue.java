package org.example.centralservice.activeMQ.consumer.queues;

import org.example.centralservice.activeMQ.consumer.ActiveMqConsumer;
import org.example.centralservice.service.impl.HumidityAlarmService;

import javax.jms.JMSException;

import static org.example.centralservice.constants.Constants.HUMIDITY_QUEUE;

public class HumidityQueue extends ActiveMqConsumer  {
    public HumidityQueue() throws JMSException {
        super(HUMIDITY_QUEUE);
        alarmService = new HumidityAlarmService();
    }
}
