package io.beamtechnology.ingestion;

public class PaymentSource {

    private static String POST_URI_FRAGMENT = "/model/v1/payment-sources";

    public String paymentSourceId = null; // length <= 128
    public String paymentSourceCreated = null; // ISO-8601 Date-Time Format
    public String partyId = null; // length <= 128
    public String method = null; // length <= 128
    public String paymentSourceModified = null; // ISO-8601 Date-Time Format
    public String methodType = null; // length <= 128
    public String type = null; // length <= 128
    public String code = null; // length <= 128
    public String category = null; // length <= 128
    public String hash = null; // length <= 128
    public String issuingCountryIso = null; // ISO 3166-1
    public String bin = null; // length <= 6
    public String last4 = null; // length <= 4
    public String accountNumber = null; // length <= 128
    public String routingNumber = null; // length <= 128
    public String name = null; // length <= 255
    public String network = null; // length <= 128
    public String networkId = null; // length <= 128
    public String location = null; // length <= 255
    public String accountId = null; // length <= 128
    public String status = null; // length <= 128
    public GeoCode geoCode = null;

    public void create() {

    }
}
