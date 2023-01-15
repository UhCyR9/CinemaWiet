package pl.edu.agh.to.cinemawiet.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cienemawiet@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
