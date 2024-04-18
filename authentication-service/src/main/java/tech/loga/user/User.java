package tech.loga.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable, UserDetails
{
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "username", unique = true, length = 50)
    private java.lang.String username;

    @Column(name = "password")
    private java.lang.String password;

    @Column(name = "role")
    private java.lang.String role;

    @Column(name = "is_active")
    private Boolean active = true;

    public User(User user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.role=user.getRole();
        this.active=user.getActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public java.lang.String getPassword() {
        return this.password;
    }

    @Override
    public java.lang.String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isCredentialsNonExpired();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return getActive();
    }
}