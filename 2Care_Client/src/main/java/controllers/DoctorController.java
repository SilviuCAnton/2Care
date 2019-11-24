package controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.FadeInRight;
import domain.Patient;
import domain.UserAccount;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        subscribedListView.setManaged(false);
        userAccounts.setAll(userAccountRepoProxy.findAllPatients());
    }

    public void initialize() {
        patientsListView.setItems(userAccounts);
        patientsListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Patient>() {
                    public void changed(ObservableValue<? extends Patient> observable, Patient oldValue, Patient newValue) {
                        System.out.println("selection changed");
                        subscribedListView.setManaged(true);
                        FadeInRight fadeInRight = new FadeInRight(subscribedListView);
                        fadeInRight.play();
                    }
                });
    }

}
