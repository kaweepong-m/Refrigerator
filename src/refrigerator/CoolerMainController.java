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


public class CoolerMainController {
    ArrayList<Food> readCooler1 = new ArrayList<Food>();
    ArrayList<Food> readCooler2 = new ArrayList<Food>();
    ArrayList<Food> readCooler3 = new ArrayList<Food>();
    ArrayList<Food> readCooler4 = new ArrayList<Food>();
    ArrayList<Food> readCooler5 = new ArrayList<Food>();
    ArrayList<Food> readCooler6 = new ArrayList<Food>();

    @FXML private Button homeButton;
    @FXML private Label coolerNumberFood1, coolerNumberFood2, coolerNumberFood3, coolerNumberFood4, coolerNumberFood5, coolerNumberFood6,
            coolerName1, coolerName2, coolerName3, coolerName4, coolerName5, coolerName6,
            coolerEXP1,coolerEXP2,coolerEXP3,coolerEXP4,coolerEXP5,coolerEXP6;

    @FXML private ImageView coolerImage1, coolerImage2, coolerImage3, coolerImage4, coolerImage5, coolerImage6;

    @FXML void handleHomeButton(ActionEvent event) {
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setTitle("Hello User");
            stage.setScene(scene);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button addFoodCoolerButton;
    @FXML public void handleAddCoolerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/AddFoodCooler.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) addFoodCoolerButton.getScene().getWindow();
            stage.setTitle("Add Food!!");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //read csv
    public void initialize() {
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
                if (temp.getSlot().equals("Cooler")) {
                    if (temp.getSlotIndex().equals("1")) {
                        readCooler1.add(temp);
                        coolerNumberFood1.setText("อาหารในช่องนี้ " + readCooler1.size() + " ชิ้น");
                        String[] date = coolerEXP1.getText().split(" ");
                        if (coolerName1.getText().equals("")){
                            coolerName1.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP1.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler1 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler1 = new Image(pathCooler1);
                            coolerImage1.setImage(pathImageCooler1);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName1.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP1.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler1 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler1 = new Image(pathCooler1);
                            coolerImage1.setImage(pathImageCooler1);
                        }
                    }

                    if (temp.getSlotIndex().equals("2")) {
                        String[] date = coolerEXP2.getText().split(" ");
                        readCooler2.add(temp);
                        coolerNumberFood2.setText("อาหารในช่องนี้ " + readCooler2.size() + " ชิ้น");
                        if (coolerName2.getText().equals("")) {
                            coolerName2.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP2.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler2 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler2 = new Image(pathCooler2);
                            coolerImage2.setImage(pathImageCooler2);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName2.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP2.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler2 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler2 = new Image(pathCooler2);
                            coolerImage2.setImage(pathImageCooler2);
                        }
                    }
                    if (temp.getSlotIndex().equals("3")) {
                        String[] date = coolerEXP3.getText().split(" ");
                        readCooler3.add(temp);
                        coolerNumberFood3.setText("อาหารในช่องนี้ " + readCooler3.size() + " ชิ้น");
                        if (coolerName3.getText().equals("")){
                            coolerName3.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP3.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler3 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler3 = new Image(pathCooler3);
                            coolerImage3.setImage(pathImageCooler3);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName3.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP3.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler3 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler3 = new Image(pathCooler3);
                            coolerImage3.setImage(pathImageCooler3);
                        }
                    }
                    if (temp.getSlotIndex().equals("4")) {
                        String[] date = coolerEXP4.getText().split(" ");
                        readCooler4.add(temp);
                        coolerNumberFood4.setText("อาหารในช่องนี้ " + readCooler4.size() + " ชิ้น");
                        if (coolerName4.getText().equals("")){
                            coolerName4.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP4.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler4 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler4 = new Image(pathCooler4);
                            coolerImage4.setImage(pathImageCooler4);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName4.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP4.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler4 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler4 = new Image(pathCooler4);
                            coolerImage4.setImage(pathImageCooler4);
                        }
                    }
                    if (temp.getSlotIndex().equals("5")) {
                        String[] date = coolerEXP5.getText().split(" ");
                        readCooler5.add(temp);
                        coolerNumberFood5.setText("อาหารในช่องนี้ " + readCooler5.size() + " ชิ้น");
                        if (coolerName5.getText().equals("")){
                            coolerName5.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP5.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler5 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler5 = new Image(pathCooler5);
                            coolerImage5.setImage(pathImageCooler5);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName5.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP5.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler5 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler5 = new Image(pathCooler5);
                            coolerImage5.setImage(pathImageCooler5);
                        }
                    }
                    if (temp.getSlotIndex().equals("6")) {
                        String[] date = coolerEXP6.getText().split(" ");
                        readCooler6.add(temp);
                        coolerNumberFood6.setText("อาหารในช่องนี้ " + readCooler6.size() + " ชิ้น");
                        if (coolerName6.getText().equals("")){
                            coolerName6.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP6.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler6 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler6 = new Image(pathCooler6);
                            coolerImage6.setImage(pathImageCooler6);
                        }
                        else if (temp.checkEXP(date[1])){
                            coolerName6.setText(temp.getFoodName()+"  "+temp.getTemperature()+"C");
                            coolerEXP6.setText("วันหมดอายุ: "+(temp.getFoodEXP()));
                            FileInputStream pathCooler6 = new FileInputStream(temp.getImgAddress());
                            Image pathImageCooler6 = new Image(pathCooler6);
                            coolerImage6.setImage(pathImageCooler6);
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML public void handleViewFoodCooler1(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 1);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodCooler2(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 2);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodCooler3(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 3);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodCooler4(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 4);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodCooler5(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 5);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML public void handleViewFoodCooler6(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ViewFood.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            ViewFoodController view = fxmlLoader.<ViewFoodController>getController();
            view.set("Cooler", 6);
            Stage stage = new Stage();
            stage.setTitle("View Food!");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML private Button removeCoolerButton;
    @FXML public void handleRemoveCoolerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/RemoveFoodCooler.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) removeCoolerButton.getScene().getWindow();
            stage.setTitle("Remove Food!!");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}




