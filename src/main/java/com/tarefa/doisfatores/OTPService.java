package com.tarefa.doisfatores;

import org.jboss.aerogear.security.otp.Totp;
import org.springframework.stereotype.Service;

@Service
public class OTPService {
    public String generateOTP(String secret) {
        Totp totp = new Totp(secret);
        return totp.now();
    }

    public boolean verifyOTP(String secret, String otp) {
        Totp totp = new Totp(secret);
        return totp.verify(otp);
    }
}
