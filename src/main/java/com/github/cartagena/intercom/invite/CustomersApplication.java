package com.github.cartagena.intercom.invite;

import com.github.cartagena.intercom.invite.customer.CustomerFormatter;
import com.github.cartagena.intercom.invite.customer.CustomerRepository;
import com.github.cartagena.intercom.invite.customer.NearbyCustomerFilter;
import com.github.cartagena.intercom.invite.geo.Coordinates;
import com.github.cartagena.intercom.invite.geo.DistanceMeasurer;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Properties;

import static com.github.cartagena.intercom.invite.ApplicationConfiguration.*;
import static com.github.cartagena.intercom.invite.geo.Coordinates.*;

public class CustomersApplication {

    private final CustomerRepository repository;
    private final DistanceMeasurer measurer;
    
    private final ApplicationConfiguration config;
    private final PrintStream output;

    public CustomersApplication(ApplicationConfiguration configuration, PrintStream output) {
        this.config = configuration;
        this.output = output;

        this.repository = new CustomerRepository(Paths.get(configuration.getCustomersFile()));
        this.measurer = new DistanceMeasurer();
    }

    public void searchAndPrintNearbyCustomers() {
        NearbyCustomerFilter nearby = new NearbyCustomerFilter(config.getOfficeLocation(), config.getMaxDistance());
        CustomerFormatter formatter = new CustomerFormatter();

        repository.readAllCustomers()
                .stream()
                .filter(nearby)
                .sorted()
                .map(formatter)
                .forEach(output::println);
    }

    public static void main(String... args) {
        CustomersApplication app = new CustomersApplication(loadConfiguration(), System.out);
        app.searchAndPrintNearbyCustomers();
    }

    private static ApplicationConfiguration loadConfiguration() {
        try {
            String defaultCustomersFile = ApplicationConfiguration.class.getResource("/customers.json").getFile();

            Properties properties = new Properties();
            properties.load(ApplicationConfiguration.class.getResourceAsStream("/config.properties"));

            double minDistance = Double.valueOf(properties.getProperty("min.distance"));

            double officeLat = Double.valueOf(properties.getProperty("office.lat"));
            double officeLng = Double.valueOf(properties.getProperty("office.lng"));

            Coordinates office = newCoordinates()
                    .latitude(officeLat)
                    .longitude(officeLng)
                    .build();

            return newApplicationConfiguration()
                    .maxDistance(minDistance)
                    .customersFile(properties.getProperty("customers.file", defaultCustomersFile))
                    .officeLocation(office)
                    .build()
                    .validate();

        } catch (Exception e) {
            throw new IllegalArgumentException("Configuration file missing arguments.", e);
        }
    }
}
