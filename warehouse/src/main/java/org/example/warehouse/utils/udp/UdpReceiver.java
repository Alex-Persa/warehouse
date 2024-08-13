package org.example.warehouse.utils.udp;

import org.example.warehouse.exception.ActiveMQException;
import org.example.warehouse.service.ActiveMQService;
import org.example.warehouse.service.HumidityMQService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public abstract class UdpReceiver extends Thread {

    DatagramSocket socket;
    ActiveMQService activeMQService;
    boolean running;


    public void run() {
        System.out.println("HumidityReceiverUDP started");
        running = true;
        byte[] buf = new byte[256];

        while (isRunning()) {
            retrieveAndSend(buf);
        }
        socket.close();
        System.out.println("HumidityReceiverUDP stopped");
        activeMQService.closeSessions();
    }

    void retrieveAndSend(byte[] buf) {
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("received: " + received);
        activeMQService.send(received);
    }

    boolean isRunning() {
        return running;
    }

    public void stopReceiver() {
        running = false;
    }


    DatagramSocket getDatagramSocket(int port) throws SocketException {
        return new DatagramSocket(port);
    }

}
