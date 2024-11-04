package com.tarefa.doisfatores;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String otp;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email, String otpSecret) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.otp = otp;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getOtp() {
        return otp;
    }
}
