package vuejs.springboot.mysql.backend.domain.model.account;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "username")
public class SimpleAccount implements UserDetails {

    private Long id;
    private String username;
    private String password;

    private SimpleAccount(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public static UserDetails create(Account findAccount) {
        return new SimpleAccount(findAccount.getId(), findAccount.getUsername(), findAccount.getEmailAddress());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
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

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}

