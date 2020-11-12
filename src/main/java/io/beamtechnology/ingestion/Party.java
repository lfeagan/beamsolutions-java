package io.beamtechnology.ingestion;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Date;

public class Party {
    public String partyId = null; // REQUIRED; length <= 128
    public Date partyCreated = null; // REQUIRED; ISO-8601 Format Date
    public String userName = null; // length <=255
    public String partyModified = null; // ISO-8601 Format Date
    public String group = null; // length <=128
    public String type = null; // length <=128
    public String code = null; // length <=128
    public String category = null; // length <=128
    public String role = null; // length <=255
    public String correlation = null; // JSON-format
    public String accountId = null; // length <=128
    public String merchantName = null; // length <=255
    public boolean business = false;
    public String occupation = null; // length <=255
    public String firstName = null; // length <=128
    public String middleName = null; // length <=128
    public String lastName = null; // length <=128
    public String fullName = null; // length <=384
    public String address = null; // length <=512
    public String city = null; // length <=255
    public String postal = null; // length <=12
    public String stateIso = null; // ISO 3166-2
    public String countryIso = null; // ISO 3166-1
    public String residenceCountryIso = null; // ISO 3166-1
    public String citizenshipCountryIso = null; // ISO 3166-1
    public String email = null; // length <=255
    public String landline = null; // length <=20
    public String mobile = null; // length <=20
    public String fax = null; // length <=20
    public String dob = null; // ISO-8601 Date Format
    public String gender = null; // length <=1
    public String[] socialMedia = null; // array <=1024
    public String verified = null; // length <=128
    public String status = null; // length <=128

    public GeoCode geoCode = null;

    static final String PARTY_URI_FRAGMENT = "/model/v1/parties";

    public static Party get(String partyId) {
        return null;
    }

    public CreateResponse create(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.doPost(PARTY_URI_FRAGMENT, this);
        CreateResponse createResponse = JsonUtils.fromJson(CreateResponse.class, response.body());
        return createResponse;
    }

    public static Party get(BeamTransport transport, String partyId) throws IOException, InterruptedException {
        if (partyId == null) {
            throw new NullPointerException("partyId must not be null");
        }
        final String uriFragment = PARTY_URI_FRAGMENT + "/" + partyId;
        HttpResponse<String> response = transport.doGet(uriFragment);
        Party party = JsonUtils.fromJson(Party.class, response.body());
        return party;
    }

    public Party update(BeamTransport transport, boolean suppressHistory) throws IOException, InterruptedException {
        if (partyId == null) {
            throw new NullPointerException("partyId must not be null");
        }
        String uriFragment = PARTY_URI_FRAGMENT + "/" + partyId;
        HttpResponse<String> response = transport.doUpdate(uriFragment, this, suppressHistory);
        Party party = JsonUtils.fromJson(Party.class, response.body());
        return party;
    }

    public static void main(String[] args) throws JsonProcessingException {
        Party p = new Party();
        p.partyId = "123";
        p.partyCreated = new Date();
        System.out.println(JsonUtils.toJson(p));

        // 2019-09-04T04:16:28.138Z
    }

}
