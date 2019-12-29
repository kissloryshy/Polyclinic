package Polyclinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SecondaryController implements Initializable {

    public Label lbLogin;
    public Label lbPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("onLoad from Secondary Controller");

        lbLogin.setText("qwe");
        lbPass.setText("rty");
    }

    public void setVariables (String login, String pass) {
        lbLogin.setText(login);
        lbPass.setText(pass);
    }
}