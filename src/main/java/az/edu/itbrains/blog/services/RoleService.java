package az.edu.itbrains.blog.services;

import az.edu.itbrains.blog.dtos.role.RoleAssignmentDto;
import az.edu.itbrains.blog.dtos.role.RoleCreateDto;
import az.edu.itbrains.blog.dtos.role.RoleDto;
import az.edu.itbrains.blog.payloads.ApiResponse;

import java.util.List;

public interface RoleService {
    ApiResponse assignRoleToUser(RoleAssignmentDto roleAssignmentDto);
    List<RoleDto> getAllRoles();

    ApiResponse createRole(RoleCreateDto roleCreateDto);
}
