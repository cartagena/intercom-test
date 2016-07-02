package com.github.cartagena.intercom.invite;

import lombok.Builder;
import lombok.Value;

import java.util.Comparator;

@Value
@Builder(builderMethodName = "newCustomer")
public class Customer {

    String userId;
    String name;
    double latitude;
    double longitude;

    public static Comparator<? super Customer> getIdSorter() {
        return null;
    }
}
