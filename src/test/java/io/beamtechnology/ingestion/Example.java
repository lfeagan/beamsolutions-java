package io.beamtechnology.ingestion;

import io.beamtechnology.transport.BeamTransport;
import io.beamtechnology.transport.EnterpriseBeamTransport;
import io.beamtechnology.transport.ExpressBeamTransport;

public class Example {

    public static void main(String[] args) {
        // Supports both Express and Enterprise accounts, with a common BeamTransport interface
        BeamTransport express = new ExpressBeamTransport("myCustomerId", "myClientKey");
        BeamTransport enterprise = new EnterpriseBeamTransport("myCustomerId,", "myClientKey");

        // Required fields are constructor arguments
        PaymentSource ps1 = new PaymentSource("aPaymentSourceId", "aPartyId");
        // Other fields are set directly
        ps1.issuingCountryIso = "US";
        ps1.methodType = "visa";
        try {
            // to create the payment source, simply
            CreateResponse createResponse = ps1.create(express);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // A payment source can be retrieved via id
        try {
            PaymentSource ps2 = PaymentSource.get(express, "aPaymentSourceId");
            System.out.println(ps2.methodType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
