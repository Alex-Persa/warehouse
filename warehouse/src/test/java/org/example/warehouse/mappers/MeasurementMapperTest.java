package org.example.warehouse.mappers;


import org.example.warehouse.model.Measurement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class MeasurementMapperTest {

    public MeasurementMapper toTest;

    @BeforeEach
    public void setup() {
        toTest = new MeasurementMapper();
    }

    @Test
    public void toMeasurementSuccess() {
        Measurement result = toTest.toMeasurement("sensor_id=t1; value=30");


        Assertions.assertEquals("t1", result.getSensorId());
        Assertions.assertEquals(30, result.getValue());
    }

    @Test
    public void toMeasurement_wrongSensorIdKey() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.toMeasurement("sensorId=t1; value=30"));

        Assertions.assertEquals("can't find sensor_id=", exception.getMessage());
    }

    @Test
    public void toMeasurement_wrongDelimiter() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.toMeasurement("sensor_id=t1;value=30"));

        Assertions.assertEquals("invalid delimiter. Expected value:; ", exception.getMessage());
    }

    @Test
    public void toMeasurement_wrongValueKey() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.toMeasurement("sensor_id=t1; temp=30"));

        Assertions.assertEquals("can't find value=", exception.getMessage());
    }

    @Test
    public void toMeasurement_wrongValue() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> toTest.toMeasurement("sensor_id=t1; value=30C"));

        Assertions.assertEquals("invalid value=30C", exception.getMessage());
    }
}
