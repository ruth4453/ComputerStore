package View;

import Controler.Backend_dao_List;
import Model.Customer;
import Model.Product;
import Model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StoreManagerGUIView {
    public static Backend_dao_List Controller;
    private JButton insertCustomerButton;
    private JButton productButton;
    private JButton toDoOrderButton;
    private JButton theAllOrdersButton;
    private JPanel panel;

    public StoreManagerGUIView() {

        insertCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new  InsertCustomer().setVisible(true);

            }

        });
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("");
                frame.setContentPane(new ManageCatalogForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        toDoOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        theAllOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("ViewPurchasesForm");
                frame.setContentPane(new ViewPurchasesForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                frame.pack();
                frame.setVisible(true);
            }
        });

    }

    public static void main(String[] args) throws IOException{
        JFrame frame = new JFrame("StoreManagerGUIView");
        frame.setContentPane(new StoreManagerGUIView().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    Backend_dao_List.getSingleton().loadDataFromFile();

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Backend_dao_List.getSingleton().savaDataToFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    }

