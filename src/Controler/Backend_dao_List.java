package Controler;

import Model.*;

import java.io.*;
import java.util.*;

public class Backend_dao_List implements  Backend{

    private Map<Long,Customer>_customers;
    private Set<Product>_product;
    private List<PurchaseOrder>_purchaseOrder;

    private  static  Backend_dao_List singleton;
    public static Backend_dao_List getSingleton(){
        if(Objects.isNull(singleton))
            singleton=new Backend_dao_List();
        return  singleton;}


    private Backend_dao_List() {
        _customers=new HashMap<>();
        _product=new HashSet<>();
        _purchaseOrder=new ArrayList<>();

        _product.add(new HardwereProduct(1,"1","hsajgb",12,23));
        _product.add(new SoftWareProduct(2,"hn","dg",3,4));

        _customers.put(Long.valueOf(1),new Customer(1,"avi","hdskj"));
        _customers.put(Long.valueOf(12),new Customer(12,"avijt","hdfbxskj"));

    }
    public List<PurchaseOrder> getPurchaseOrders(){
        return _purchaseOrder;
    }

    @Override
    public void AddCustomer(Customer c) throws Exception {
        _customers.put(c.getId(),c);

    }

    @Override
    public void AddPurchaseOrder(PurchaseOrder p) throws Exception {
        _purchaseOrder.add(p);
    }

    @Override
    public void AddProduct(Product c) throws Exception {
        _product.add(c);
    }

    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>)_customers;
    }

    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) _product;
    }

    @Override
    public void RemoveProduct(Product c) throws Exception {
        this._product.remove(c);
    }


    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList<Product> ret=new ArrayList<Product>();
        for (PurchaseOrder po :
                _purchaseOrder) {
            if (po.getOrderingCustomer().equals(c))
                ret.addAll(po.getProductList());
        }
        return ret;
    }

    @Override
    public Float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum=0;
        for (Product p:products)
              {
            sum+= p.getPrice();
        }
        return sum;
    }
    public void	savaDataToFile() throws IOException {
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("data"));

        oos.writeObject(_customers);
        oos.writeObject(_product);
        oos.writeObject(_purchaseOrder);

        oos.close();

    }
    public void loadDataFromFile() throws Exception {
        ObjectInputStream ois=null;
        try {
            ois=new ObjectInputStream(new FileInputStream("data"));
            _customers = (Map<Long, Customer>) ois.readObject();
            _product = (Set<Product>) ois.readObject();
            _purchaseOrder = (List<PurchaseOrder>) ois.readObject();
        }finally {
            ois.close();
        }

    }
}
