package com.github.cartagena.intercom.invite.geo;

import static java.lang.Math.*;
import static java.util.Objects.isNull;

public class DistanceMeasurer {

    private static final double EARTH_RADIOUS = 6371.0;

    public double distanceBetween(Coordinates x, Coordinates y) {
        if( (isNull(x) || !x.isValid()) || (isNull(y) || !y.isValid())  ) {
            throw new IllegalArgumentException("Invalid coordinates.");
        }

        double latX = toRadians(x.getLatitude());
        double lngX = toRadians(x.getLongitude());

        double latY = toRadians(y.getLatitude());
        double lngY = toRadians(y.getLongitude());

        return EARTH_RADIOUS * acos(sin(latX) * sin(latY) + cos(latX) * cos(latY) * cos(lngX - lngY));
    }
}
