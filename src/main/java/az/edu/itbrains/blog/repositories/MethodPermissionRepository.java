package az.edu.itbrains.blog.repositories;

import az.edu.itbrains.blog.models.MethodPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MethodPermissionRepository extends JpaRepository<MethodPermission, Long> {
    MethodPermission findByName(String methodName);
}
