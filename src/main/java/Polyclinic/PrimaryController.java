package Polyclinic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    public TextField tfLogin;
    public TextField tfPass;
    public Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SQLTasks.connect();
    }

    @FXML
    private void btnLoginClicked() {
        System.out.println(tfLogin.getText());
        System.out.println(tfPass.getText());

        String login = tfLogin.getText();
        String pass = tfPass.getText();

        String[] result = SQLTasks.verify(login, pass);
        if (result[0]==null || result[1]==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неправильный логин или пароль");
            //alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea("Ошибка")));
            alert.showAndWait();
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("secondary.fxml"));
            Parent root = (Parent)fxmlLoader.load();

            SecondaryController sc = fxmlLoader.getController();
            sc.setVariables(result[0], result[1]);
            Stage stage = new Stage();
            stage.setTitle("Поликлиника У Дмитрия");
            stage.setScene(new Scene(root, 600, 400));
            stage.resizableProperty().setValue(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
