package org.example.centralservice.model;

public class Measurement {
    private String sensorId;
    private int value;

    public Measurement(String sensorId, int value) {
        this.sensorId = sensorId;
        this.value = value;
    }

    public Measurement() {
    }

    public String getSensorId() {
        return sensorId;
    }

    public int getValue() {
        return value;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
