package com.example.demologin.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendVerifyEmail(String to, String subject, String text) throws MessagingException;
}
