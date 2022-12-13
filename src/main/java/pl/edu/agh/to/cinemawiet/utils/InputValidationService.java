package pl.edu.agh.to.cinemawiet.utils;


import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.utils.exception.InputValidationException;

import java.util.regex.Pattern;

@Service
public class InputValidationService {

    private final static Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]+");
    private final static Pattern MAIL_PATTERN = Pattern.compile("\\w+@\\w+(\\.\\w+)+");

    public String validateName(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new InputValidationException(name);
        }
        return name;
    }

    public String validateSecondName(String secondName) {
        if (!NAME_PATTERN.matcher(secondName).matches()) {
            throw new InputValidationException(secondName);
        }
        return secondName;
    }

    public String validateMail(String mail) {
        if (!MAIL_PATTERN.matcher(mail).matches()) {
            throw new InputValidationException(mail);
        }
        return mail;
    }
}
