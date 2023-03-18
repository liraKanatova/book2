package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;
import peaksoft.dto.RegisterRequest;
import peaksoft.service.AuthService;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest){
return authService.register(registerRequest);
    }
    @PostMapping("/authenticate")
    public AuthResponse register(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }
}
