package com.github.cartagena.intercom.invite.customer;

import com.github.cartagena.intercom.invite.geo.Coordinates;
import lombok.Builder;
import lombok.Value;

import static com.github.cartagena.intercom.invite.geo.Coordinates.newCoordinates;

@Value
@Builder(builderMethodName = "newCustomer")
public class Customer implements Comparable<Customer> {

    int userId;
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
        return userId - o.getUserId();
    }

}
