package tech.loga.auth;

public interface IPassport {
    Passport authenticate(Credentials credentials);
}