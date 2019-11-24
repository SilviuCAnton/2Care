package controllers;

import animatefx.animation.FadeInLeft;
import domain.Patient;
import domain.UserAccount;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proxy.UserAccountRepoProxy;

import java.util.List;

public class DoctorController {
    @FXML
    public ListView patientsListView;
    @FXML
    public TextField searchPatients;
    private UserAccountRepoProxy userAccountRepoProxy;
    private ObservableList<UserAccount> patientObservableList =  FXCollections.observableArrayList();

    public void initialize(){
        FadeInLeft fadeList = new FadeInLeft(patientsListView);
        fadeList.play();
        patientsListView.setItems(patientObservableList);
    }

    public void setUserAccountRepoProxy(UserAccountRepoProxy userAccountRepoProxy) {
        this.userAccountRepoProxy = userAccountRepoProxy;
        List<UserAccount> all = userAccountRepoProxy.findAll();
        for (UserAccount s: all) {
            System.out.println(s);
        }
        patientObservableList.setAll(all);
    }
}
