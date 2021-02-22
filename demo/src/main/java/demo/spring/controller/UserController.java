package demo.spring.controller;

import demo.spring.annotations.AllowAnonymous;
import demo.spring.models.mapping.UserMapping;
import demo.spring.models.request.LoginRequest;
import demo.spring.models.response.LoginResponse;
import demo.spring.services.abstraction.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @AllowAnonymous
    @PostMapping(value = "/auth")
    public LoginResponse doLogin(@RequestBody LoginRequest request) {
        return userService.authenticate(request);
    }

    @AllowAnonymous
    @GetMapping(value="/list-all-users")
    public List<UserMapping> getUsers(String loginNamePattern){
        return userService.getUsersByLoginNamePattern(loginNamePattern);
    }
}
