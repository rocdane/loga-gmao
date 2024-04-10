package tech.loga.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.loga.user.User;
import tech.loga.user.UserNotFoundException;
import tech.loga.user.UserResource;

@Service
public class SessionResource implements IPassport {

    private final UserResource userResource;
    private final PasswordEncoder passwordEncoder;
    private final PassportMapper passportMapper;

    @Autowired
    public SessionResource(UserResource userResource, PasswordEncoder passwordEncoder,
                           PassportMapper passportMapper) {
        this.userResource = userResource;
        this.passwordEncoder = passwordEncoder;
        this.passportMapper = passportMapper;
    }

    @Override
    public Passport authenticate(Credentials credentials) {
        User user = userResource.find(credentials.username());
        if(user!=null){
            if(!passwordEncoder.matches(credentials.password(), user.getPassword())){
                throw new SessionErrorException("Password is not correct");
            } else if(!user.getActive()){
                throw new SessionErrorException("Access not granted");
            }
            return passportMapper.apply(user);
        }else {
            throw new SessionErrorException("Failed to establish session because user does not exists!!!");
        }
    }
}
