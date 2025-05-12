package az.edu.itbrains.blog.repositories;

import az.edu.itbrains.blog.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
