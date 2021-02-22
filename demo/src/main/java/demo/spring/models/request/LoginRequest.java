package demo.spring.models.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class LoginRequest implements Serializable {
    private String loginName;
    private String password;
    public LoginRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }
}
