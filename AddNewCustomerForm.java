import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AddNewCustomerForm extends JFrame {
    Backend_DAO_List bdl = Backend_DAO_List.get_bdl_singleton();
    //AddNewCustomerForm
    private JButton jButtonOK;
    private JLabel jLabelID;
    private JLabel jLabelName;
    private JLabel jLabelAddress;
    private JTextField jTextFieldID;
    private JTextField jTextFieldName;
    private JTextField jTextFieldAddress;

    public AddNewCustomerForm() {
        jButtonOK = new JButton("OK");
        jLabelID = new JLabel("ID:");
        jLabelName = new JLabel("Name:");
        jLabelAddress = new JLabel("Address:");
        jTextFieldID = new JTextField();
        jTextFieldName = new JTextField();
        jTextFieldAddress = new JTextField();
        jTextFieldID.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //  JOptionPane.showMessageDialog(AddNewCustomerForm.this,e.getKeyChar());
                if (!Character.isDigit(e.getKeyChar()) || jTextFieldID.getText().length() >= 9)
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        getContentPane().add(jLabelID);
        getContentPane().add(jTextFieldID);
        getContentPane().add(jLabelName);
        getContentPane().add(jTextFieldName);
        getContentPane().add(jLabelAddress);
        getContentPane().add(jTextFieldAddress);
        getContentPane().add(jButtonOK);

        this.setPreferredSize(new Dimension(600, 800));

        getContentPane().setLayout(new GridLayout(0, 2, 10, 10));//מנהל פרישה טבלאית
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();  // סידור הרכבים באופן פורפציונלי

        jButtonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id;
                    if (jTextFieldID.getText().isBlank()) {
                        throw new Exception("enter a id please!");
                    } else {
                        id = Long.parseLong(jTextFieldID.getText().trim());
                    }
                    if (jTextFieldName.getText().isBlank())
                        throw new Exception("enter a name please!");
                    if (bdl.getAllCustomers().containsKey(id))
                        throw new Exception("this customer exists in the program");
                    Customer customer = new Customer();
                    customer.setId(id);
                    customer.setName(jTextFieldName.getText());
                    customer.setAddress(jTextFieldAddress.getText());
                    // adding the new customer to the array of customers
                    bdl.AddCustomer(customer);
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this, "the customer was added successfully");
                    System.out.println(bdl.getAllCustomers());

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AddNewCustomerForm.this, ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        AddNewCustomerForm form = new AddNewCustomerForm();
        form.setVisible(true);
    }
}
