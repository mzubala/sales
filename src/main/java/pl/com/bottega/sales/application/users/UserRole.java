package pl.com.bottega.sales.application.users;

import java.time.Clock;

public abstract class UserRole implements User {

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
