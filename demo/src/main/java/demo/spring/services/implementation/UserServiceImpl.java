package demo.spring.services.implementation;

import demo.spring.constants.ApiMessage;
import demo.spring.models.mapping.UserMapping;
import demo.spring.models.persistence.User;
import demo.spring.models.request.LoginRequest;
import demo.spring.models.response.LoginResponse;
import demo.spring.repositories.UserRepository;
import demo.spring.services.abstraction.UserService;
import demo.spring.utilities.CryptoUtility;
import demo.spring.utilities.JwtUtility;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CryptoUtility cryptoUtility;
    private final JwtUtility jwtUtility;

    public UserServiceImpl(UserRepository userRepository, CryptoUtility cryptoUtility, JwtUtility jwtUtility) {
        this.userRepository = userRepository;
        this.cryptoUtility = cryptoUtility;
        this.jwtUtility = jwtUtility;
    }

    @Override
    public LoginResponse authenticate(LoginRequest request) {
        LoginResponse failed = new LoginResponse(ApiMessage.AuthFailed.name());
        User user = userRepository.findByLoginName(request.getLoginName());
        if (user == null) return failed;
        String hashPW = user.getPasswordHash();
        String salt = user.getSalt();
        var computedHash = cryptoUtility.generateHash(request.getPassword(), salt);
        if (!computedHash.equals(hashPW)) return failed;
        var jwt = jwtUtility.generateJwt(user);
        return new LoginResponse(ApiMessage.AuthSuccess.name(), user.getDisplayName(), jwt);
    }

    @Override
    public List<UserMapping> getUsersByLoginNamePattern(String phrase) {
        var list = userRepository.getUsersByLoginNamePhrase(phrase);
        return list;
    }
}
