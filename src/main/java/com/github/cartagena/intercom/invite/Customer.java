package com.github.cartagena.intercom.invite;

import lombok.Builder;
import lombok.Value;

import java.util.Comparator;

import static com.github.cartagena.intercom.invite.Coordinates.*;

@Value
@Builder(builderMethodName = "newCustomer")
public class Customer implements Comparable<Customer> {

    String userId;
    String name;
    double latitude;
    double longitude;

    public Coordinates getLocation() {
        return newCoordinates()
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    @Override
    public int compareTo(Customer o) {
        return userId.compareTo(o.getUserId());
    }

}
