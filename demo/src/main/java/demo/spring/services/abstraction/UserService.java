package demo.spring.services.abstraction;

import demo.spring.models.mapping.UserMapping;
import demo.spring.models.request.LoginRequest;
import demo.spring.models.response.LoginResponse;

import java.util.List;


public interface UserService {
    LoginResponse authenticate(LoginRequest request);
    List<UserMapping> getUsersByLoginNamePattern(String phrase);
}
