package az.edu.itbrains.blog.services;

import az.edu.itbrains.blog.models.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);
    boolean removeToken(String email);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
}