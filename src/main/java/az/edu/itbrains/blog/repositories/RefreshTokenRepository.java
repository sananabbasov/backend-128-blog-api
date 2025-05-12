package az.edu.itbrains.blog.repositories;

import az.edu.itbrains.blog.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long id);

    Optional<RefreshToken> findByToken(String token);
}
