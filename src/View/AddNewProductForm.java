package View;

import Controler.Backend_dao_List;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewProductForm {
    private JTextField nameb;
    private JTextField descri;
    private JTextField varib;
    private JTextField price;

    private JButton addButton;
    private JComboBox comboBox1;
    public JPanel panel;
    private JLabel variable;
    private JTextField idb;
private  ManageCatalogForm manageCatalogForm;
    public AddNewProductForm(ManageCatalogForm catalogForm){
        this.manageCatalogForm=catalogForm;
        DefaultComboBoxModel model = new DefaultComboBoxModel(ProductType.values());
        comboBox1.setModel(model);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Product newProduct = null;
                    long id = Long.parseLong(idb.getText());
                    String name = nameb.getText();
                    String description=descri.getText();
                    float pricePerUnit = Float.parseFloat(price.getText());
                    if (isInHardwareMode()) {

                        int werrantyPeriod = Integer.parseInt(varib.getText());

                        newProduct =new HardwereProduct( id,name,description,pricePerUnit,werrantyPeriod);
                    }
                    else{
                       int numberOfUsers= Integer.parseInt(varib.getText());
                        newProduct =new SoftWareProduct(id,name,description,pricePerUnit,numberOfUsers);
                    }
                    Backend_dao_List.getSingleton().AddProduct(newProduct);
                    manageCatalogForm.RefreshProductList();
                    System.out.println(Backend_dao_List.getSingleton().getAllProducts());

                    JOptionPane.showMessageDialog(null, "Product added successfully");

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }


        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInHardwareMode()){
                    variable.setText("Warrenty Period");
                }
                else
                    variable.setText("Number of Users");

            }
        });
    }
    private boolean isInHardwareMode() {

        return comboBox1.getSelectedItem().equals(ProductType.hardware);
    }
    public static void main(String[] args) {
    }
}
