package parkinglot;

public class SpaceNE extends ParkingSpace{
    SpaceNE(int num){
        super.setType(Constants.NE);
        super.setNum(num);
        super.setEmpty();
        super.setPrice(Constants.VALUE_NE);
        super.setCarType("");
    }
}