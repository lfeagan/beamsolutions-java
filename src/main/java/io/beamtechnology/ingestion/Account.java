package io.beamtechnology.ingestion;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    public String accountId = null; // length <= 128
    public Date accountCreated = null; // ISO-8601 Date-Time Format
    public String[] partyIds = null; // REQUIRED; length <= 128 each
    public String type = null; // length <= 128
    public Date accountModified = null; // ISO-8601 Date-Time Format
    public String code = null; // length <= 128
    public String category = null; // length <= 128
    public String primaryPartyId = null; // length <= 128
    public String number = null; // length <= 128
    public String name = null; // length <= 128
    public Balance balance = null;

    private static String URI_FRAGMENT = "/model/v1/accounts";

    public static class Balance {
        public BigDecimal value = null; // NUMBER(19,4)
        public String currency = null; // ISO-4217
    }

    public String description = null; // length <= 512
    public String status = null; // length <= 36

    public CreateResponse create(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.doPost(URI_FRAGMENT, this);
        CreateResponse createResponse = JsonUtils.fromJson(CreateResponse.class, response.body());
        return createResponse;
    }

    public Account update(BeamTransport transport, boolean suppressHistory) throws IOException, InterruptedException {
        if (accountId == null) {
            throw new NullPointerException("accountId must not be null");
        }
        HttpResponse<String> response = transport.doUpdate(URI_FRAGMENT, this, suppressHistory);
        Account account = JsonUtils.fromJson(Account.class, response.body());
        return account;
    }

    public static Account get(BeamTransport transport, String accountId) throws IOException, InterruptedException {
        final String uriFragment = URI_FRAGMENT + "/" + accountId;
        HttpResponse<String> response = transport.doGet(uriFragment);
        Account account = JsonUtils.fromJson(Account.class, response.body());
        return account;
    }

}
