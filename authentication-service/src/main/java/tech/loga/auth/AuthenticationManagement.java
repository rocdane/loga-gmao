package tech.loga.auth;

public interface AuthenticationManagement {
    String authenticate(AuthenticationRequest authenticationRequest);
    boolean authenticate(String token);
}