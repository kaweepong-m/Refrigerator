package refrigerator;

import java.time.LocalDate;

public class Food {
    private String slot;
    private String slotIndex;
    private String foodName;
    private String foodType;
    private String foodNumber;
    private String foodUnit;
    private String inputDate;
    private String foodEXP;
    private String imgAddress;
    private int temperature;
    private String status;


    public Food(String slot, String slotIndex, String foodName, String foodType, String foodNumber, String foodUnit, String inputDate, String foodEXP, String imgAddress,int temperature,String status) {
        this.slot = slot;
        this.slotIndex = slotIndex;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodNumber = foodNumber;
        this.foodUnit = foodUnit;
        this.inputDate = inputDate;
        this.foodEXP = foodEXP;
        this.imgAddress = imgAddress;
        this.temperature = temperature;
        this.status = status;
    }

    public Food(String slot, String slotIndex, String foodName, String foodType, String foodNumber, String foodUnit, String inputDate, String foodEXP, String imgAddress,String status) {
        this.slot = slot;
        this.slotIndex = slotIndex;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodNumber = foodNumber;
        this.foodUnit = foodUnit;
        this.inputDate = inputDate;
        this.foodEXP = foodEXP;
        this.imgAddress = imgAddress;
        this.status = status;
    }

    public void foodGetIn() {
        this.temperature = 22;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getSlot() {
        return slot;
    }

    public String getSlotIndex() {
        return slotIndex;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getFoodNumber() {
        return foodNumber;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public String getInputDate() {
        return inputDate;
    }

    public String getFoodEXP() {
        return foodEXP;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public String getStatus() {
        return status;
    }

    public void statusChange(){
        if (this.status.equals("ของเหลว")){
            this.status = "ของแข็ง";
        }
    }

    public boolean checkEXP (String date){
        if (LocalDate.parse(foodEXP).isBefore(LocalDate.parse(date))){
            return true;
        }
        else {
            return false;
        }
    }

    @Override public String toString() {
        return slot+","+slotIndex+","+foodName+","+foodType+","+getFoodNumber()+","+getFoodUnit()+","+getInputDate()+","+getFoodEXP()+","+getImgAddress()+","+getTemperature()+","+getStatus();
    }
}
