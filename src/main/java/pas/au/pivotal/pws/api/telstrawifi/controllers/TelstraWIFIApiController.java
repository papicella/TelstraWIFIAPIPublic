package pas.au.pivotal.pws.api.telstrawifi.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pas.au.pivotal.pws.api.telstrawifi.Utils;
import pas.au.pivotal.pws.api.telstrawifi.beans.WifiSpot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TelstraWIFIApiController
{

    private static final Logger log = LoggerFactory.getLogger(TelstraWIFIApiController.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showSearchPage()
    {
        return "findhotspots";
    }

    @RequestMapping(value="/searchHotspots", method = RequestMethod.POST)
    public String searchGrants
            (@RequestParam(value="lat") String lat,
             @RequestParam(value="lon") String lon,
             @RequestParam(value="radius") String radius,
             Model model)
    {
        List<WifiSpot> hotspots = new ArrayList<WifiSpot>();

        String jsonString = Utils.getHotspots(lat, lon, radius, false);

        List<Object> jsonList = parser.parseList(jsonString);

        for (Object item: jsonList)
        {
            Map m = (Map) item;
            hotspots.add(new WifiSpot
                    ((String)m.get("address"), (String)m.get("state"), (Double) m.get("lat"), (Double) m.get("long")));
        }

        log.info("WIFI hotspots found = " + hotspots.size());

        model.addAttribute("hotspots", hotspots);
        model.addAttribute("hotspotscount", hotspots.size());

        return "findhotspots";
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/hotspots/search",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<HotSpotAPIResponse> fetchLinks(@RequestBody LatLonRadRequest latLonRadRequest) throws IOException {
        return Utils.getHotspots(latLonRadRequest.getLat(), latLonRadRequest.getLon(), latLonRadRequest.getRadius());
    }
}
