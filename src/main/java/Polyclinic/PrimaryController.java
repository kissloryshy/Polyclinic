package Polyclinic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    public TextField tfLogin;
    public TextField tfPass;
    public Button btnLogin;
    public Button btnNew;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("onLoad");
        SQLTasks.connect();
    }

    @FXML
    private void btnLoginClicked() {
        System.out.println(tfLogin.getText());
        System.out.println(tfPass.getText());

        String login = tfLogin.getText();
        String pass = tfPass.getText();

        SQLTasks.verify(login, pass);
    }

    @FXML
    private void btnNewClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            SecondaryController sc = fxmlLoader.getController();
            sc.setVariables(tfLogin.getText(), tfPass.getText());
            Stage stage = new Stage();
            stage.setTitle("Поликлиника У Дмитрия");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
