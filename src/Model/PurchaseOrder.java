package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseOrder implements Serializable {
    private Customer orderingCustomer;
    private ArrayList<Product> productList;
    private LocalDate orderDate;

    public PurchaseOrder(Customer orderingCustomer, ArrayList<Product> productList) {
        this.orderingCustomer = orderingCustomer;
        this.productList = productList;
        this.orderDate = LocalDate.now();
    }

    public PurchaseOrder() {
    }

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderingCustomer=" + orderingCustomer +
                ", productList=" + productList +
                ", orderDate=" + orderDate +
                '}';
    }
}
