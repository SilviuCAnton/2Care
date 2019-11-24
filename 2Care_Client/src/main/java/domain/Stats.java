package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Stats extends Entity<Integer> {
    private double steps;
    private String bloodPressure;
    private int cholesterol;
    private double weight;
    private int day;

    public Stats(Integer id, double steps, String bloodPressure, int cholesterol, double weight, int day) {
        super.setId(id);
        this.steps = steps;
        this.bloodPressure = bloodPressure;
        this.cholesterol = cholesterol;
        this.weight = weight;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    @Override
    public String toString() {
        return "steps=" + steps +
                ", bloodPressure='" + bloodPressure + '\'' +
                ", cholesterol=" + cholesterol +
                ", weight=" + weight;
    }
}
