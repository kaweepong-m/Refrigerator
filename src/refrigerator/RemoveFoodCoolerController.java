package refrigerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class RemoveFoodCoolerController {
    @FXML private ComboBox foodCoolerRemove, slotCoolerRemove;
    public void initialize() {
        foodCoolerRemove.getItems().addAll("1","2","3","4","5","6");
    }

    public void chooseSlotCoolerIndex (ActionEvent actionEvent) throws IOException {
        slotCoolerRemove.getItems().clear();
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
                if (slotIndex.equals(foodCoolerRemove.getValue().toString()) && slot.equals("Cooler")){
                    slotCoolerRemove.getItems().add(foodName);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private Button removeCoolerButton;
    public void handleRemoveCoolerButton (ActionEvent actionEvent) throws IOException {
        String fs = File.separator;
        String dir = System.getProperty("user.dir") + fs + "RefrigeratorData";
        String fileName = dir + fs + "RefrigeratorData.csv";
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        int deleteCheck = 0;
        ArrayList<Food> temp = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data[2].equals(slotCoolerRemove.getValue().toString()) && deleteCheck == 0 && data[0].equals("Cooler")) {
                deleteCheck += 1;
            } else {
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
                Food food = null;
                if (slot.equals("Freezer")){
                    food = new FreezeFood(slot, slotIndex, foodName, foodType, foodNumber, foodUnit, inputDate, foodExp, imgAddress, temperature,status);
                }else {
                    food = new Food(slot, slotIndex, foodName, foodType, foodNumber, foodUnit, inputDate, foodExp, imgAddress, temperature,status);
                }
                temp.add(food);
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Food food : temp) {
            writer.write(food.getSlot() + "," + food.getSlotIndex() + "," + food.getFoodName() + "," + food.getFoodType() + "," + food.getFoodNumber() + "," +
                    food.getFoodUnit() + "," + food.getInputDate() + "," + food.getFoodEXP() + "," + food.getImgAddress() + "," + food.getTemperature() + "," + food.getStatus());
            writer.newLine();
        }
        writer.flush();
        writer.close();
        reader.close();
        slotCoolerRemove.getItems().clear();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line2 = null;
            while ((line2 = buffer.readLine()) != null) {
                String[] data = line2.split(",");
                String slot = data[0].trim();
                String slotIndex = data[1].trim();
                String foodName = data[2].trim();
                if (slotIndex.equals(foodCoolerRemove.getValue().toString()) && slot.equals("Cooler")) {
                    slotCoolerRemove.getItems().add(foodName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/CoolerMain.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) removeCoolerButton.getScene().getWindow();
            stage.setTitle("Cooler");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button backCoolerButton;
    @FXML public void handleBackCoolerButton(ActionEvent event) throws IOException{
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/CoolerMain.fxml"));
        Scene scene = new Scene(fxmlLoader);
        Stage stage = (Stage) backCoolerButton.getScene().getWindow();
        stage.setTitle("Freezer");
        stage.setScene(scene);
    }
}