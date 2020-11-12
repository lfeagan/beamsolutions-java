# A Simple Java Library for Accessing the Beam Solutions (now Jumio) REST API

This **very** simple library deals with the basics of authenticating with Beam Solutions AML API for creating, retrieving, and updating objects in their ingestion, investigation, and webhooks APIs. The ingestion API supports creating the four "TAPS" objects in Beam:
1. Transactions -- Money moving between accounts.
1. Accounts -- Stores of money.
1. Parties -- Owners of accounts.
1. Sources -- Payment sources, such as ACH, card, cash, marketplace, and P2P.