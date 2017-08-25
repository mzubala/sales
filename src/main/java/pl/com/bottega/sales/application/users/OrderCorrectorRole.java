package pl.com.bottega.sales.application.users;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class OrderCorrectorRole extends UserRole {

    @ElementCollection
    private Collection<String> correctedOrders = new ArrayList<>();

    public void orderCorrected(String nr) {
        correctedOrders.add(nr);
    }

}
