package peaksoft.service;

import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;
import peaksoft.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
