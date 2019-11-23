package controllers;

import animatefx.animation.FadeInLeft;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorController {
    @FXML
    public ListView patientsListView;
    public TextField searchPatients;

    public void initialize(){
        FadeInLeft fadeList = new FadeInLeft(patientsListView);
        fadeList.play();
    }
}
