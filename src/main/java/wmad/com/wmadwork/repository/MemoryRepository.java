package wmad.com.wmadwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wmad.com.wmadwork.entity.Memory;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);
}
