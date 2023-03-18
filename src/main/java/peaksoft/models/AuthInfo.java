package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import peaksoft.enums.Role;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "auth_Infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
    ,generator = "authInfo_id_gen")
    @SequenceGenerator(name = "authInfo_id_gen",
            sequenceName = "authInfo_id_seq",
            allocationSize = 1)

    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
