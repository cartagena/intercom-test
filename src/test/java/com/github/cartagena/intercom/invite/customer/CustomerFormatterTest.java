package com.github.cartagena.intercom.invite.customer;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CustomerFormatterTest {

    private final CustomerFormatter formatter = new CustomerFormatter();

    @Test
    public void shouldPrintNameAndId() {
        Customer customer = Customer.newCustomer()
                .name("Test")
                .userId(1)
                .build();

        String output = formatter.apply(customer);

        assertThat(output)
                .isEqualTo("Test 1");
    }

    @Test
    public void shouldThrowExceptioWithNullArgument() {
        try {
            formatter.apply(null);
            fail("Expected exception when argument is null.");
        } catch(Exception e) {
            assertThat(e)
                    .isInstanceOf(NullPointerException.class);
        }
    }

}