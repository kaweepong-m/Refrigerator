package refrigerator;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;


public class ViewFoodController {
    @FXML private TableView<Food> foodTableView;
    @FXML private TableColumn<Food, String> nameTableView;
    @FXML private TableColumn<Food, String> typeTableView;
    @FXML private TableColumn<Food, String> numberTableView;
    @FXML private TableColumn<Food, String> inputDateTableView;
    @FXML private TableColumn<Food, String> EXPTableView;
    @FXML private TableColumn<Food, String> unitTableView;
    @FXML private TableColumn<Food, String> statusTableView;
    @FXML private Label test;

    private String slot2;
    private int slotNumber;
    private String fs = File.separator;
    private String dir = System.getProperty("user.dir")+fs+"RefrigeratorData";
    private String fileName = dir+fs+"RefrigeratorData.csv";

    public void set(String slot2,int slotNumber){
        this.slot2 = slot2;
        this.slotNumber = slotNumber;
    }

    public void initialize(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                test.setText(slot2+slotNumber);
                ObservableList<Food> viewFile = FXCollections.observableArrayList();
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
                        if (temp.getSlotIndex().equals(slotNumber+ "") && temp.getSlot().equals(slot2)){
                            viewFile.add(temp);
                        }
                        for (Food food: viewFile){
                            food.foodGetIn();
                        }
                        nameTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("foodName"));
                        typeTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("foodType"));
                        numberTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("foodNumber"));
                        unitTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("foodUnit"));
                        inputDateTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("inputDate"));
                        EXPTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("foodEXP"));
                        statusTableView.setCellValueFactory(new PropertyValueFactory<Food,String>("status"));
                        foodTableView.setItems(viewFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}