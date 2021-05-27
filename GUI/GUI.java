package GUI;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));
    
        Scene scene = new Scene(root);
    
        stage.setTitle("VideoGameStore App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    
}
