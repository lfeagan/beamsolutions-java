package io.beamtechnology.ingestion;

import java.util.Date;

public class JsonError {

    public int status;
    public int code;
    public Date time = null; // ISO-8601 Date-Time Format
    public String message = null;
    public String moreInfo = null;
    public String traceId = null;

//    {
//        "status": 400,
//            "code": 1004,
//            "time": "2018-05-12T00:13:34.471Z",
//            "message": "Missing firstName",
//            "moreInfo": "https://beamtechnology.io",
//            "traceId": "4f4e85b0-5579-11e8-8eff-8aa0fc600898"
//    }

}
