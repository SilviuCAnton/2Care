package controllers;

import animatefx.animation.FadeInLeft;
import domain.Patient;
import domain.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ListAdapter;
import proxy.UserAccountRepoProxy;

public class DoctorController {
    @FXML
    public ListView patientsListView;
    @FXML
    public ListView subscribedListView;
    @FXML
    public TextField searchPatients;

    private ObservableList<Patient> userAccounts = FXCollections.observableArrayList();

    private UserAccountRepoProxy userAccountRepoProxy;

    public void setUserAccountRepoProxy(UserAccountRepoProxy userAccountRepoProxy) {
        this.userAccountRepoProxy = userAccountRepoProxy;
        userAccounts.setAll(userAccountRepoProxy.findAllPatients());
    }

    public void initialize() {
        patientsListView.setItems(userAccounts);
        patientsListView.selectionModelProperty().addListener((x,y,z)->{
            System.out.println("sal");
        });
    }

}
