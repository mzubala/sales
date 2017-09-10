package pl.com.bottega.sales.application.users;

import java.util.ArrayList;
import java.util.Collection;

public class InvoiceCorrectorRole extends UserRole {

    private Collection<String> correctedInvoices = new ArrayList<>();

    public void invoiceCorrected(String nr) {
        correctedInvoices.add(nr);
    }

}
