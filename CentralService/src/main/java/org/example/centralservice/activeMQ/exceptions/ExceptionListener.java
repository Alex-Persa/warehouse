package org.example.centralservice.activeMQ.exceptions;

import javax.jms.JMSException;

public class ExceptionListener implements javax.jms.ExceptionListener {
    @Override
    public void onException(JMSException exception) {
        exception.printStackTrace(System.out);
    }
}
