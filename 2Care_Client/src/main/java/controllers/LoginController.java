package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.LoginService;

import java.io.*;
import java.net.Socket;

public class LoginController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    private LoginService loginService;
    private Socket client;

    public void initialize(){
        try {
            client = new Socket("127.0.0.1",1256);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void handleConnectButton(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if (!username.equals("") && !password.equals("")) {
            try {
                ObjectOutputStream socketOut = new ObjectOutputStream(client.getOutputStream());
                socketOut.writeObject(username);
                socketOut.writeObject(password);
                ObjectInputStream socketIn = new ObjectInputStream(client.getInputStream());
                int connected =(int) socketIn.readObject();
                if (connected == 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "username or password invalid.");
                    alert.setTitle("Connection error");
                    alert.showAndWait();
                } else if (connected == 1) { // patient
                    loadStage("/views/Patient.fxml");
                } else if (connected == 2) {

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadStage(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
