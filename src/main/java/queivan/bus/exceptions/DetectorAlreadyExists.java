package queivan.bus.exceptions;

public class DetectorAlreadyExists extends RuntimeException{
    public DetectorAlreadyExists(){
        super("Czujnik o podanym adresie MAC istnieje już w bazie");
    }
}
