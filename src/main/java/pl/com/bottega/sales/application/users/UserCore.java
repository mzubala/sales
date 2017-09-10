package pl.com.bottega.sales.application.users;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserCore implements User {

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
