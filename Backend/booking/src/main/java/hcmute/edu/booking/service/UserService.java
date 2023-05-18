package hcmute.edu.booking.service;

import hcmute.edu.booking.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
  List<User> getAllUser();

  Optional<User> findByEmail(String email);

  boolean existByPhone(String phone);

  Object insertUser(User newUser);

  User updateUser(User newUser, String email);

  boolean existById(String email);

  void deleteUser(String email);

  User findUserByEmail(String username);

  User save(User user);

  User signupUser(User newUser);

  List<User> getAllAdmin();

  List<User> getAllStaff();

  List<User> getAllCustomer();

  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

  boolean isMatchPassword(String password, String userPassword);

  User updatePassword(User user, String newPassword);


  boolean changePass(String email, String oldPassword, String newPassword);
}
