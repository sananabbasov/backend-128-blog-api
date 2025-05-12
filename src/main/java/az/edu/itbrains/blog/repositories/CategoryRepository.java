package az.edu.itbrains.blog.repositories;

import az.edu.itbrains.blog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNameIgnoreCase(String name);
}
