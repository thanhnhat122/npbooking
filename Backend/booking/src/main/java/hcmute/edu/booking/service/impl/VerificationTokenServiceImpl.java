package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.model.User;
import hcmute.edu.booking.model.VerificationToken;
import hcmute.edu.booking.repository.VerificationTokenRepository;
import hcmute.edu.booking.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Override
    public void save(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setExpiryDate((calculateExpiryDate(15)));
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
