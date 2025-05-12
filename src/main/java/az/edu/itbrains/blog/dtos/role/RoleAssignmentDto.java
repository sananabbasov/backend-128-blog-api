package az.edu.itbrains.blog.dtos.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAssignmentDto {
    private String email;
    private Long roleId;
}
