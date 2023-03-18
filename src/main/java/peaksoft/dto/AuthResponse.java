package peaksoft.dto;

import lombok.Builder;

@Builder
public class AuthResponse {
    private String email;
    private String token;
}
