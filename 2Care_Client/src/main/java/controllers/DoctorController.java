package controllers;

import animatefx.animation.FadeInLeft;
import domain.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proxy.UserAccountRepoProxy;

public class DoctorController {
    @FXML
    public ListView patientsListView;
    @FXML
    public TextField searchPatients;

    private ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList();

    private UserAccountRepoProxy userAccountRepoProxy;

    public void setUserAccountRepoProxy(UserAccountRepoProxy userAccountRepoProxy) {
        this.userAccountRepoProxy = userAccountRepoProxy;
        userAccounts.setAll(userAccountRepoProxy.findAll());
    }

    public void initialize(){
        patientsListView.setItems(userAccounts);
    }

}
