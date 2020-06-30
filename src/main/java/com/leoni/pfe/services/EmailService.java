package com.leoni.pfe.services;

import com.leoni.pfe.Email.EmailConfig;
import com.leoni.pfe.Email.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailConfig emailConfig;

    @Autowired
    public EmailService(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    // ****************

    public boolean sendEmail(Feedback feedback) {
        // Creation un mail pour L'expéditeur
        try {
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

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
