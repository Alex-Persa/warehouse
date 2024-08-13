package org.example.warehouse.constants;

public class Constants {
    //TCP
    public static final int HUMIDITY_PORT = 3355;
    public static final int TEMPERATURE_PORT = 3344;

    //MQ
    public static final String MQ_BROKER_URL = "tcp://localhost:61616";
    public static final String HUMIDITY_QUEUE = "HumidityQueue";
    public static final String TEMPERATURE_QUEUE = "TemperatureQueue";
}
