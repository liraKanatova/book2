package peaksoft.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.AuthRequest;
import peaksoft.dto.AuthResponse;
import peaksoft.dto.RegisterRequest;
import peaksoft.enums.Role;
import peaksoft.models.AuthInfo;
import peaksoft.repository.AuthInfoRepository;
import peaksoft.service.AuthService;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthInfoRepository authInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (authInfoRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException(String.format("User with email: %s already exists", registerRequest.getEmail()));
        }
            AuthInfo authInfo = AuthInfo.builder()
                    .email(registerRequest.getEmail())
                    .role(registerRequest.getRole())
                    .password(passwordEncoder.encode(registerRequest.getPassword()))
                    .build();
            authInfoRepository.save(authInfo);
            String token = jwtUtil.generateToken(authInfo);

            return AuthResponse.builder()
                    .token(token)
                    .email(authInfo.getEmail())
                    .build();
        }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );
        AuthInfo authInfo = authInfoRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(()->new NoSuchElementException(String.format("User with email: %s doesn't exists",authRequest.getEmail())) );
        String token = jwtUtil.generateToken(authInfo);

        return AuthResponse.builder()
                .token(token)
                .email(authInfo.getEmail())
                .build();
    }
    @PostConstruct
    public void init(){
        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail("admin@gmail.com");
        authInfo.setPassword(passwordEncoder.encode("admin"));
        authInfo.setRole(Role.ADMIN);
        if(!authInfoRepository.existsByEmail(authInfo.getEmail())){
            authInfoRepository.save(authInfo);
        }
    }

}
