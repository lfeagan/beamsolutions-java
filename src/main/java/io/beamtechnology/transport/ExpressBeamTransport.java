package io.beamtechnology.transport;

public class ExpressBeamTransport extends AbstractBeamTransport {

    static String BEAM_API_PATH="https://express.api.beamtechnology.com:8888";

    public ExpressBeamTransport(String customer, String clientKey) {
        super(customer, clientKey);
    }

    @Override
    protected String makeBaseUri(final String customer) {
        return BEAM_API_PATH + "/" + customer;
    }

    @Override
    protected String makeAuthUrl(final String customer) {
        return BEAM_API_PATH + "/token";
    }

    @Override
    protected String makeTestUrl(final String customer) {
       return BEAM_API_PATH + "/" + customer +"/system/v1/test";
    }

}
