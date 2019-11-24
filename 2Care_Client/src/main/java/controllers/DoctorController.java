package controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import domain.Patient;
import domain.Stats;
import domain.UserAccount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ListAdapter;
import proxy.UserAccountRepoProxy;

import java.util.ArrayList;
import java.util.List;

public class DoctorController {
    @FXML
    public ListView patientsListView;

    @FXML
    public TextField searchPatients;
    @FXML
    public LineChart barChartWeight;
    @FXML
    public CategoryAxis xAxisWeight;
    @FXML
    public NumberAxis yAxisWeight;

    private ObservableList<Patient> userAccounts = FXCollections.observableArrayList();

    private UserAccountRepoProxy userAccountRepoProxy;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String,Number> barChart;
    private List<XYChart.Series> allSeries = new ArrayList<>();
    private List<XYChart.Series> allSeriesWeight = new ArrayList<>();

    public void setUserAccountRepoProxy(UserAccountRepoProxy userAccountRepoProxy) {
        this.userAccountRepoProxy = userAccountRepoProxy;
        barChart.setManaged(false);
        barChartWeight.setManaged(false);
        userAccounts.setAll(userAccountRepoProxy.findAllPatients());
    }

    public void initialize() {
        patientsListView.setItems(userAccounts);

        barChart.setTitle("Steps Summary");
        xAxis.setLabel("Steps");
        yAxis.setLabel("Value");
        barChartWeight.setTitle("Weight Summary");
        xAxisWeight.setLabel("Weight");
        yAxisWeight.setLabel("Value");

        patientsListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Patient>() {
                    public void changed(ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) {
                        createStepsBarChart();
                        createWeightBarChart();
                    }
                });

    }

    private void createStepsBarChart() {
        barChart.getData().removeAll(allSeries);
        allSeries.clear();
        Patient selected = (Patient) patientsListView.getSelectionModel().getSelectedItem();
        List<Stats> allStats = userAccountRepoProxy.getAllStatsFromLastWeek(selected.getId());
        if(allStats.size()>0) {
            int lastday = allStats.get(0).getDay(), index = 0;
            XYChart.Series series = new XYChart.Series();
            series.setName(Integer.toString(lastday));
            barChart.getData().add(index++, series);
            allSeries.add(series);
            for (Stats s : allStats) {
                if (lastday != s.getDay()) {
                    series = new XYChart.Series();
                    series.setName(Integer.toString(s.getDay()));
                    barChart.getData().add(index++, series);
                    allSeries.add(series);
                }
                series.getData().add(new XYChart.Data("steps", s.getSteps()));
            }
            barChart.setManaged(true);
            FadeInRight fadeInRight = new FadeInRight(barChart);
            fadeInRight.play();
        }
    }
    private void createWeightBarChart() {
        barChartWeight.getData().removeAll(allSeriesWeight);
        allSeriesWeight.clear();
        Patient selected = (Patient) patientsListView.getSelectionModel().getSelectedItem();
        List<Stats> allStats = userAccountRepoProxy.getAllStatsFromLastWeek(selected.getId());
        if(allStats.size()>0) {
            int lastday = allStats.get(0).getDay(), index = 0;
            XYChart.Series series = new XYChart.Series();
            series.setName(Integer.toString(lastday));
            barChartWeight.getData().add(index++, series);
            allSeriesWeight.add(series);
            for (Stats s : allStats) {
                if (lastday != s.getDay()) {
                    series = new XYChart.Series();
                    series.setName(Integer.toString(s.getDay()));
                    barChartWeight.getData().add(index++, series);
                    allSeriesWeight.add(series);
                }
                series.getData().add(new XYChart.Data("weight", s.getWeight()));
            }
            barChartWeight.setManaged(true);
            FadeInRight fadeInRight = new FadeInRight(barChartWeight);
            fadeInRight.play();
        }
    }

}
