package org.example.warehouse.mappers;

import org.example.warehouse.model.Measurement;

public class MeasurementMapper {

    public static final String SENSOR_ID = "sensor_id=";
    public static final String DELIMITER = "; ";
    public static final String VALUE = "value=";

    public Measurement toMeasurement(String message) {
        String sensorName;
        int value;

        if (!message.startsWith(SENSOR_ID)) {
            throw new IllegalArgumentException("can't find " + SENSOR_ID);
        }

        message = message.substring(SENSOR_ID.length());

        int delimiterIndex = message.indexOf(DELIMITER);

        if (delimiterIndex < 1) {
            throw new IllegalArgumentException("invalid delimiter. Expected value:" + DELIMITER);
        }

        sensorName = message.substring(0, delimiterIndex);

        message = message.substring(delimiterIndex + DELIMITER.length());
        if (!message.startsWith(VALUE)) {
            throw new IllegalArgumentException("can't find value=");
        }

        message = message.substring(VALUE.length()).trim();

        try {
            value = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid value=" + message);
        }

        return new Measurement(sensorName, value);
    }
}
