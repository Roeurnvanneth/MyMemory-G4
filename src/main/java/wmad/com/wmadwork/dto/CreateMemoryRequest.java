package wmad.com.wmadwork.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemoryRequest {
    private Long id;
    private String title;
    private String description;
    private Long categoryId;
}
