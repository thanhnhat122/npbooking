package hcmute.edu.booking.service;

import hcmute.edu.booking.model.User;

import javax.mail.MessagingException;

public interface EmailService {
    void sendHtmlMail(User user) throws MessagingException;
}
