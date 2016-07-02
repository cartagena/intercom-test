package com.github.cartagena.intercom.invite;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.cartagena.intercom.invite.Coordinates.newCoordinates;
import static com.github.cartagena.intercom.invite.ApplicationConfiguration.newApplicationConfiguration;
import static org.assertj.core.api.Assertions.*;

public class CustomersApplicationTest {

    @Test
    public void shouldPrintNearbyCustomers() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);

        Coordinates officeLocation = newCoordinates()
                .latitude(53.3381985)
                .longitude(-6.2592576)
                .build();

        ApplicationConfiguration config = newApplicationConfiguration()
                .customersFile("src/test/resources/customers.json")
                .minDistance(50D)
                .officeLocation(officeLocation)
                .build();

        CustomersApplication app = new CustomersApplication(config, out);

        app.searchAndPrintNearbyCustomers();

        String output = baos.toString();
        assertThat(output)
                .contains(
                        "Wicklow 1",
                        "Kildare 5");

    }


}