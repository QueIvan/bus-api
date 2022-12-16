package queivan.bus.exceptions;

public class ReadingNotFoundException extends RuntimeException{
    public ReadingNotFoundException(){
        super("Nie znaleziono odczytu z id o takiej wartości");
    }
}
