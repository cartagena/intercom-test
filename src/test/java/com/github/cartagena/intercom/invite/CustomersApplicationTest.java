package com.github.cartagena.intercom.invite;

import com.github.cartagena.intercom.invite.geo.Coordinates;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.cartagena.intercom.invite.ApplicationConfiguration.newApplicationConfiguration;
import static com.github.cartagena.intercom.invite.geo.Coordinates.newCoordinates;
import static org.assertj.core.api.Assertions.assertThat;

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
                .maxDistance(50D)
                .officeLocation(officeLocation)
                .build();

        CustomersApplication app = new CustomersApplication(config, out);

        app.searchAndPrintNearbyCustomers();

        String output = baos.toString();

        System.out.print(output);

        assertThat(output)
                .contains(
                        "Wicklow 1\n" +
                        "Kildare 5");

    }


}