package DatasetObjects;

public class UserInformation {
    private String username;
    private Integer resting_hr;
    private HeartRateZoneInfo[] zone_information;

    public UserInformation(String username, Integer resting_hr, HeartRateZoneInfo[] zone_information) {
        this.username = username;
        this.resting_hr = resting_hr;
        this.zone_information = zone_information;
    }

    public String getUsername() {
        return this.username;
    }

    public Integer getRestingHr() {
        return this.resting_hr;
    }

    public HeartRateZoneInfo[] getZoneInformation() {
        return zone_information;
    }
}
