package parkinglot;

public class SpaceVP extends ParkingSpace{

    SpaceVP(int num){
        super.setType(Constants.VP);
        super.setNum(num);
        super.setEmpty();
        super.setPrice(Constants.VALUE_VP);
        super.setCarType("");
    }
}