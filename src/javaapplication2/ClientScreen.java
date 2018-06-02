package javaapplication2;

/**
 *
 * @author falcu
 */
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class ClientScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ShowScreen.fxml"));
        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);

        stage.setTitle("Title");
        stage.setResizable(false);

        stage.show();
    }
}
