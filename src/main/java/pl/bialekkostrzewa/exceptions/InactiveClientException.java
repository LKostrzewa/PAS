package pl.bialekkostrzewa.exceptions;

public class InactiveClientException extends RuntimeException {

    public InactiveClientException(String message){
        super(message);
    }
}
