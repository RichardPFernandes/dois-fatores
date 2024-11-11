package com.tarefa.doisfatores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private EmailService emailService;

    @Value("${app.secret}")
    private String secret;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {
        if (userService.authenticate(user.getUsername(), user.getPassword())) {
            String secret = getSecretForUser(user.getUsername());
            String otp = otpService.generateOTP(secret);
            emailService.sendOtpEmail(userService.getUserEmail(user.getUsername()), otp);
            return ResponseEntity.ok("Código OTP enviado para o seu e-mail.");
        }
        return ResponseEntity.badRequest().body("Credenciais inválidas.");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody UserDTO verifyOtpDTO) {
        String secret = getSecretForUser(verifyOtpDTO.getUsername());
        if (otpService.verifyOTP(secret, verifyOtpDTO.getOtp())) {
            return ResponseEntity.ok("Código OTP válido.");
        }
        return ResponseEntity.badRequest().body("Código OTP inválido.");
    }

    private String getSecretForUser(String username) {
        return secret + username;
    }
}
