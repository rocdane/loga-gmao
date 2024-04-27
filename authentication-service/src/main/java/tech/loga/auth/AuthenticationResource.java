package tech.loga.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import tech.loga.vendor.JwtService;

@Service
public class AuthenticationResource implements AuthenticationManagement {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationResource(JwtService jwtService,
                                  AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public String authenticate(AuthenticationRequest authenticationRequest) {
        boolean authenticated =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()))
                        .isAuthenticated();
        if(authenticated){
            return jwtService.generateToken(authenticationRequest.username());
        }else{
            throw new AuthenticationErrorException("Failed to establish session because user does not exists!!!");
        }
    }

    @Override
    public boolean authenticate(String token) {
        return jwtService.isTokenValid(token);
    }
}
