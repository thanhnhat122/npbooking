package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.model.User;
import hcmute.edu.booking.model.VerificationToken;
import hcmute.edu.booking.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    private final VerificationTokenServiceImpl verificationTokenService;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(VerificationTokenServiceImpl verificationTokenService, JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendHtmlMail(User user) throws MessagingException {
        VerificationToken verificationToken = verificationTokenService.findByUser(user);
        if (verificationToken != null) {
            String token = verificationToken.getToken();
            String toAddress = user.getEmail();
            String subject = "Please verify your registration";
            String content = "Dear [[name]],<br>"
                    + "Please click the link below to verify your registration:<br>"
                    + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                    + "Please note that the link will be expired in 15 minutes,<br>"
                    + "Thank you,<br>"
                    + "NPBOOKING.";
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", user.getFirstName());
            String verifyURL = "http://localhost:4200/activation?token=" + token;

            content = content.replace("[[URL]]", verifyURL);

            helper.setText(content, true);

            javaMailSender.send(message);
        }
    }
}
