package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import peaksoft.models.AuthInfo;

import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfo,Long> {


   Optional<AuthInfo> findByEmail(String email);
   Boolean existsByEmail(String email);
}
