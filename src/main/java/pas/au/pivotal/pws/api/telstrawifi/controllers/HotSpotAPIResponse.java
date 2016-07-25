package pas.au.pivotal.pws.api.telstrawifi.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotSpotAPIResponse {
    private String lat;

    @JsonProperty("long")
    private String lon;

    private String address;
    private String city;
    private String state;

    public HotSpotAPIResponse(){}

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
