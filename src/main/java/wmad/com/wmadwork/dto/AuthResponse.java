package wmad.com.wmadwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor

public class AuthResponse {
    public AuthResponse() {

	}
	private String token;
    private String message;
    private Long id;
    private String email;
    private String username;
    private boolean enabled;
    private String createdAt;
    private String updatedAt;
}
