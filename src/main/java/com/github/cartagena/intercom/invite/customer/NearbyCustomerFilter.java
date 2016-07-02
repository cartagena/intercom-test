package com.github.cartagena.intercom.invite.customer;

import com.github.cartagena.intercom.invite.geo.Coordinates;
import com.github.cartagena.intercom.invite.geo.DistanceMeasurer;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class NearbyCustomerFilter implements Predicate<Customer> {

    private final Coordinates base;
    private final double maxDistance;
    private final DistanceMeasurer measurer;

    public NearbyCustomerFilter(Coordinates base, double maxDistance) {
        this.base = base;
        this.maxDistance = maxDistance;

        this.measurer = new DistanceMeasurer();
    }

    @Override
    public boolean test(Customer customer) {
        requireNonNull(customer);

        return measurer.distanceBetween(base, customer.getLocation()) <= maxDistance;
    }
}
