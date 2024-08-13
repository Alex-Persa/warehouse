package org.example.warehouse.utils.udp;

import org.example.warehouse.service.HumidityMQService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HumidityReceiverUDPTest {
    public HumidityReceiverUDP toTest;
    public HumidityMQService activeMQService;
    DatagramSocket socket;

    @BeforeEach
    public void setup() {
        toTest = Mockito.mock(HumidityReceiverUDP.class);
        activeMQService = Mockito.mock(HumidityMQService.class);
        socket = Mockito.mock(DatagramSocket.class);

        toTest.socket = socket;
        toTest.activeMQService = activeMQService;
    }

    @Test
    public void testRun() throws IOException {
        //given
        Mockito.doCallRealMethod().when(toTest).run();
        doReturn(true, true, false).when(toTest).isRunning();
        doNothing().when(toTest).retrieveAndSend(any());
        //when
        toTest.run();
        //than
        verify(toTest, times(2)).retrieveAndSend(any());
        verify(toTest, times(3)).isRunning();
        verify(socket).close();
        verify(activeMQService).closeSessions();
    }

    @Test
    public void testRetrieveAndSend() throws IOException {
        //given
        byte[] buf = {0, 0, 0};
        byte[] received = {1,2,3};
        String receivedStr = new String(received);
        doAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            DatagramPacket packet = ((DatagramPacket) args[0]);
            packet.setData(received);
            return null;
        }).when(socket).receive(any());
        doNothing().when(activeMQService).send(receivedStr);
        Mockito.doCallRealMethod().when(toTest).retrieveAndSend(buf);

        //when
        toTest.retrieveAndSend(buf);

        //then
        verify(socket).receive(any());
        verify(activeMQService).send(receivedStr);
    }

    @Test
    public void testIsRunning_beforeStart() {
        Mockito.doCallRealMethod().when(toTest).isRunning();
        boolean result = toTest.isRunning();
        assertFalse(result);
    }

    @Test
    public void testStopRun() {
        toTest.running = true;
        Mockito.doCallRealMethod().when(toTest).isRunning();
        assertTrue(toTest.isRunning());

        Mockito.doCallRealMethod().when(toTest).stopReceiver();
        toTest.stopReceiver();
        assertFalse(toTest.isRunning());
    }
}
