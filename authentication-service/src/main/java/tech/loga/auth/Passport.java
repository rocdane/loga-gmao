package tech.loga.auth;

import java.util.List;

public record Passport(String username, Boolean enabled, List<String> roles) {}
