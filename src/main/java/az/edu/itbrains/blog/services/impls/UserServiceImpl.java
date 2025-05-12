package az.edu.itbrains.blog.services.impls;

import az.edu.itbrains.blog.dtos.auth.RegisterDto;
import az.edu.itbrains.blog.models.Role;
import az.edu.itbrains.blog.models.User;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.repositories.UserRepository;
import az.edu.itbrains.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse registerUser(RegisterDto registerDto) {

        try {
            User findUser = userRepository.findByEmail(registerDto.getEmail());
            if (findUser != null){
                return new ApiResponse("User already exist.", false, HttpStatus.OK);
            }

            String password = passwordEncoder.encode(registerDto.getPassword());
            User user = new User();
            user.setEmail(registerDto.getEmail());
            user.setFirstName(registerDto.getFirstName());
            user.setLastName(registerDto.getLastName());
            user.setPassword(password);
            userRepository.save(user);
            return new ApiResponse("User registered successfully", true, HttpStatus.CREATED);

        }catch (Exception e){
            return new ApiResponse(e.getMessage(), false, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public ApiResponse addRoleToUser(String email, Role role) {
        try {
            User user = userRepository.findByEmail(email);
            Set<Role> roles = user.getRoles();
            roles.add(role);
            userRepository.save(user);
            return new ApiResponse("Role assigned successfully", true, HttpStatus.OK);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(), false, HttpStatus.BAD_REQUEST);
        }
    }


}
