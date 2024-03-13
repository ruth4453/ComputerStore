package View;

import Controler.Backend_dao_List;
import Model.Customer;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPurchasesForm {
    private JComboBox comboBox1;
    private JList list1;
    JPanel panel;
    private JTextField textField1;
    DefaultListModel <Product> productOrders;

    public ViewPurchasesForm() {
        try {
            DefaultComboBoxModel model = new DefaultComboBoxModel(Backend_dao_List.getSingleton().getAllCustomers().values().toArray());
            comboBox1.setModel(model);
            productOrders=new DefaultListModel();

            list1.setModel(productOrders);
            refreshingList();
            System.out.println(list1.getModel());

            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Product [] pro=new Product[productOrders.size()];
                    productOrders.copyInto(pro);
                    Float tot= null;
                    try {
                        tot = Backend_dao_List.getSingleton().CalcProductsTotalCost(pro);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    textField1.setText(tot.toString());

                }
            });


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshingList();
            }
        });

    }
    public  void refreshingList(){
        try{
            productOrders.clear();
            Customer c=(Customer) comboBox1.getSelectedItem();
            for (Product p:Backend_dao_List.getSingleton().getCustomersOrders(c)   ) {
              productOrders.addElement(p);

            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ViewPurchasesForm");
        frame.setContentPane(new ViewPurchasesForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
