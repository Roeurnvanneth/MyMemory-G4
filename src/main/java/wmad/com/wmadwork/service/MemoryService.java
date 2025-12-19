package wmad.com.wmadwork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wmad.com.wmadwork.dto.CreateMemoryRequest;
import wmad.com.wmadwork.dto.MemoryResponse;
import wmad.com.wmadwork.entity.Category;
import wmad.com.wmadwork.entity.Memory;
import wmad.com.wmadwork.entity.User;
import wmad.com.wmadwork.exception.ResourceNotFoundException;
import wmad.com.wmadwork.repository.CategoryRepository;
import wmad.com.wmadwork.repository.MemoryRepository;
import wmad.com.wmadwork.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public MemoryResponse createMemory(CreateMemoryRequest request, String authenticatedUsername) {
        User user = userRepository.findByUsername(authenticatedUsername)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + authenticatedUsername));

        Category selectedCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Memory memory = new Memory();
        memory.setTitle(request.getTitle()); // Map Title
        memory.setDescription(request.getDescription()); // Map Description
        memory.setUser(user);
        memory.setCategory(selectedCategory.getName()); // Save category name

        Memory saved = memoryRepository.save(memory);

        // Build response
        MemoryResponse response = new MemoryResponse();
        response.setId(saved.getId());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setCategory(saved.getCategory());
        response.setMessage("Memory created successfully");

        return response;
    }

    public List<MemoryResponse> searchMemories(String query) {
        // Fuzzy search Title OR Category
        List<Memory> memories = memoryRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);

        if (memories.isEmpty()) {
            throw new ResourceNotFoundException("No memories found for: " + query);
        }

        return memories.stream().map(m -> new MemoryResponse(
                m.getId(), m.getTitle(), m.getDescription(), m.getCategory(), null
        )).toList();
    }
}