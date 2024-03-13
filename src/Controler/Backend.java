package Controler;

import Model.Customer;
import Model.Product;
import Model.PurchaseOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface Backend {

    void AddCustomer(Customer c) throws  Exception;
    void AddPurchaseOrder(PurchaseOrder p) throws Exception;
    void AddProduct(Product c) throws  Exception;
    HashMap<Long, Customer> getAllCustomers()throws Exception;
    HashSet<Product> getAllProducts()throws  Exception;
    void RemoveProduct(Product c) throws  Exception;

    ArrayList<Product> getCustomersOrders(Customer c)throws  Exception;
    Float CalcProductsTotalCost(Product [] products)throws  Exception;

}
