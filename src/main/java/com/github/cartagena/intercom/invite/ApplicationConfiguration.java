package com.github.cartagena.intercom.invite;

import com.github.cartagena.intercom.invite.geo.Coordinates;
import lombok.Builder;
import lombok.Value;

import static java.util.Objects.isNull;

@Value
@Builder(builderMethodName = "newApplicationConfiguration")
public class ApplicationConfiguration {

    String customersFile;
    double maxDistance;
    Coordinates officeLocation;

    public ApplicationConfiguration validate() {
        if(isNull(customersFile) || customersFile.isEmpty() || maxDistance < 0 ||
                isNull(officeLocation) || !officeLocation.isValid()) {

            throw new IllegalArgumentException("Invalid configuration arguments.");
        }

        return this;
    }

}
