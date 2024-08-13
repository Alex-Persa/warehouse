package org.example.warehouse.utils.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.warehouse.exception.ActiveMQException;

import javax.jms.*;

import static org.example.warehouse.constants.Constants.MQ_BROKER_URL;

public class ActiveMqProducer {

    private final Connection connection;
    private final Session session;
    private final MessageProducer producer;


    public ActiveMqProducer(String queueName) throws ActiveMQException {
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_BROKER_URL);

        try {
            // Create a Connection
            connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(queueName);

            // Create a MessageProducer from the Session to the Topic or Queue
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException e) {
            throw new ActiveMQException(e);
        }

    }

    public void send(String text) throws ActiveMQException {
        TextMessage message;
        try {
            message = session.createTextMessage(text);
            producer.send(message);
        } catch (JMSException e) {
            throw new ActiveMQException(e);
        }

        System.out.println("Sent message: " + text);
    }

    public void stop() throws ActiveMQException {
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new ActiveMQException(e);
        }

    }

}
