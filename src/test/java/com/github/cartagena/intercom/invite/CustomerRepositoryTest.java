package com.github.cartagena.intercom.invite;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.github.cartagena.intercom.invite.Customer.newCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class CustomerRepositoryTest {

    public static final String USER_1_JSON = "{\"latitude\": \"0\", \"user_id\": 1, \"name\": \"John\", \"longitude\": \"0\"}";
    public static final String USER_2_JSON = "{\"latitude\": \"0\", \"user_id\": 2, \"name\": \"Andrew\", \"longitude\": \"0\"}";

    @Test
    public void shouldReturnAllCustomers() throws IOException {
        Path tmpFile = createTempFileWithContent(USER_1_JSON, USER_2_JSON);

        CustomerRepository repository = new CustomerRepository(tmpFile);

        Customer customer1 = newCustomer()
                .userId("1")
                .name("John")
                .latitude(0)
                .longitude(0)
                .build();

        Customer customer2 = newCustomer()
                .userId("2")
                .name("Andrew")
                .latitude(0)
                .longitude(0)
                .build();

        List<Customer> customers = repository.readAllCustomers();

        assertThat(customers)
                .hasSize(2)
                .containsOnly(customer1, customer2);
    }

    @Test
    public void shouldReturnEmptyListIfNoCustomers() throws IOException {
        Path tmpFile = createTempFileWithContent();
        CustomerRepository repository = new CustomerRepository(tmpFile);

        List<Customer> customers = repository.readAllCustomers();

        assertThat(customers)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void shouldThrowExceptionIfFileIsInvalid() throws IOException {
        Path tmpFile = createTempFileWithContent(
                USER_1_JSON,
                "I am an invalid JSON");
        CustomerRepository repository = new CustomerRepository(tmpFile);

        try {
            repository.readAllCustomers();
            fail("Expecting exception when customers file has an invalid line.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(InvalidCustomersFileException.class)
                    .hasMessageContaining("contains an invalid JSON line");
        }

    }

    @Test
    public void shouldThrowExceptionIfFileDoesNotExists() throws IOException {
        Path tmpFile = Paths.get("invald_file");
        CustomerRepository repository = new CustomerRepository(tmpFile);

        try {
            repository.readAllCustomers();
            fail("Expecting exception when customers file does not exists.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(InvalidCustomersFileException.class)
                    .hasMessageContaining("File with customers does not exists");
        }
    }

    @Test
    public void shouldThrowExceptionIfPathIsNull() throws IOException {
        try {
            new CustomerRepository(null);
            fail("Expecting exception when customers file does not exists.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Filepath of customers file can't be null.");
        }
    }

    @Test
    public void shouldIgnoreBlankLines() throws IOException {
        Path tmpFile = createTempFileWithContent(USER_1_JSON, "", USER_2_JSON);

        CustomerRepository repository = new CustomerRepository(tmpFile);

        Customer customer1 = newCustomer()
                .userId("1")
                .name("John")
                .latitude(0)
                .longitude(0)
                .build();

        Customer customer2 = newCustomer()
                .userId("2")
                .name("Andrew")
                .latitude(0)
                .longitude(0)
                .build();

        List<Customer> customers = repository.readAllCustomers();

        assertThat(customers)
                .hasSize(2)
                .containsOnly(customer1, customer2);
    }

    @Test
    public void shouldIgnoreDuplicatedLines() throws IOException {
        Path tmpFile = createTempFileWithContent(USER_1_JSON, USER_1_JSON);

        CustomerRepository repository = new CustomerRepository(tmpFile);

        Customer customer1 = newCustomer()
                .userId("1")
                .name("John")
                .latitude(0)
                .longitude(0)
                .build();

        List<Customer> customers = repository.readAllCustomers();

        assertThat(customers)
                .hasSize(1)
                .containsOnly(customer1);
    }

    private Path createTempFileWithContent(String... lines) throws IOException {
        Path file = Files.createTempFile("customers", "json");
        Files.write(file, Arrays.asList(lines));

        return file;
    }
}
