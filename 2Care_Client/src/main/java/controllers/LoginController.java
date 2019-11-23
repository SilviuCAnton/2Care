package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import services.LoginService;

import java.net.Socket;

public class LoginController {
    @FXML
    public TextField usernameTextField;
    @FXML
    public TextField passwordTextField;
    private LoginService loginService;
    Socket server;

    private void initialize(){

    }

    public void setService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void handleConnectButton(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

    }
}
