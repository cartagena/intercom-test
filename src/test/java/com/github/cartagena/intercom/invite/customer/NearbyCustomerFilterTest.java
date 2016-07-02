package com.github.cartagena.intercom.invite.customer;


import com.github.cartagena.intercom.invite.geo.Coordinates;
import org.junit.Before;
import org.junit.Test;

import static com.github.cartagena.intercom.invite.customer.Customer.newCustomer;
import static com.github.cartagena.intercom.invite.geo.Coordinates.newCoordinates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class NearbyCustomerFilterTest {

    private NearbyCustomerFilter filter;

    @Before
    public void setup() {
        Coordinates base = newCoordinates()
                .latitude(0.0)
                .longitude(0.0)
                .build();

        filter = new NearbyCustomerFilter(base, 100);
    }

    @Test
    public void shouldBeTrueForCustomerWithinTheDistance() {
        Customer customer = newCustomer()
                .latitude(0.1)
                .longitude(0.1)
                .build();

        boolean isNear = filter.test(customer);

        assertThat(isNear)
                .isTrue();
    }

    @Test
    public void shouldBeFalseForCustomerOutTheDistance() {
        Customer customer = newCustomer()
                .latitude(45)
                .longitude(45)
                .build();

        boolean isNear = filter.test(customer);

        assertThat(isNear)
                .isFalse();
    }

    @Test
    public void shouldThrowExceptionIfCustomerIsNull() {
        try {
            filter.test(null);
            fail("Expected exception for null customer.");
        } catch (Exception e) {
            assertThat(e)
                    .isInstanceOf(NullPointerException.class);
        }
    }

}