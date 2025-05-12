package az.edu.itbrains.blog.repositories;

import az.edu.itbrains.blog.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameIgnoreCase(String name);
}
