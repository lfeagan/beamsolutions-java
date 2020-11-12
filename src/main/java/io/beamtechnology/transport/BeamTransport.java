package io.beamtechnology.transport;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface BeamTransport {
    public String[] makeHeaders();

    String getBaseUri();

    HttpResponse<String> doPost(String uriFragment, Object object) throws IOException, InterruptedException;

    HttpResponse<String> doGet(String uriFragment) throws IOException, InterruptedException;

    HttpResponse<String> doUpdate(String uriFragment, Object object, boolean suppressHistory) throws IOException, InterruptedException;

    HttpResponse<String> doPut(String uriFragment, Object object) throws IOException, InterruptedException;
}
