package io.beamtechnology.ingestion;

import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;

public class PaymentSource {

    private static String URI_FRAGMENT = "/model/v1/payment-sources";

    public String paymentSourceId = null; // REQUIRED; length <= 128
    public Date paymentSourceCreated = null; // REQUIRED; ISO-8601 Date-Time Format
    public String partyId = null; // REQUIRED; length <= 128
    public String method = null; // length <= 128
    public Date paymentSourceModified = null; // ISO-8601 Date-Time Format
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

    // default no-args for use by serialization
    PaymentSource() {}

    public PaymentSource(final String paymentSourceId, final String partyId) {
        this.paymentSourceId = paymentSourceId;
        this.partyId = null;
        this.paymentSourceCreated = new Date();
    }

    public CreateResponse create(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.post(URI_FRAGMENT, this);
        CreateResponse createResponse = JsonUtils.fromJson(CreateResponse.class, response.body());
        return createResponse;
    }

    public static PaymentSource get(BeamTransport transport, String paymentSourceId) throws IOException, InterruptedException {
        final String uriFragment = URI_FRAGMENT + "/" + paymentSourceId;
        HttpResponse<String> response = transport.get(uriFragment);
        PaymentSource paymentSource = JsonUtils.fromJson(PaymentSource.class, response.body());
        return paymentSource;
    }

    public PaymentSource update(BeamTransport transport, boolean suppressHistory) throws IOException, InterruptedException {
        if (paymentSourceId == null) {
            throw new NullPointerException("paymentSourceId must not be null");
        }
        HttpResponse<String> response = transport.patch(URI_FRAGMENT, this, suppressHistory);
        PaymentSource paymentSource = JsonUtils.fromJson(PaymentSource.class, response.body());
        return paymentSource;
    }

}
