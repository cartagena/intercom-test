package com.github.cartagena.intercom.invite;

import lombok.Builder;
import lombok.Value;

import java.util.Comparator;

import static com.github.cartagena.intercom.invite.Coordinates.*;

@Value
@Builder(builderMethodName = "newCustomer")
public class Customer {

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

    public static Comparator<Customer> getIdSorter() {
        return (c1, c2) -> c1.getUserId().compareTo(c2.getUserId());
    }
}
