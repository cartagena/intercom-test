package com.github.cartagena.intercom.invite;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

import static java.util.Objects.isNull;

@Value
@Builder(builderMethodName = "newApplicationConfiguration")
public class ApplicationConfiguration {

    String customersFile;
    double minDistance;
    Coordinates officeLocation;

    public ApplicationConfiguration validate() {
        if(isNull(customersFile) || customersFile.isEmpty() || minDistance < 0 ||
                isNull(officeLocation) || !officeLocation.isValid()) {

            throw new IllegalArgumentException("Invalid configuration arguments.");
        }

        return this;
    }

}
