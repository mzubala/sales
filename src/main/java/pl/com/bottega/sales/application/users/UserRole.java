package pl.com.bottega.sales.application.users;

import pl.com.bottega.common.domain.BaseEntity;

import javax.persistence.*;
import java.time.Clock;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserRole implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    private UserCore userCore;

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
}
