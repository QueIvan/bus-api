package queivan.bus.exceptions;

public class VehicleAlreadyExists extends RuntimeException{
    public VehicleAlreadyExists(){
        super("Pojazd o podanej tablicy rejestracyjne istnieje już w bazie");
    }
}
