package pl.com.bottega.sales.domain;

import java.util.Random;

public class OrderItem {

    private Long productId;
    private Long itemsCount;

    public OrderItem() {productId = new Random().nextLong();}

}
