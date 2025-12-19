package wmad.com.wmadwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // CRITICAL: Postman needs this to read the fields
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoryResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String message;
}