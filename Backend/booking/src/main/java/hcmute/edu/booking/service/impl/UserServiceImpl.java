package hcmute.edu.booking.service.impl;

import hcmute.edu.booking.SampleData;
import hcmute.edu.booking.model.User;
import hcmute.edu.booking.repository.UserRepository;
import hcmute.edu.booking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static hcmute.edu.booking.model.User.RoleEnum.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SampleData.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationTokenServiceImpl verificationTokenService;
    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public boolean existByPhone(String phone) {
        return userRepository.findByPhone(phone) != null;
    }

    @Override
    public Object insertUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

  @Override
  public User updateUser(User newUser, String email) {
    return userRepository.findById(email)
        .map(user -> {
          user.setEmail(email);
          user.setFirstName(newUser.getFirstName());
          user.setLastName(newUser.getLastName());
          user.setPhone(newUser.getPhone());
          
          user.setRole(newUser.getRole());
          return userRepository.save(user);
        }).orElseGet(() -> {
          newUser.setEmail(email);
          return userRepository.save(newUser);
        });
  }

    @Override
    public boolean existById(String email) {
        return userRepository.existsById(email);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.deleteById(email);
    }

    @Override
    public User findUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User signupUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(ROLE_KH);
        newUser.setEnabled(false);

        Optional<User> saved = Optional.of(save(newUser));
//    return userRepository.save(newUser);
        saved.ifPresent(u -> {
            try {
                String token = UUID.randomUUID().toString();
                verificationTokenService.save(saved.get(), token);

                emailService.sendHtmlMail(u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return saved.get();
    }

    @Override
    public List<User> getAllAdmin() {
        return userRepository.findByRole(ROLE_AD);
    }

    @Override
    public List<User> getAllStaff() {
        return userRepository.findByRole(ROLE_NV);
    }

    @Override
    public List<User> getAllCustomer() {
        return userRepository.findByRole(ROLE_KH);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            logger.info("User found in the database: {}", username);
        }
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("User is not verified");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public boolean isMatchPassword(String password, String userPassword) {
        return passwordEncoder.matches(password, userPassword);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    public boolean changePass(String email, String oldPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            return false;
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return true;
    }

}
