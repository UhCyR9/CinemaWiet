package pl.edu.agh.to.cinemawiet.utils.exception;


public class InputValidationException extends RuntimeException {

    public InputValidationException(String given) {
        super("Provided value: '%s' doesn't match this field".formatted(given));
    }
}
