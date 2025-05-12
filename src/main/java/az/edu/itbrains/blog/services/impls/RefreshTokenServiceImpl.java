package az.edu.itbrains.blog.services.impls;

import az.edu.itbrains.blog.models.RefreshToken;
import az.edu.itbrains.blog.models.User;
import az.edu.itbrains.blog.repositories.RefreshTokenRepository;
import az.edu.itbrains.blog.services.RefreshTokenService;
import az.edu.itbrains.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;



    public RefreshToken createRefreshToken(String username){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userService.findUserByName(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public boolean removeToken(String email) {
        try {
            User user = userService.findUserByName(email);
            if (user != null){
                RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId()).orElseThrow();
                refreshTokenRepository.delete(refreshToken);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }


    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}