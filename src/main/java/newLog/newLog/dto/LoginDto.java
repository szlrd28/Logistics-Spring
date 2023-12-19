package newLog.newLog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


public class LoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public LoginDto( @NotBlank String username,  @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}