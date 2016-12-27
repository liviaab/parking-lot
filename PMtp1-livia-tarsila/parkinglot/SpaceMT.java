package parkinglot;

public class SpaceMT extends ParkingSpace{    
    SpaceMT(int num){
        super.setType(Constants.MT);
        super.setNum(num);
        super.setEmpty();
        super.setPrice(Constants.VALUE_MT);
        super.setCarType("");
    }    
}