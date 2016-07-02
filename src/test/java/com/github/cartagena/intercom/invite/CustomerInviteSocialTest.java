package com.github.cartagena.intercom.invite;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerInviteSocialTest {

    public static final String USER_1_JSON = "{\"latitude\": \"0\", \"user_id\": 1, \"name\": \"John\", \"longitude\": \"0\"}";
    public static final String USER_2_JSON = "{\"latitude\": \"0\", \"user_id\": 2, \"name\": \"Andrew\", \"longitude\": \"0\"}";
    public static final String USER_3_JSON = "{\"latitude\": \"0\", \"user_id\": 3, \"name\": \"James\", \"longitude\": \"0\"}";


    @Test
    public void shouldOutputInvitedPeople() throws IOException {
        CustomerRepository repository = new CustomerRepository(createTempFileWithContent(USER_1_JSON, USER_3_JSON, USER_2_JSON));
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

    private Path createTempFileWithContent(String... lines) throws IOException {
        Path file = Files.createTempFile("customers", "json");
        Files.write(file, Arrays.asList(lines));

        return file;
    }

}
