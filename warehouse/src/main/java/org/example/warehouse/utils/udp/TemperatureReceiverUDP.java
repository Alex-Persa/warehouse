package org.example.warehouse.utils.udp;

import org.example.warehouse.exception.ActiveMQException;
import org.example.warehouse.service.TemperatureMQService;

import java.net.SocketException;

import static org.example.warehouse.constants.Constants.TEMPERATURE_PORT;

public class TemperatureReceiverUDP extends UdpReceiver {

    public TemperatureReceiverUDP() throws SocketException, ActiveMQException {
        socket = getDatagramSocket(TEMPERATURE_PORT);
        activeMQService = getTemperatureMQService();
    }

    TemperatureMQService getTemperatureMQService() throws ActiveMQException {
        return new TemperatureMQService();
    }
}
