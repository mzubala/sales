package pl.com.bottega.sales.application.users;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFactoryTest {

    private UserFactory userFactory = new UserFactory();

    @Test
    public void adminShouldHaveInvoiceCorrectorRole() {
        User admin = userFactory.adminUser("test", "test");

        InvoiceCorrectorRole invoiceCorrector = admin.getRole(InvoiceCorrectorRole.class);

        assertThat(invoiceCorrector).isNotNull();
    }

    @Test
    public void adminShouldHaveOrderCorrectorRole() {
        User admin = userFactory.adminUser("test", "test");

        OrderCorrectorRole orderCorrectorRole = admin.getRole(OrderCorrectorRole.class);

        assertThat(orderCorrectorRole).isNotNull();
    }

    @Test
    public void supervisorShouldHaveInvoiceCorrectorRole() {
        User supervisor = userFactory.supervisorUser("test", "test");

        InvoiceCorrectorRole invoiceCorrector = supervisor.getRole(InvoiceCorrectorRole.class);

        assertThat(invoiceCorrector).isNotNull();
    }


    @Test(expected = RuntimeException.class)
    public void standardUserShouldntHaveInvoiceCorrectorRole() {
        User standardUser = userFactory.standardUser("test", "test");

        standardUser.getRole(InvoiceCorrectorRole.class);
    }

    @Test(expected = RuntimeException.class)
    public void standardUserShouldntHaveOrderCorrectorRole() {
        User standardUser = userFactory.standardUser("test", "test");

        standardUser.getRole(OrderCorrectorRole.class);
    }

}
