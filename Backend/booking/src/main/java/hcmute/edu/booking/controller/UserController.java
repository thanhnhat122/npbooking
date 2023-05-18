package hcmute.edu.booking.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.model.User;
import hcmute.edu.booking.model.VerificationToken;
import hcmute.edu.booking.service.UserService;
import hcmute.edu.booking.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(path = "/api/User")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @GetMapping("")
    DataResponse getAllUser() {
        List<User> userList = userService.getAllUser();
        if (userList.size() > 0) {
            return new DataResponse(userList);
        }
        throw new RuntimeException("No User found");
    }

    @GetMapping("/admin")
    DataResponse getAllAdmin() {
        List<User> userList = userService.getAllAdmin();
        if (userList.size() > 0) {
            return new DataResponse(userList);
        }
        throw new RuntimeException("No User found");
    }

    @GetMapping("/staff")
    DataResponse getAllStaff() {
        List<User> userList = userService.getAllStaff();
        if (userList.size() > 0) {
            return new DataResponse(userList);
        }
        throw new RuntimeException("No User found");
    }

    @GetMapping("/customer")
    DataResponse getAllCustomer() {
        List<User> userList = userService.getAllCustomer();
        if (userList.size() > 0) {
            return new DataResponse(userList);
        }
        throw new RuntimeException("No User found");
    }


    @PostMapping("/getUser")
    DataResponse findByEmail(@RequestParam String email) {
        Optional<User> foundUser = userService.findByEmail(email);
        if (foundUser.isPresent()) {
            return new DataResponse(foundUser);
        }
        throw new RuntimeException("Cannot find User with email = " + email);
    }

    @PostMapping("")
    DataResponse insertUser(@RequestBody @Validated User newUser, BindingResult result) {
        if (!userService.existById(newUser.getEmail())) {
            if (!result.hasErrors()) {
                return new DataResponse(userService.insertUser(newUser));
            } else {
                throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
            }
        } else {
            throw new RuntimeException("Email has exist!");
        }
    }

    @PutMapping("")
    DataResponse updateUser(@RequestBody @Validated User newUser, BindingResult result, @RequestParam String email) {
        if (!result.hasErrors()) {
            User updateUser = userService.updateUser(newUser, email);
            return new DataResponse(updateUser);
        } else {
            throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
        }
    }

    @DeleteMapping("")
    DataResponse deleteUser(@RequestParam String email) {
        if (userService.existById(email)) {
            userService.deleteUser(email);
            return new DataResponse("");
        } else {
            throw new RuntimeException("Cannot find User with id =" + email + " to delete");
        }

    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refreshToken = authorizationHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            String username = decodedJWT.getSubject();
            User user = userService.findUserByEmail(username);

            String accessToken = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", Collections.singletonList(user.getRole().toString()))
                    .sign(algorithm);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", refreshToken);

            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping("/signup")
    DataResponse SignUp(@RequestBody @Validated User newUser, BindingResult result) {
        if (!userService.existById(newUser.getEmail())) {
            if (!userService.existByPhone(newUser.getPhone())) {
                if (!result.hasErrors()) {
                    return new DataResponse(userService.signupUser(newUser));
                } else {
                    throw new RuntimeException(Objects.requireNonNull(result.getFieldError()).toString());
                }
            } else {
                throw new RuntimeException("Phone exist!");
            }
        } else {
            throw new RuntimeException("Email exist!");
        }
    }

    @PostMapping("/matchPassword")
    DataResponse isMatchPassword(@RequestParam String password, @RequestParam String userPassword) {
        boolean decodePassword = userService.isMatchPassword(password, userPassword);
        return new DataResponse(decodePassword);
    }

    @PostMapping("/updatePassword")
    DataResponse updatePassword(@RequestBody @Validated User user, @RequestParam String newPassword) {
        return new DataResponse(userService.updatePassword(user, newPassword));
    }

    @PostMapping("/changePass")
    DataResponse changePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        if (userService.changePass(email, oldPassword, newPassword))
            return new DataResponse("Đổi mật khẩu thành công!");
        else
            throw new RuntimeException("Đổi mật thất bại!");
    }

    @GetMapping("/activation")
    DataResponse activation(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenService.findByToken(token);
        if (verificationToken == null) {
            throw new RuntimeException("Your verification token is invalid");
        } else {
            User user = verificationToken.getUser();
            if (!user.isEnabled()) {
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
                if (verificationToken.getExpiryDate().before(currentTimestamp)) {
                    throw new RuntimeException("Your verification token has expired");
                } else {
                    user.setEnabled(true);
                    userService.save(user);
                    return new DataResponse("Your account is successfully activated");
                }
            } else {
                return new DataResponse("Your account is already activated");
            }
        }
    }
}
