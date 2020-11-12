package io.beamtechnology.transport;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.beamtechnology.util.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Objects;

public abstract class AbstractBeamTransport implements BeamTransport  {

    protected final String customer;
    protected final String clientKey;
    protected String accessToken = null;
    protected final String baseUri;
    protected final String authUrl;
    protected final String testUrl;

    protected AbstractBeamTransport(final String customer, final String clientKey) {
        this.customer = customer;
        this.clientKey = clientKey;
        this.baseUri = makeBaseUri(customer);
        this.authUrl = makeAuthUrl(customer);
        this.testUrl = makeTestUrl(customer);
    }

    protected abstract String makeBaseUri(final String customer);

    protected abstract String makeAuthUrl(final String customer);

    protected abstract String makeTestUrl(final String customer);

    @Override
    public String[] makeHeaders() {
        // alternating list of header key-value pairs
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Authorization");
        headers.add("Bearer " + accessToken);
        headers.add("Content-Type");
        headers.add("application/json");
        return headers.toArray(new String[headers.size()]);
    }

    public void authenticate() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String data = "grant_type=client_credentials";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authUrl))
                .header("Authorization", "Basic " + clientKey)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        accessToken = extractFieldFromJson(response.body(), "access_token");
    }

    @Override
    public String getBaseUri() {
        return this.baseUri;
    }

    protected static String extractFieldFromJson(final String json, final String key) {
        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(json);

            while(!parser.isClosed()){
                JsonToken jsonToken = parser.nextToken();

                if(JsonToken.FIELD_NAME.equals(jsonToken)) {
                    String fieldName = parser.getCurrentName();
                    jsonToken = parser.nextToken();
                    if (key.equals(fieldName)) {
                        return parser.getValueAsString();
                    }
                }
            }
            return "";
        } catch (IOException e) {
            throw new RuntimeException("Unable to find field");
        }
    }

    public boolean verify() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authUrl))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return (response.statusCode() == 204);
    }

    @Override
    public HttpResponse<String> post(final String uriFragment, @NotNull Object object) throws IOException, InterruptedException {
        Objects.requireNonNull(object);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.getBaseUri() + uriFragment))
                .headers(this.makeHeaders())
                .POST(HttpRequest.BodyPublishers.ofString(JsonUtils.toJson(object)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    @Override
    public HttpResponse<String> get(String uriFragment) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.getBaseUri() + uriFragment))
                .headers(this.makeHeaders())
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    @Override
    public HttpResponse<String> patch(String uriFragment, @NotNull Object object, boolean suppressHistory) throws IOException, InterruptedException {
        Objects.requireNonNull(object);
        HttpClient client = HttpClient.newHttpClient();
        String url = this.getBaseUri() + uriFragment;
        if (suppressHistory) {
            url += "?history=false";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(this.makeHeaders())
                .method("PATCH", HttpRequest.BodyPublishers.ofString(JsonUtils.toJson(object)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    @Override
    public HttpResponse<String> put(String uriFragment, Object object) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String url = this.getBaseUri() + uriFragment;
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(this.makeHeaders());
        if (object == null) {
            requestBuilder = requestBuilder.PUT(HttpRequest.BodyPublishers.noBody());
        } else {
            requestBuilder = requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(JsonUtils.toJson(object)));
        }
        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

}
