package queivan.bus.exceptions;

public class VehicleNotFoundException extends RuntimeException{
    public VehicleNotFoundException(){
        super("Nie znaleziono pojazdu z tablicami o takiej wartości");
    }
}
