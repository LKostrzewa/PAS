package pl.bialekkostrzewa.exceptions;

public class ResourceTakenException extends RuntimeException {

    public ResourceTakenException(String message){
        super(message);
    }
}
