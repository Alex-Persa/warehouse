package org.example.warehouse.utils.udp;

import org.example.warehouse.exception.ActiveMQException;
import org.example.warehouse.service.HumidityMQService;

import java.net.SocketException;

import static org.example.warehouse.constants.Constants.HUMIDITY_PORT;

public class HumidityReceiverUDP extends UdpReceiver {

    public HumidityReceiverUDP() throws SocketException, ActiveMQException {
        socket = getDatagramSocket(HUMIDITY_PORT);
        activeMQService  = getHumidityMQService();
    }

    HumidityMQService getHumidityMQService() throws ActiveMQException {
        return new HumidityMQService();
    }
}
