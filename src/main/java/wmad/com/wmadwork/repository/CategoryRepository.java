package wmad.com.wmadwork.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import wmad.com.wmadwork.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    Optional<Category> findById(Long categoryId);
   
}
