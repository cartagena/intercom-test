package com.github.cartagena.intercom.invite.geo;


import lombok.Builder;
import lombok.Value;

import static java.lang.Math.abs;

@Value
@Builder(builderMethodName = "newCoordinates")
public class Coordinates {

    double latitude;
    double longitude;

    public boolean isValid() {
        return abs(latitude) <= 90 && abs(longitude) <= 90;
    }

}
