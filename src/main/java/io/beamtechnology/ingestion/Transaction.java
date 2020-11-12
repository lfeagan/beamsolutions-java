package io.beamtechnology.ingestion;

import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;

public class Transaction {

    static String URI_FRAGMENT = "/model/v1/transactions";

    public String transactionId = null; // length <= 128
    public Date transactionCreated = null; // length <= 128
    public String accountId = null; // length <= 128
    public String payDirection = null; // length <= 128
    public String type = null; // length <= 128
    public String code = null; // length <= 128
    public String category = null; // length <= 128
    public String transactionModified = null; // length <= 128
    public String processor = null; // length <= 128
    public String exchangeRate = null; // NUMBER(19,4)
    public String referenceAmountValue = null; // NUMBER(19,4)
    public String invoiceNumber = null; // length <= 128
    public String correlation = null; // JSON Object with length <=4096
    public String status = null; // length <= 128, one of: created, pending, settled, failed, disbursed, completed
    public String paymentSourceId = null; // length <= 128
    public String partyId = null; // length <= 128
    public String description = null; // length <= 512
    public String custom = null; // length <= 128

    public GeoCode geoCode = null;
    public Amount amount = null;

    public static class Amount {
        public String value = null; // NUMBER(19,4)
        public String currency = null; // ISO 4217
    }

    public CreateResponse create(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.doPost(URI_FRAGMENT, this);
        CreateResponse createResponse = JsonUtils.fromJson(CreateResponse.class, response.body());
        return createResponse;
    }

    public static Transaction get(BeamTransport transport, String transactionId) throws IOException, InterruptedException {
        final String uriFragment = URI_FRAGMENT + "/" + transactionId;
        HttpResponse<String> response = transport.doGet(uriFragment);
        Transaction transaction = JsonUtils.fromJson(Transaction.class, response.body());
        return transaction;
    }

    public void updateStatus(BeamTransport transport) throws IOException, InterruptedException {
        // put /model/v1/transactions/1111?status=pending
        final String uriFragment = URI_FRAGMENT + "/" + transactionId + "?status=" + status;
        HttpResponse<String> response = transport.doPut(uriFragment, null);

    }
}
