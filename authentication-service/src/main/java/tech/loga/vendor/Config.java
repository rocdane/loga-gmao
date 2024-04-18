package tech.loga.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tech.loga.user.UserRepository;

@Configuration
public class Config {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(()->new UsernameNotFoundException("User cannot been authenticated !!!"));
    }
}
