package wmad.com.wmadwork.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wmad.com.wmadwork.dto.AuthResponse;
import wmad.com.wmadwork.dto.LoginRequest;
import wmad.com.wmadwork.dto.LoginResponse;
import wmad.com.wmadwork.dto.RegisterRequest;
import wmad.com.wmadwork.service.AuthService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
     public ResponseEntity<AuthResponse> rejister(@RequestBody RegisterRequest req){
    	AuthResponse response = authService.register(req);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
    
    
    
}