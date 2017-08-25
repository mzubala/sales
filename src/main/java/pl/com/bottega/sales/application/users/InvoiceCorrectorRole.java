package pl.com.bottega.sales.application.users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class InvoiceCorrectorRole extends UserRole {

    @ElementCollection
    private Collection<String> correctedInvoices = new ArrayList<>();

    public void invoiceCorrected(String nr) {
        correctedInvoices.add(nr);
    }

}
