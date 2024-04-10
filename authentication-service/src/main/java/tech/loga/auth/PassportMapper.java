package tech.loga.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import tech.loga.user.User;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PassportMapper implements Function<User, Passport> {

    @Override
    public Passport apply(User user) {
        return new Passport(
                user.getUsername(),
                user.getActive(),
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }
}
