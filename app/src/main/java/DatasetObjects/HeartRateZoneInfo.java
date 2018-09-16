package DatasetObjects;

public class HeartRateZoneInfo {
    private String data_date;
    private String activity_type;
    private Integer max_hr;
    private Integer min_hr;

    public HeartRateZoneInfo() {
        this.data_date = "undefined date";
        this.activity_type = "undefined activity type";
        this.min_hr = -1;
        this.max_hr = -1;
    }

    public HeartRateZoneInfo(String data_date, String activity_type, Integer max_hr, Integer min_hr) {
        //this.username  = username;
        this.data_date = data_date;
        this.activity_type = activity_type;
        this.max_hr = max_hr;
        this.min_hr = min_hr;
    }

    public String getDate() {
        return this.data_date;
    }

    public String getActivityType() {
        return this.activity_type;
    }

    public Integer getMaxHr() {
        return this.max_hr;
    }

    public Integer getMinHr() {
        return this.min_hr;
    }
}
