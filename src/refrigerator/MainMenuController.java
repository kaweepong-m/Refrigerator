package refrigerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainMenuController {
    @FXML private Button creatorButton;
    @FXML public void handleCreatorButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/Creator.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) creatorButton.getScene().getWindow();
            stage.setTitle("Creator");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button freezeButton;
    @FXML void handleFreezeButton(ActionEvent event) throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FreezerMain.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) freezeButton.getScene().getWindow();
            stage.setTitle("Freezer");
            stage.setScene(scene);

    }

    @FXML private Button coolerButton;
    @FXML void handleCoolerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/CoolerMain.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) coolerButton.getScene().getWindow();
            stage.setTitle("Cooler");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button homeButton;
    @FXML void handleHomeButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setTitle("Hello User");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    // Create CSV
    @FXML public void initialize(){
        String fs = File.separator;
        String dir = System.getProperty("user.dir")+fs+"RefrigeratorData";
        String fileName = dir+fs+"RefrigeratorData.csv";
        File file = new File(dir);
        try{
            if(!file.exists()){
                file.mkdirs();
                File dataFile = new File(fileName);
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
