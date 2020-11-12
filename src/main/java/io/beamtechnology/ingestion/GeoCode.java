package io.beamtechnology.ingestion;

import java.math.BigDecimal;

public class GeoCode {
    public String ipAddress = null; // IPv4 or IPv6, length <= 50
    public String city = null; // length <=255
    public String postal = null; // length <=12
    public String stateIso = null; // ISO 3166-2
    public String countryIso = null; // ISO 3166-1
    public GeoPoint geoPoint = null;

    public static class GeoPoint {
        public BigDecimal latitude = null; // NUMBER(3,7)
        public BigDecimal longitude = null; // NUMBER(3,7)
    }
}

