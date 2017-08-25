package pl.com.bottega.sales.application.users;

import pl.com.bottega.common.domain.BaseAggregateRoot;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserCore extends BaseAggregateRoot implements User {

    @OneToMany(mappedBy = "userCore", cascade = CascadeType.ALL, orphanRemoval =  true)
    private Set<UserRole> roles = new HashSet<>();

    private String login, password;

    private LocalDateTime lastLogin;

    UserCore() {
    }

    UserCore(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public <T extends UserRole> T getRole(Class<T> roleClass) {
        return (T) roles.stream().
                filter(role -> role.getClass().equals(roleClass)).
                findFirst().
                orElseThrow(() -> new RuntimeException("User has no such role"));
    }

    @Override
    public void addRole(UserRole userRole) {
        roles.add(userRole);
    }

    @Override
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public void saveLastLoginDate(Clock clock) {
        this.lastLogin = LocalDateTime.now(clock);
    }
}
