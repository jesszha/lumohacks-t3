package DatasetObjects;

public class HRInterval {
    private HeartRate[] dataset;

    public HRInterval(HeartRate[] dataset) {
        this.dataset = dataset;
    }

    public HeartRate[] getDataset() {
        return dataset;
    }
}
