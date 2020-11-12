package io.beamtechnology.investigator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.webhooks.Subscription;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;

public class MonitoringResult {

    private static final String TX_URI_FRAGMENT = "/investigator/v1/monitoring-results/transactions";
    private static final String PARTY_URI_FRAGMENT = "/investigator/v1/monitoring-results/party";

    public Date created = null; // ISO-8601 Date-Time Format
    public Integer score = null;
    public String[] labels = null;
    public String[] categories = null;
    public String[] actions = null;
    public String monitoringId = null;
    public String transactionId = null;
    public String caseId = null;
    public Alert[] alerts = null;

    public static class Alert {
        public String id = null;
        public String name = null;
        public String category = null;
        public String[] labels = null;
        public String[] actions = null;
    }

    public MonitoringResult[] getAllTx(BeamTransport transport, String transactionId) throws IOException, InterruptedException {
        final String uriFragment = TX_URI_FRAGMENT + "/" + transactionId;
        HttpResponse<String> response = transport.get(uriFragment);
        String jsonArray = response.body();
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MonitoringResult[] monitoringResults = om.readValue(jsonArray, MonitoringResult[].class);
        return monitoringResults;
    }

    public MonitoringResult[] getAllForParty(BeamTransport transport, String partyId) throws IOException, InterruptedException {
        final String uriFragment = TX_URI_FRAGMENT + "/" + partyId;
        HttpResponse<String> response = transport.get(uriFragment);
        String jsonArray = response.body();
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MonitoringResult[] monitoringResults = om.readValue(jsonArray, MonitoringResult[].class);
        return monitoringResults;
    }

}
