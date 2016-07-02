package com.github.cartagena.intercom.invite;

import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.String.format;

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
        repository.readAllCustomers()
                .stream()
                .filter(nearby())
                .sorted()
                .map(asString())
                .forEach(output::println);
    }

    private Predicate<Customer> nearby() {
        return c -> measurer.distanceBetween(config.getOfficeLocation(), c.getLocation()) <= config.getMinDistance();
    }

    private Function<Customer, String> asString() {
        return c -> format("%s %s", c.getName(), c.getUserId());
    }

}
