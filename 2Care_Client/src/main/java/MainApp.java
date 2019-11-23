import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.LoginService;

import java.io.IOException;

public class MainApp extends Application {
    private LoginService loginService;


    private void initComponents() {
        loginService = new LoginService();
    }

    private Parent initStudentView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/views/Login.fxml"));
        Parent root = loader.load();
        LoginController loginController = loader.getController();
        loginController.setService(loginService);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initComponents();
        Parent root = initStudentView();
        Scene scene = new Scene(root, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
