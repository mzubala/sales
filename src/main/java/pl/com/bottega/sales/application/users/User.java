package pl.com.bottega.sales.application.users;

import java.time.Clock;

public interface User {

    <T extends UserRole> T getRole(Class<T> roleClass);

    void addRole(UserRole userRole);

    void changePassword(String newPassword);

    void saveLastLoginDate(Clock clock);

}
