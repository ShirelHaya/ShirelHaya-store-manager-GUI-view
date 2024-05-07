package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrder implements Serializable {
    Customer orderingCustomer;
    private ArrayList<Product> productsList;    //collaction
    private LocalDate orderDate = LocalDate.now();

    public Customer getOrderingCustomer() {
        return orderingCustomer;
    }

    public void setOrderingCustomer(Customer orderingCustomer) {
        this.orderingCustomer = orderingCustomer;
    }

    public ArrayList<Product> getProductsList() {

        return productsList;
    }

    public void setListProd(List<Product> listProd) {
        this.productsList = (ArrayList<Product>) listProd;
    }

    public void setProductsList(ArrayList<Product> productsList) {
        this.productsList = productsList;
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
                " orderingCustomer=" + orderingCustomer +
                ", productsList=" + productsList +
                ", orderDate=" + orderDate +
                '}';
    }
}
