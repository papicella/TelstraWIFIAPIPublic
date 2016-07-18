package pas.au.pivotal.pws.api.telstrawifi.beans;

public class WifiSpot
{
    private String address;
    private String state;
    private Double latitude;
    private Double longitude;

    public WifiSpot(String address, String state, Double latitude, Double longitude) {
        this.address = address;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public WifiSpot()
    {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "WifiSpot{" +
                "address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
