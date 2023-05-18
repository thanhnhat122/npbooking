package hcmute.edu.booking.repository;

import hcmute.edu.booking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
  User findByEmail(String username);

  User findByPhone(String phone);

  List<User> findByRole(User.RoleEnum roleAd);
}
