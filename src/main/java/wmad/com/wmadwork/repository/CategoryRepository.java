package wmad.com.wmadwork.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import wmad.com.wmadwork.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
   
}
