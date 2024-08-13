package org.example.warehouse.model;

public class Measurement {
    private final String sensorId;
    private final int value;

    public Measurement(String name, Integer value) {
        this.sensorId = name;
        this.value = value;
    }

    public String getSensorId() {
        return sensorId;
    }

    public int getValue() {
        return value;
    }
}
