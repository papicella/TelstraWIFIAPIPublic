package pas.au.pivotal.pws.api.telstrawifi.controllers;

import pas.au.pivotal.pws.api.telstrawifi.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelstraWIFIApiRest
{
    private static String CONSUMER_KEY = "Pas6KQQtIynnrFYctot2flq3ZcOv49Go";
    private static String CONSUMER_SECRET = "bAElq7KQBhBFcyxy";

    private static final Logger log = LoggerFactory.getLogger(TelstraWIFIApiRest.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();

//    @RequestMapping(value = "/sample", method = RequestMethod.GET)
//    public String sampleOuput()
//    {
//        return Utils.getHotspots("", "", "", true);
//    }
//
//    @RequestMapping(value = "/findhotspots", method = RequestMethod.GET)
//    public String sendSms(@RequestParam(value="latitude", required=true) String lat,
//                          @RequestParam(value="longitude", required=true) String lon,
//                          @RequestParam(value="radius", required=true) String radius)
//    {
//        return Utils.getHotspots(lat, lon, radius, false);
//    }


}
