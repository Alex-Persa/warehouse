package org.example.centralservice.activeMQ.connection;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.centralservice.activeMQ.exceptions.ExceptionListener;

import javax.jms.Connection;
import javax.jms.JMSException;

import static org.example.centralservice.constants.Constants.MQ_BROKER_URL;

public class ActiveMqConnection {

    private static ActiveMqConnection instance;
    private final Connection connection;

    private ActiveMqConnection() throws JMSException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);

        // Create a Connection
        connection = connectionFactory.createConnection();
        connection.start();

        connection.setExceptionListener(new ExceptionListener());
    }

     synchronized public static Connection getConnection() throws JMSException {
        if (instance == null)
        {
            instance = new ActiveMqConnection();
        }
        return instance.connection;
    }
}
