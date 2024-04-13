package tech.loga.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tech.loga.user.User;
import tech.loga.user.UserRepository;

import java.util.Optional;

@Component
public class Authentication implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetails = userRepository.findByUsernameIgnoreCase(username);
        return userDetails.map(User::new)
                .orElseThrow(()->new UsernameNotFoundException("User cannot been authenticated !!!"));
    }
}
