package View;

import Controler.Backend_dao_List;
import Model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InsertCustomer extends JFrame {
    private JButton jButtonOK;
    private JLabel  jLabelID;
    private JLabel  jLabelName;
    private JLabel  jLabelAddress;
    private JTextField    jTextFieldID;
    private JTextField   jTextFieldName;
    private JTextField   jTextFieldAddress;

    public InsertCustomer() {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();

        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);
        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);
        getContentPane().add(jButtonOK);

        jTextFieldID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });

        this.setPreferredSize(new Dimension(400, 200));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        getContentPane().setLayout(new GridLayout(0,2,10,10));

        jButtonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    long id=Long.parseLong(jTextFieldID.getText());
                    String name=jTextFieldName.getText();
                    String adress=jTextFieldAddress.getText();

                    if(!isValid(id)){
                        throw new IllegalArgumentException("it isnt a valid id,please insert again");
                    }
                    if(name.trim().isEmpty()){
                        throw new IllegalArgumentException("name is requaired filed");
                    }
                    if(adress.trim().isEmpty()){
                        throw new IllegalArgumentException("adress is requaired filed");
                    }
                    Customer c=new Customer(id,name,adress);
                    Backend_dao_List.getSingleton().AddCustomer(c);
                    System.out.println(Backend_dao_List.getSingleton().getAllCustomers());

                    JOptionPane.showMessageDialog(null, "Customer added successfully");

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"error adding customer"+ex.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                }
            }

        });
}
private boolean isValid(long id){
        String idStr=Long.toString(id);
        return idStr.length()==9;
}
public static void main(String[] args){

}
}