package domain;

public class Stats {
    private double steps;
    private String bloodPressure;
    private int cholesterol;
    private double weight;

    public Stats(double steps, String bloodPressure, int cholesterol, double weight) {
        this.steps = steps;
        this.bloodPressure = bloodPressure;
        this.cholesterol = cholesterol;
        this.weight = weight;
    }

    public double getSteps() {
        return steps;
    }

    public void setSteps(double steps) {
        this.steps = steps;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
