package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Stats extends Entity<Integer> {
    private double steps;
    private String bloodPressure;
    private int cholesterol;
    private double weight;
    private int week;

    public Stats(Integer id, double steps, String bloodPressure, int cholesterol, double weight) {
        super.setId(id);
        this.steps = steps;
        this.bloodPressure = bloodPressure;
        this.cholesterol = cholesterol;
        this.weight = weight;
        this.week = ThreadLocalRandom.current().nextInt(1, 7);
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
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
