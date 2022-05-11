package refrigerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddFoodCoolerController  {

    @FXML private ImageView foodImage;
    @FXML private Button addImage;
    @FXML private TextField nameTextField,unitTextField,numberTextField;
    @FXML private DatePicker expDatePicker;
    @FXML private ComboBox<String> foodTypeComboBox,coolerSlotComboBox,status;

    private ArrayList<Food> read = new ArrayList<>();
    private ArrayList<Food> write = new ArrayList<>();
    private String fs = File.separator;
    private String dir = System.getProperty("user.dir")+fs+"RefrigeratorData";
    private String fileName = dir+fs+"RefrigeratorData.csv";
    public void initialize () {
        foodTypeComboBox.getItems().addAll("อาหารสด","ขนม","อาหารสำเร็จรูป","เครื่องดื่ม","อื่นๆ");
        coolerSlotComboBox.getItems().addAll("1","2","3","4","5","6");
        status.getItems().addAll("ของแข็ง","ของเหลว");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line = null;
            while((line = buffer.readLine()) != null){
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
                if (temp.getSlot().equals("Cooler")){
                    read.add(temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path target;
    @FXML public void handle(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
        File file = chooser.showOpenDialog(addImage.getScene().getWindow());
        if (file != null){
            try {
                File destDir = new File("images");
                destDir.mkdirs();
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now()+"_"+System.currentTimeMillis()+"."+fileSplit[fileSplit.length - 1];
                target = FileSystems.getDefault().getPath(destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING );
                foodImage.setImage(new Image(target.toUri().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML public void handleClearButton(ActionEvent event){
        nameTextField.setText(null);
        foodTypeComboBox.setValue(null);
        coolerSlotComboBox.setValue(null);
        unitTextField.setText(null);
        numberTextField.setText(null);
        expDatePicker.setValue(null);
        foodImage.setImage(null);
    }

    public boolean check(){
        try {
            Integer.parseInt(numberTextField.getText());


        } catch (NumberFormatException e) {

            return false;
        }
        return true;
    }
    public boolean check2(){
        try {
            Integer.parseInt(unitTextField.getText());
        } catch (NumberFormatException e) {

            return false;
        }
        return true;
    }
    public boolean check3(){
        int n = 0;
        if (read.size() == 0){
            return true;
        }
        else{
            for(int count = 0 ; count < read.size();count++){
                if (read.get(count).equals("Cooler")){
                    if (read.get(count).getSlotIndex().equals(coolerSlotComboBox.getValue())){
                        n += 1;
                        if (read.get(count).getFoodType().equals(foodTypeComboBox.getValue())){
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }

            }
            if (n == 0){
                return true;
            }
        }
        return false;
    }

    @FXML private Button backCoolerButton;
    @FXML public void handleBackCoolerButton(ActionEvent event){
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/CoolerMain.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage stage = (Stage) backCoolerButton.getScene().getWindow();
            stage.setTitle("Cooler");
            stage.setScene(scene);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    @FXML private Button addButton;
    @FXML public void handleAddButton(ActionEvent event) {
        if (nameTextField.getText() != null && foodTypeComboBox.getValue() != null && coolerSlotComboBox.getValue() != null && numberTextField.getText() != null
                && expDatePicker.getValue() != null && target != null & status.getValue() != null){
            if (check()){
                if (!check2()){
                    if (check3()){
                        Food food = new Food("Cooler", coolerSlotComboBox.getValue(), nameTextField.getText(), foodTypeComboBox.getValue(), numberTextField.getText(), unitTextField.getText(), LocalDate.now().toString(), expDatePicker.getValue().toString(), target.toString(),status.getValue());
                        write.add(food);
                        save();
                        try {
                            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/fxml/CoolerMain.fxml"));
                            Scene scene = new Scene(fxmlLoader);
                            Stage stage = (Stage) addButton.getScene().getWindow();
                            stage.setTitle("Cooler");
                            stage.setScene(scene);

                        } catch (Exception e){
                            System.err.println(e.getMessage());
                        }
                    }
                    else {
                        Alert alert3 = new Alert(Alert.AlertType.WARNING);
                        alert3.setTitle("Warning Dialog");
                        alert3.setHeaderText("Cannot enter different types of food. Please enter the same food type.");
                        alert3.showAndWait();

                    }
                }
                else {
                    Alert alert3 = new Alert(Alert.AlertType.WARNING);
                    alert3.setTitle("Warning Dialog");
                    alert3.setHeaderText("Please enter the text in the food unit field.");
                    alert3.showAndWait();
                }
            }
            else {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Warning Dialog");
                alert2.setHeaderText("Please enter the number in the amount of food field.");
                alert2.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Please fill out all information.");
            alert.showAndWait();
        }
    }
    public void save (){
        try {
            File file = new File(dir);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
            writer.write(write.get(0).toString());
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
