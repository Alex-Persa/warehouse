package org.example.warehouse;

import org.example.warehouse.exception.ActiveMQException;
import org.example.warehouse.utils.udp.HumidityReceiverUDP;
import org.example.warehouse.utils.udp.TemperatureReceiverUDP;

import java.net.SocketException;

/**
 * C:\Program Files (x86)\Nmap>
 * echo sensor_id=t1; value=30 | ncat -u -w1 127.0.0.1 3344
 * echo sensor_id=h1; value=40 | ncat -u -w1 127.0.0.1 3355
 *
 * 2) start MQ -> C:\Users\besta\Downloads\apache-activemq-6.1.3-bin\apache-activemq-6.1.3\bin\win64\activemq.bat (pre-req java_home)
 *  - reference1: https://github.com/Nurislom373/MessageBrokers/tree/master
 *  - reference2: https://activemq.apache.org/components/classic/documentation/hello-world
 *
 * Check queues status -> http://localhost:8161/admin/queues.jsp
 *
 */
public class Main {

    public static void main(String[] args) throws SocketException, ActiveMQException {
        new HumidityReceiverUDP().start();
        new TemperatureReceiverUDP().start();
    }
}