package refrigerator;

public class FreezeFood extends Food{
    public FreezeFood(String slot, String slotIndex, String foodName, String foodType, String foodNumber, String foodUnit, String inputDate, String foodEXP, String imgAddress,int temperature,String status) {
        super(slot,slotIndex,foodName,foodType,foodNumber,foodUnit,inputDate,foodEXP,imgAddress,temperature,status);
    }

    public FreezeFood(String slot, String slotIndex, String foodName, String foodType, String foodNumber, String foodUnit, String inputDate, String foodEXP, String imgAddress,String status) {
        super(slot,slotIndex,foodName,foodType,foodNumber,foodUnit,inputDate,foodEXP,imgAddress,status);
    }

    @Override
    public void foodGetIn() {
        if (getFoodType().equals("ไอศกรีม")){
            setTemperature(-15);
        }else if(getFoodType().equals("อาหารแช่แข็ง")){
            setTemperature(-10);
        }else{
            setTemperature(-12);
        }
        statusChange();
    }
}
