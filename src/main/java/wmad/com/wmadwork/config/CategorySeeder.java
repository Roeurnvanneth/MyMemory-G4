package wmad.com.wmadwork.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import wmad.com.wmadwork.entity.Category;
import wmad.com.wmadwork.repository.CategoryRepository;

@Component
public class CategorySeeder implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    
    public CategorySeeder(CategoryRepository categoryRepository) {
    	this.categoryRepository = categoryRepository;
    }@Override
    public void run(String... args) throws Exception {
        List<String> defaultNames = Arrays.asList("Tour", "Party", "Love", "Family", "Friends", "Study", "Personal");

        for (String name : defaultNames) {
            if (!categoryRepository.existsByName(name)) {
                categoryRepository.save(new Category(null, name));
            }
        }
    }
    
}
