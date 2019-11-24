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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ListAdapter;
import proxy.UserAccountRepoProxy;

import java.util.List;

public class DoctorController {
    @FXML
    public ListView patientsListView;

    @FXML
    public TextField searchPatients;

    private ObservableList<Patient> userAccounts = FXCollections.observableArrayList();

    private UserAccountRepoProxy userAccountRepoProxy;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart<String,Number> barChart;

    public void setUserAccountRepoProxy(UserAccountRepoProxy userAccountRepoProxy) {
        this.userAccountRepoProxy = userAccountRepoProxy;
        barChart.setManaged(false);
        userAccounts.setAll(userAccountRepoProxy.findAllPatients());
    }

    public void initialize() {
        patientsListView.setItems(userAccounts);

        barChart.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data("austria", 57401.85));

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("2005");
        series3.getData().add(new XYChart.Data("austria", 45000.65));
        barChart.getData().addAll(series1, series2, series3);

        patientsListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Patient>() {
                    public void changed(ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) {
                        Patient selected = (Patient) patientsListView.getSelectionModel().getSelectedItem();
                        List<Stats> allStats = userAccountRepoProxy.getAllStatsFromLastWeek(selected.getId());
                        System.out.println("selection changed");
                        barChart.setManaged(true);
                        FadeInRight fadeInRight = new FadeInRight(barChart);
                        fadeInRight.play();
                    }
                });


    }

}
