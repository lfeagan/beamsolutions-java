package io.beamtechnology.ingestion;

import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.transport.ExpressBeamTransport;

public class Example {

    public static void main(String[] args) {
        BeamTransport bt = new ExpressBeamTransport("aCustomer", "myClientKey");
        PaymentSource ps1 = new PaymentSource("aPaymentSourceId", "aPartyId");
        try {
            ps1.create(bt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
