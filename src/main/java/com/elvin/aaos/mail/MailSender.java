package com.elvin.aaos.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailSender {

    private final JavaMailSender mailSender;

    public MailSender(@Autowired JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String msg) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("elvinjava1@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("text/html; charset=utf-8", msg);
        mailSender.send(message);
    }

}
