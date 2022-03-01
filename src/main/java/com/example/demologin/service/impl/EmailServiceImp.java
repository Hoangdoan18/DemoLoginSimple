package com.example.demologin.service.impl;

import com.example.demologin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender sender;


    //Need to generate verify code
    @Override
    public void sendVerifyEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        sender.send(message);
    }
}
