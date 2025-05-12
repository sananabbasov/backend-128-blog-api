package az.edu.itbrains.blog.services;

import az.edu.itbrains.blog.dtos.auth.RegisterDto;
import az.edu.itbrains.blog.models.Role;
import az.edu.itbrains.blog.models.User;
import az.edu.itbrains.blog.payloads.ApiResponse;

import java.util.Set;

public interface UserService {
    ApiResponse registerUser(RegisterDto registerDto);
    User findUserByName(String username);
    ApiResponse addRoleToUser(String email, Role role);
}
