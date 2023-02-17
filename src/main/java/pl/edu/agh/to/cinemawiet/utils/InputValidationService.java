package pl.edu.agh.to.cinemawiet.utils;


import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.utils.exception.InputValidationException;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class InputValidationService {

    private final static Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]+");
    private final static Pattern MAIL_PATTERN = Pattern.compile("\\w+@\\w+(\\.\\w+)+");

    public String validateName(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            ArrayList<String> given = new ArrayList<>();
            given.add(name);
            given.add("name");
            throw new InputValidationException(given);
        }
        return name;
    }

    public String validateSecondName(String secondName) {
        if (!NAME_PATTERN.matcher(secondName).matches()) {
            ArrayList<String> given = new ArrayList<>();
            given.add(secondName);
            given.add("second name");
            throw new InputValidationException(given);
        }
        return secondName;
    }

    public String validateMail(String mail) {
        if (!MAIL_PATTERN.matcher(mail).matches()) {
            ArrayList<String> given = new ArrayList<>();
            given.add(mail);
            given.add("mail");
            throw new InputValidationException(given);
        }
        return mail;
    }
}
