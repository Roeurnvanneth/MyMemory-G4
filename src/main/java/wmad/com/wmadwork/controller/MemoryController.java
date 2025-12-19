package wmad.com.wmadwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import wmad.com.wmadwork.dto.CreateMemoryRequest;
import wmad.com.wmadwork.dto.MemoryResponse;
import wmad.com.wmadwork.service.MemoryService;
import java.util.List;

@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping("")
    public ResponseEntity<MemoryResponse> createMemory(
            @AuthenticationPrincipal String authenticatedUsername,
            @RequestBody CreateMemoryRequest request) {

        // 1. Get the response from the service
        MemoryResponse response = memoryService.createMemory(request, authenticatedUsername);

        // 2. Return with .body(response) so Postman shows the data
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/search")
    public ResponseEntity<List<MemoryResponse>> search(@RequestParam String query) {
        // Return the list in the body
        return ResponseEntity.ok(memoryService.searchMemories(query));
    }
}