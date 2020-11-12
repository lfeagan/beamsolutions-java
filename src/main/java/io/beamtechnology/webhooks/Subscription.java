package io.beamtechnology.webhooks;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Subscription {

    public String id = null; // UUID
    public String created = null; // ISO-8601 Date-Time Format
    public String modified = null; // ISO-8601 Date-Time Format
    public String webhookUrl = null; // REQUIRED
    public String[] events = null; // REQUIRED; array of event types
    public String status = null; // "active" "inactive"

    static String URI_FRAGMENT = "/subscriptions";

    public Subscription create(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.doPost(URI_FRAGMENT, this);
        Subscription subscription = JsonUtils.fromJson(Subscription.class, response.body());
        return subscription;
    }

    public Subscription get(BeamTransport transport, String subscriptionId) throws IOException, InterruptedException {
        final String uriFragment = URI_FRAGMENT + "/" + subscriptionId;
        HttpResponse<String> response = transport.doGet(uriFragment);
        Subscription subscription = JsonUtils.fromJson(Subscription.class, response.body());
        return subscription;
    }

    public Subscription[] getAll(BeamTransport transport) throws IOException, InterruptedException {
        final String uriFragment = URI_FRAGMENT;
        HttpResponse<String> response = transport.doGet(uriFragment);
        String jsonArray = response.body();
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Subscription[] subscriptions = om.readValue(jsonArray, Subscription[].class);
        return subscriptions;
    }

    public Subscription update(BeamTransport transport, boolean suppressHistory) throws IOException, InterruptedException {
        if (id == null) {
            throw new NullPointerException("id must not be null");
        }
        String uriFragment = URI_FRAGMENT + "/" + id;
        HttpResponse<String> response = transport.doUpdate(uriFragment, this, suppressHistory);
        Subscription subscription = JsonUtils.fromJson(Subscription.class, response.body());
        return subscription;
    }
}
