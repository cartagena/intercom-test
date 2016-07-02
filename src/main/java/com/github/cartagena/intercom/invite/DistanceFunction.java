package com.github.cartagena.intercom.invite;

import java.util.function.Predicate;

public class DistanceFunction implements Predicate<Customer> {

    @Override
    public boolean test(Customer customer) {
        return false;
    }

    @Override
    public Predicate<Customer> and(Predicate<? super Customer> other) {
        return null;
    }

    @Override
    public Predicate<Customer> negate() {
        return null;
    }

    @Override
    public Predicate<Customer> or(Predicate<? super Customer> other) {
        return null;
    }
}
