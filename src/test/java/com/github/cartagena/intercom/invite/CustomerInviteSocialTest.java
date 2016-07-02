package com.github.cartagena.intercom.invite;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerInviteSocialTest {

    @Test
    public void shouldOutputInvitedPeople() {
        CustomerRepository repository = new CustomerRepository();
        DistanceFunction distanceFunction = new DistanceFunction();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);


        List<Customer> customers = repository.readAllCustomers();

        customers.stream()
                .filter(distanceFunction)
                .sorted(Customer.getIdSorter())
                .forEach(out::println);


        String output = baos.toString();
        assertThat(output)
                .contains("John 1",
                        "Andrew 2",
                        "James 3");

    }

}
