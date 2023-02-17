package pl.edu.agh.to.cinemawiet.utils;


import org.junit.jupiter.api.Test;
import pl.edu.agh.to.cinemawiet.utils.exception.InputValidationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputValidationServiceTest {

    private final InputValidationService validationService = new InputValidationService();

    @Test
    void validateSimpleEmail() {
        //given
        String mail = "sample@mail.com";

        //when
        String validMail = validationService.validateMail(mail);

        //then
        assertEquals(mail, validMail);
    }

    @Test
    void validateEmailWithNumber() {
        //given
        String mail = "sample3@mail.com";

        //when
        String validMail = validationService.validateMail(mail);

        //then
        assertEquals(mail, validMail);
    }

    @Test
    void validateEmailWithWhitespace() {
        //given
        String mail = "samp le3@mail.com";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateMail(mail));
    }

    @Test
    void validateEmailWithoutAtSign() {
        //given
        String mail = "samplemail.com";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateMail(mail));
    }

    @Test
    void validateTooShortMail() {
        //given
        String mail = "sample@mail";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateMail(mail));
    }

    @Test
    void validateSimpleName() {
        //given
        String name = "Jan";

        //when
        String validName = validationService.validateName(name);

        //then
        assertEquals(name, validName);
    }

    @Test
    void validateWhitespaceName() {
        //given
        String name = "J an";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateName(name));
    }

    @Test
    void validateNameWithNumber() {
        //given
        String name = "Jan3";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateName(name));
    }

    @Test
    void validateNameWithNonCapitalLetterStart() {
        //given
        String name = "jan";

        //then
        assertThrows(InputValidationException.class, () -> validationService.validateName(name));
    }
}
