package tech.loga.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Authentication implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            var userDetails = restTemplate
                    .getForObject("http://AUTHENTICATION-SERVICE//session/?token="+username, AuthenticatedUser.class);

            assert userDetails!=null;

            return userDetails;
        }catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }
}
