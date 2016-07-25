package pas.au.pivotal.pws.api.telstrawifi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import pas.au.pivotal.pws.api.telstrawifi.controllers.HotSpotAPIResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils
{
    private static String CONSUMER_KEY = "Pas6KQQtIynnrFYctot2flq3ZcOv49Go";
    private static String CONSUMER_SECRET = "bAElq7KQBhBFcyxy";

    private static final Logger log = LoggerFactory.getLogger(Utils.class);
    private static final JsonParser parser = JsonParserFactory.getJsonParser();

    public static String getHotspots (String lat, String lon, String radius, boolean isSample)
    {
        String hotspotResponse = "";

        RestTemplate restTemplate = new RestTemplate();

        String jsonResponse =
                restTemplate.getForObject
                        (String.format
                                ("https://api.telstra.com/v1/oauth/token?client_id=%s&client_secret=%s&grant_type=client_credentials&scope=WIFI",
                                        CONSUMER_KEY,
                                        CONSUMER_SECRET), String.class);

        Map<String, Object> jsonMap = parser.parseMap(jsonResponse);

        String accessToken = (String) jsonMap.get("access_token");

        log.info("Access Token for Telstra WIFI API is - " + accessToken);

        String url = "https://api.telstra.com/v1/wifi/hotspots?lat=%s&long=%s&radius=%s";

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", String.format("Bearer %s", accessToken));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        HttpEntity<String> response = null;

        if (isSample)
        {
            response =
                    restTemplate.exchange(String.format
                          //  (url, "-37.818496", "144.953240", "100"), HttpMethod.GET, entity, String.class);
                            (url, lat, lon, radius), HttpMethod.GET, entity, String.class);
        }
        else
        {
            response =
                    restTemplate.exchange(String.format
                            (url, lat, lon, radius), HttpMethod.GET, entity, String.class);
        }

        hotspotResponse = response.getBody();

        return hotspotResponse;
    }

    public static List<HotSpotAPIResponse> getHotspots (String lat, String lon, String radius) throws IOException {
        String hotspotResponse = "";

        RestTemplate restTemplate = new RestTemplate();

        String jsonResponse =
                restTemplate.getForObject
                        (String.format
                                ("https://api.telstra.com/v1/oauth/token?client_id=%s&client_secret=%s&grant_type=client_credentials&scope=WIFI",
                                        CONSUMER_KEY,
                                        CONSUMER_SECRET), String.class);

        Map<String, Object> jsonMap = parser.parseMap(jsonResponse);

        String accessToken = (String) jsonMap.get("access_token");

        log.info("Access Token for Telstra WIFI API is - " + accessToken);

        String url = "https://api.telstra.com/v1/wifi/hotspots?lat=%s&long=%s&radius=%s";

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", String.format("Bearer %s", accessToken));

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        HttpEntity<String> response = null;
        response = restTemplate.exchange(String.format
                            (url, lat, lon, radius), HttpMethod.GET, entity, String.class);

        hotspotResponse = response.getBody();

        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        List<HotSpotAPIResponse> hotSpotList = mapper.readValue(hotspotResponse,
                mapper.getTypeFactory().constructCollectionType(List.class, HotSpotAPIResponse.class));

        return hotSpotList;
    }

}
