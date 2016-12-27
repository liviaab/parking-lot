package parkinglot;

public class SpaceVG extends ParkingSpace{
    SpaceVG(int num){
        super.setType(Constants.VG);
        super.setNum(num);
        super.setEmpty();
        super.setPrice(Constants.VALUE_VG);
        super.setCarType("");
    }    
}