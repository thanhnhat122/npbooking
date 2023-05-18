package hcmute.edu.booking.service;

import hcmute.edu.booking.model.User;
import hcmute.edu.booking.model.VerificationToken;

import java.sql.Timestamp;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

    void save(User user, String token);

    Timestamp calculateExpiryDate(int expiryTimeInMinutes);
}
