package io.beamtechnology.transport;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface BeamTransport {
    public String[] makeHeaders();

    String getBaseUri();

    HttpResponse<String> post(String uriFragment, Object object) throws IOException, InterruptedException;

    HttpResponse<String> get(String uriFragment) throws IOException, InterruptedException;

    HttpResponse<String> patch(String uriFragment, Object object, boolean suppressHistory) throws IOException, InterruptedException;

    HttpResponse<String> put(String uriFragment, Object object) throws IOException, InterruptedException;
}
