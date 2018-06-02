/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author falcu
 */
public class ShowScreenController implements Initializable {

    @FXML
    private Button searchButton;
    @FXML
    private TextField searchCity;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> tempCol;
    @FXML
    private TableColumn<?, ?> pressCol;
    @FXML
    private TableColumn<?, ?> descCol;

    private RequestResponse rr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void search() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        tempCol.setCellValueFactory(new PropertyValueFactory<>("temperature"));
        pressCol.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("weatherDesc"));

        try {
            rr = new RequestResponse(searchCity.getText());
        } catch (Exception ex) {
            Logger.getLogger(ShowScreenController.class.getName()).log(Level.SEVERE, null, ex);
            rr = null;
        } finally {
            tableView.getItems().addAll(rr.we);
        }

    }

}
