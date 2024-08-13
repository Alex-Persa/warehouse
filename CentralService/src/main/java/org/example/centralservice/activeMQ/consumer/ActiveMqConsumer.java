package org.example.centralservice.activeMQ.consumer;

import org.example.centralservice.activeMQ.connection.ActiveMqConnection;
import org.example.centralservice.service.AlarmService;

import javax.jms.*;


public abstract class ActiveMqConsumer extends Thread {

    private final MessageConsumer consumer;
    protected AlarmService alarmService;

    boolean running;

    public ActiveMqConsumer(String queueName) throws JMSException {
        Connection connection = ActiveMqConnection.getConnection();
        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         // Create the destination (Topic or Queue)
         Destination destination = session.createQueue(queueName);

         // Create a MessageConsumer from the Session to the Topic or Queue
         consumer = session.createConsumer(destination);

        running = true;
    }

    public void run() {
        while(running) {
            try {
                Message message = consumer.receive(1000);
                if (message != null) {
                    String text = ((TextMessage) message).getText();
                    System.out.println("Received: " + text);
                    alarmService.process(text);
                }
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
