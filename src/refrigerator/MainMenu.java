package refrigerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
        primaryStage.setTitle("Hello User");
        primaryStage.setScene(new Scene(root, 780, 580));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
