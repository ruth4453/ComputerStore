package View;

import Controler.Backend_dao_List;
import Model.Product;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class ManageCatalogForm {
    private JButton insertProductButton;
    private JButton deleteProductButton;
    private JList list1;
    public JPanel panel;
   public static DefaultListModel<Product> AllProductsListModel;



    public ManageCatalogForm() {
        AllProductsListModel = new DefaultListModel();
        list1.setModel(AllProductsListModel);

        insertProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("הוספת מוצר");
                frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        this.RefreshProductList();



        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   list1.getSelectedValuesList();
                for (Object p :list1.getSelectedValuesList()) {
                    AllProductsListModel.removeElement(p);
                    try {
                        Backend_dao_List.getSingleton().RemoveProduct((Product) p);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }


    public void RefreshProductList() {
        try {
            AllProductsListModel.clear();
            HashSet<Product> allProducts = Backend_dao_List.getSingleton().getAllProducts();
            for (Product p:allProducts)
                  {
                      AllProductsListModel.addElement(p);
                  }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    }
