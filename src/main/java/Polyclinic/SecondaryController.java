package Polyclinic;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    public Label lbLogin;
    public Label lbPass;
    public Button btnGetStaff;
    public TableView tvStaff;

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

    public void btnGetStaffClick() {
        insertTvStaff();
    }

    public void insertTvStaff () {
        tvStaff.getColumns().clear();
        try {
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            ResultSet rs = SQLTasks.getStaff();

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tvStaff.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    String tmp = rs.getString(i);
                    if (tmp == null) {
                        row.add("");
                    } else {
                        row.add(rs.getString(i));
                    }
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tvStaff.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}