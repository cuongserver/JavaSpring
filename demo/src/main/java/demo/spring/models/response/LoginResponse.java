package demo.spring.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse extends BaseResponse {
    private String displayName;
    private String jwt;
    public LoginResponse(String message) {
        super(message);
    }
    public LoginResponse(String message, String displayName, String jwt) {
        super(message);
        this.displayName = displayName;
        this.jwt = jwt;
    }
}
