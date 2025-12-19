package wmad.com.wmadwork.security;
import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Use Lombok to handle final field injection
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService; // Renamed for consistency


    // The constructor is no longer needed with @RequiredArgsConstructor and final fields

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
// ... inside doFilterInternal
        String token = extractTokenFromRequest(request);
        System.out.println("DEBUG: Extracted token: " + (token != null ? "YES" : "NO")); // Check step 1

// 1. Check if token is present AND valid
        if (token != null) {
            boolean isValid = jwtProvider.validateToken(token);
            System.out.println("DEBUG: Token valid: " + isValid); // Check step 2

            if (isValid) {
                // ... rest of the code
                System.out.println("DEBUG: Authentication set successfully!"); // Check step 3
            }
        }


        // 1. Check if token is present AND valid
        if (token != null && jwtProvider.validateToken(token)) {
            String username = jwtProvider.getClaims(token).getSubject();

            // 2. Check if username is present AND no authentication exists in the context yet
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // 3. Create the authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), // Use the username as the principal
                                null,
                                userDetails.getAuthorities());

                // 4. Set the authentication object in the Security Context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continue the filter chain
        if (token == null || !jwtProvider.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Correctly check the URI to skip the filter for public endpoints (like login/register)
        String uri = request.getRequestURI();
        return uri.startsWith("/api/auth/");
    }

    private  String  extractTokenFromRequest(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        System.out.println(header);
        // Corrected logic: only return the substring if the header is valid
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}