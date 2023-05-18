package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.User;
import hcmute.edu.booking.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
