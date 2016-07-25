package pas.au.pivotal.pws.api.telstrawifi.controllers;

public class LatLonRadRequest {
    private String lat;
    private String lon;
    private String radius;

    public LatLonRadRequest() {
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
