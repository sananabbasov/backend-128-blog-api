package az.edu.itbrains.blog.services.impls;

import az.edu.itbrains.blog.dtos.role.RoleAssignmentDto;
import az.edu.itbrains.blog.dtos.role.RoleCreateDto;
import az.edu.itbrains.blog.dtos.role.RoleDto;
import az.edu.itbrains.blog.exceptions.ResourceNotFoundException;
import az.edu.itbrains.blog.models.Role;
import az.edu.itbrains.blog.models.User;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.repositories.RoleRepository;
import az.edu.itbrains.blog.services.RoleService;
import az.edu.itbrains.blog.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse assignRoleToUser(RoleAssignmentDto roleAssignmentDto) {
        Role role = roleRepository.findById(roleAssignmentDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("Role","id", roleAssignmentDto.getRoleId()));
        ApiResponse response = userService.addRoleToUser(roleAssignmentDto.getEmail(), role);
        return response;
    }



    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtoList = roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
        return roleDtoList;
    }

    @Override
    public ApiResponse createRole(RoleCreateDto roleCreateDto) {
       try {
           Role findRole = roleRepository.findByNameIgnoreCase(roleCreateDto.getName());
           if (findRole != null){
               return new ApiResponse("Role is already exist.", false, HttpStatus.OK);
           }
           Role role = new Role();
           role.setName(roleCreateDto.getName());
           roleRepository.save(role);
           return new ApiResponse("Role created successfully", true, HttpStatus.CREATED);
       }catch (Exception e){
           return new ApiResponse(e.getMessage(), false, HttpStatus.BAD_REQUEST);
       }
    }
}
