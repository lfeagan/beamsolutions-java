package io.beamtechnology.webhooks;

import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.util.JsonUtils;

import java.io.IOException;
import java.net.http.HttpResponse;

public class Settings {

    public static String URI_FRAGMENT = "/webhooks/v1/settings";

    public String signatureKey = null;

    public static Settings getSettings(BeamTransport transport) throws IOException, InterruptedException {
        HttpResponse<String> response = transport.get(URI_FRAGMENT);
        Settings settings = JsonUtils.fromJson(Settings.class, response.body());
        return settings;
    }

}
