package az.edu.itbrains.blog.controllers;


import az.edu.itbrains.blog.dtos.auth.JwtResponseDto;
import az.edu.itbrains.blog.dtos.auth.LoginDto;
import az.edu.itbrains.blog.dtos.auth.RefreshTokenRequestDto;
import az.edu.itbrains.blog.dtos.auth.RegisterDto;
import az.edu.itbrains.blog.models.RefreshToken;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.security.JwtService;
import az.edu.itbrains.blog.services.RefreshTokenService;
import az.edu.itbrains.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterDto registerDto){

        ApiResponse response = userService.registerUser(registerDto);

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody LoginDto authRequestDTO){
        refreshTokenService.removeToken(authRequestDTO.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getEmail());
            return JwtResponseDto.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getEmail()))
                    .token(refreshToken.getToken())
                    .build();

        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }


    @PostMapping("/refreshToken")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtResponseDto.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }
}
