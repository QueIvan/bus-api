package queivan.bus.exceptions;

public class DetectorNotFoundException extends RuntimeException{
    public DetectorNotFoundException(){
        super("Nie znaleziono czujnika z adresem MAC o takiej wartości");
    }
}
