package pl.edu.agh.to.cinemawiet.utils.exception;


import java.util.ArrayList;

public class InputValidationException extends RuntimeException {

    public InputValidationException(ArrayList<String> given) {
        super("Provided value: '%s' doesn't match %s field".formatted(given.get(0),given.get(1)));
    }
}
