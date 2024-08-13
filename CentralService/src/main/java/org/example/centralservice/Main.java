package org.example.centralservice;


import org.example.centralservice.activeMQ.consumer.queues.HumidityQueue;
import org.example.centralservice.activeMQ.consumer.queues.TempQueue;

import javax.jms.JMSException;


public class Main {
    public static void main(String[] args) throws JMSException {
        new TempQueue().start();
        new HumidityQueue().start();
    }
}