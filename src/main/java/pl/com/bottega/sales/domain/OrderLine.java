package pl.com.bottega.sales.domain;

public class OrderLine {

    private ProductData productData;
    private Integer count;

    OrderLine() {}

    public OrderLine(ProductData productData, Integer count) {
        this.productData = productData;
        this.count = count;
    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public boolean hasProduct(Long productId) {
        return productData.getProductId().equals(productId);
    }
}
