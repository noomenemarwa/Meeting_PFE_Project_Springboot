package com.leoni.pfe.rest;

import com.leoni.pfe.Email.EmailConfig;
import com.leoni.pfe.Email.Feedback;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = {"http://localhost:4200"},
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class EmailController {

    private EmailConfig emailConfig;

    public EmailController(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping(path = "/sendEmail")
    public void sendFeedback(@Valid @RequestBody Feedback feedback,
                             BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback is not valid");
        }

        // Creation un mail pour L'expéditeur
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());

        // Creation d'une instance d'un email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("rc@feedback.com");
        mailMessage.setSubject("New feedback from " + feedback.getNom());
        mailMessage.setText(feedback.getFeedback());

        // Envoyer un mail
        mailSender.send(mailMessage);

    }
}
