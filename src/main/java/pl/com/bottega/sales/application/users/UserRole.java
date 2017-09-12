package pl.com.bottega.sales.application.users;

import javax.persistence.*;
import java.time.Clock;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserRole implements User {

    @ManyToOne
    private UserCore userCore;

    @Id
    @GeneratedValue
    private Long id;

    public UserRole(UserCore userCore) {
        this.userCore = userCore;
    }

    UserRole() {}

    @Override
    public <T extends UserRole> T getRole(Class<T> roleClass) {
        return userCore.getRole(roleClass);
    }

    @Override
    public void addRole(UserRole userRole) {
        userCore.addRole(userRole);
    }

    @Override
    public void changePassword(String newPassword) {
        userCore.changePassword(newPassword);
    }

    @Override
    public void saveLastLoginDate(Clock clock) {
        userCore.saveLastLoginDate(clock);
    }

    void setUserCore(UserCore userCore) {
        this.userCore = userCore;
    }
}
