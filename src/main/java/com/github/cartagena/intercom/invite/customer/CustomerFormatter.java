package com.github.cartagena.intercom.invite.customer;

import java.util.function.Function;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class CustomerFormatter implements Function<Customer, String> {

    @Override
    public String apply(Customer customer) {
        requireNonNull(customer);

        return format("%s %s", customer.getName(), customer.getUserId());
    }
}
