package DatasetObjects;

public class HeartRate {
    private String time;
    private Integer heart_rate;

    public HeartRate(String time, Integer heart_rate) {
        this.time = time;
        this.heart_rate = heart_rate;
    }

    public String getHRTime() {
        return time;
    }

    public Integer getHeartRate() {
        return heart_rate;
    }
}
