package az.edu.itbrains.blog.controllers;


import az.edu.itbrains.blog.dtos.role.RoleAssignmentDto;
import az.edu.itbrains.blog.dtos.role.RoleCreateDto;
import az.edu.itbrains.blog.dtos.role.RoleDto;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;



    @GetMapping("/getall")
    public ResponseEntity<List<RoleDto>> getAll(){
        List<RoleDto> roles = roleService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody RoleCreateDto roleCreateDto){
        ApiResponse response = roleService.createRole(roleCreateDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/assign-role")
    public ResponseEntity<ApiResponse> assignRole(@RequestBody RoleAssignmentDto roleAssignmentDto){
        ApiResponse response = roleService.assignRoleToUser(roleAssignmentDto);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
