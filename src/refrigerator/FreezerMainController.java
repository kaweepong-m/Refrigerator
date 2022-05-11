package refrigerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;

public class FreezerMainController {
    private ArrayList<Food> readFreezer1 = new ArrayList<Food>();
    private ArrayList<Food> readFreezer2 = new ArrayList<Food>();

    @FXML private Label freezerNumberFood1,freezerNumberFood2,
            freezerName1,freezerName2,
            freezerEXP1,freezerEXP2;

    @FXML private ImageView freezerImage1,freezerImage2;

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

    @FXML private Button addFoodFreezerButton;
    @FXML public void handleAddFreezerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/AddFoodFreezer.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) addFoodFreezerButton.getScene().getWindow();
            stage.setTitle("Add Food!!");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //read csv
    public void initialize () {
        String fs = File.separator;
        String dir = System.getProperty("user.dir") + fs + "RefrigeratorData";
        String fileName = dir + fs + "RefrigeratorData.csv";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = null;
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String slot = data[0].trim();
                String slotIndex = data[1].trim();
                String foodName = data[2].trim();
                String foodType = data[3].trim();
                String foodNumber = data[4].trim();
                String foodUnit = data[5].trim();
                String inputDate = data[6].trim();
                String foodExp = data[7].trim();
                String imgAddress = data[8].trim();
                int temperature = Integer.parseInt(data[9].trim());
                String status = data[10].trim();
                Food temp = null;
                if (slot.equals("Freezer")){
                    temp = new FreezeFood(slot, slotIndex, foodName, foodType, foodNumber, foodUnit, inputDate, foodExp, imgAddress, temperature,status);
                }else {
                    temp = new Food(slot, slotIndex, foodName, foodType, foodNumber, foodUnit, inputDate, foodExp, imgAddress, temperature,status);
                }
                temp.foodGetIn();//polymorphism
                if(temp.getSlot().equals("Freezer")){
                    if (temp.getSlotIndex().equals("1")){
                        readFreezer1.add(temp);
                        freezerNumberFood1.setText("อาหารในช่องนี้ " + readFreezer1.size() + " ชิ้น");
                        String[] date = freezerEXP1.getText().split(" ");
                        if (freezerName1.getText().equals("")){
                            freezerName1.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            freezerEXP1.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathFreezer1 = new FileInputStream(temp.getImgAddress());
                            Image pathImageFreezer1 = new Image(pathFreezer1);
                            freezerImage1.setImage(pathImageFreezer1);
                        }
                        else if (temp.checkEXP(date[1])){
                            freezerName1.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            freezerEXP1.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathFreezer1 = new FileInputStream(temp.getImgAddress());
                            Image pathImageFreezer1 = new Image(pathFreezer1);
                            freezerImage1.setImage(pathImageFreezer1);
                        }
                    }
                    if (temp.getSlotIndex().equals("2")){
                        readFreezer2.add(temp);
                        freezerNumberFood2.setText("อาหารในช่องนี้ " + readFreezer2.size() + " ชิ้น");
                        String[] date = freezerEXP2.getText().split(" ");
                        if (freezerName2.getText().equals("")){
                            freezerName2.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            freezerEXP2.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathFreezer2 = new FileInputStream(temp.getImgAddress());
                            Image pathImageFreezer2 = new Image(pathFreezer2);
                            freezerImage2.setImage(pathImageFreezer2);
                        }
                        else if (temp.checkEXP(date[1])){
                            freezerName2.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            freezerEXP2.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathFreezer2 = new FileInputStream(temp.getImgAddress());
                            Image pathImageFreezer2 = new Image(pathFreezer2);
                            freezerImage2.setImage(pathImageFreezer2);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void handleViewFoodFreezer1 (ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Freezer",1);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodFreezer2 (ActionEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Freezer",2);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button removeFreezerButton;
    @FXML public void handleRemoveFreezerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/RemoveFoodFreezer.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) removeFreezerButton.getScene().getWindow();
            stage.setTitle("Remove Food!!");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
