package at.spengergasse.domain;

public class CoffeeOrderException extends RuntimeException{
    public CoffeeOrderException(String message){
        super(message);
    }
}
