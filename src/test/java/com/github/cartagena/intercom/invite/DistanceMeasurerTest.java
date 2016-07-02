package com.github.cartagena.intercom.invite;

import org.junit.Test;

import static com.github.cartagena.intercom.invite.Coordinates.newCoordinates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class DistanceMeasurerTest {

    private final DistanceMeasurer measurer = new DistanceMeasurer();

    @Test
    public void shouldReturnDistance() {
        Coordinates x = newCoordinates()
                .latitude(53.3381985)
                .longitude(-6.2592576)
                .build();

        Coordinates y = newCoordinates()
                .latitude(53.4239)
                .longitude(-7.9407)
                .build();

        double distance = measurer.distanceBetween(x, y);

        assertThat(distance)
                .isBetween(111.5, 1120.0);
    }

    @Test
    public void shouldThrowExceptionForInvalidCoordinate() {
        Coordinates x = newCoordinates()
                .latitude(53.3381985)
                .longitude(-6.2592576)
                .build();

        Coordinates y = newCoordinates()
                .latitude(53.4239)
                .longitude(-102)
                .build();

        try {
            measurer.distanceBetween(x, y);
            fail("Expected exception for invalid coordinate.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Invalid coordinates.");
        }

    }

    @Test
    public void shouldThrowExceptionForNullCoordinate() {
        Coordinates x = newCoordinates()
                .latitude(53.3381985)
                .longitude(-6.2592576)
                .build();

        try {
            measurer.distanceBetween(x, null);
            fail("Expected exception for null coordinate.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Invalid coordinates.");
        }
    }
}