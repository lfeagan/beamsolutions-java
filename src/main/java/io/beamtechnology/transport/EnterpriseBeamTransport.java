package io.beamtechnology.transport;

public class EnterpriseBeamTransport extends AbstractBeamTransport {

    public EnterpriseBeamTransport(String customer, String clientKey) {
        super(customer, clientKey);
    }

    @Override
    protected String makeBaseUri(final String customer) {
        return "https://" + customer + ".api.beamtechnology.com:8888";
    }

    @Override
    protected String makeAuthUrl(final String customer) {
        return "https://" + customer + ".api.beamtechnology.com:8888/token";
    }

    @Override
    protected String makeTestUrl(final String customer) {
        return "https://" + customer + ".api.beamtechnology.com:8888/system/v1/test";
    }

}
