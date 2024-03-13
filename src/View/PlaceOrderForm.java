package View;

import Model.Customer;
import Model.Product;
import Model.ProductType;
import Controler.Backend_dao_List;
import Model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderForm {
    JPanel panel;
    private JComboBox comboBoxc;
    private JComboBox comboBoxp;
    private JButton addToOrderButton;
    private JButton removeSelectedProductsButton;
    private JButton calculateTotalButton;
    private JButton submitOrderButton;
    private JList list1;
    private JLabel price;

    DefaultListModel SelectedProductsListModel=new DefaultListModel();

    DefaultComboBoxModel CustomerModel;
    DefaultComboBoxModel ProductModel;
    public PlaceOrderForm() {
        //השמת המוצרים בקומבובוקס
        try {
            ProductModel = new DefaultComboBoxModel(Backend_dao_List.getSingleton().getAllProducts().toArray());
            comboBoxp.setModel(ProductModel);
            comboBoxp.setSelectedIndex(0);
            CustomerModel = new DefaultComboBoxModel(Backend_dao_List.getSingleton().getAllCustomers().values().toArray());
            comboBoxc.setModel(CustomerModel);
            comboBoxc.setSelectedIndex(0);
            list1.setModel(SelectedProductsListModel);
        }


        catch (Exception e) {
              e.getMessage();
        }

        //הוספה לליסט
        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SelectedProductsListModel.addElement((Product) comboBoxp.getSelectedItem());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
//מחיקת מוצר מהליסט
        removeSelectedProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object objProduct : list1.getSelectedValues()) {
                    SelectedProductsListModel.removeElement(objProduct);
                }
            }
        });

        //שמירת ההזמנה
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Customer customer=(Customer)comboBoxc.getSelectedItem();
                    ArrayList<Product> productsList=new ArrayList<>();
                    for (Object p:SelectedProductsListModel.toArray()) {
                         productsList.add((Product)p);
                    }
                    PurchaseOrder newOrder=new PurchaseOrder(customer,productsList);
                    Backend_dao_List.getSingleton().AddPurchaseOrder(newOrder);
                    JOptionPane.showMessageDialog(null, "Product added successfully");
                    System.out.println(Backend_dao_List.getSingleton().getPurchaseOrders());
                    SelectedProductsListModel.clear();
                }

                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                            JOptionPane.ERROR_MESSAGE);
                   ex.getMessage();
                }

            }
        });

//חישוב מחיר ההזמנה
        calculateTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        Product[] products = new Product[SelectedProductsListModel.size()];
                        SelectedProductsListModel.copyInto(products);
                        Float total =Backend_dao_List.getSingleton().CalcProductsTotalCost(products);
                        price.setText(total.toString());
                    } catch (Exception  ex) {
                        Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE,null, ex);

                    }

               }
        });

    }

}
