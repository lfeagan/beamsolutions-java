# A Simple Java Library for Accessing the Beam Solutions (now Jumio) REST API

This **very** simple library deals with the basics of authenticating with Beam Solutions AML API for creating, retrieving, and updating objects in their ingestion, investigation, and webhooks APIs. The ingestion API supports creating the four "TAPS" objects in Beam:
1. Transactions -- Money moving between accounts.
1. Accounts -- Stores of money.
1. Parties -- Owners of accounts.
1. Sources -- Payment sources, such as ACH, card, cash, marketplace, and P2P.

```java
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
```
