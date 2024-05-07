import model.Enum;
import model.HardwareProduct;
import model.Product;
import model.SoftwareProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddNewProductForm {
    JPanel panel;
    private JLabel idLable;
    private JTextField idText;
    private JLabel nameLabel;
    private JTextField nameTxt;
    private JTextField descriptionTxt;
    private JLabel descriptionLabel;
    private JLabel pricePerUnitLabel;
    private JTextField pricePerUnitTxt;
    private JButton addButton;
    private JComboBox cmbType;
    private JTextField jtextTpe;
    private JLabel jLablType;

    Backend_DAO_List bdl = Backend_DAO_List.get_bdl_singleton();


    public AddNewProductForm(ManageCatalogForm m) {
        this.panel.setPreferredSize(new Dimension(600, 800));
        DefaultComboBoxModel<Enum> model = new DefaultComboBoxModel(Enum.values());
        cmbType.setModel(model);

        //num users or num years of 'ahrayut'
        jtextTpe.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || jtextTpe.getText().length() >= 5)
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        pricePerUnitTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()))
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        cmbType.setSelectedIndex(0);
        cmbType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jLablType.setText(isInHardwareMode() ? "warranty period" : "num users");
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product product = null;
                    if (nameTxt.getText().isBlank())
                        throw new Exception("must include product name!");
                    if (isInHardwareMode()) {
                        product = new HardwareProduct(Long.parseLong(idText.getText()), nameTxt.getText(), descriptionTxt.getText(), Float.parseFloat(pricePerUnitTxt.getText()), Integer.parseInt(pricePerUnitTxt.getText()));
                    } else {
                        product = new SoftwareProduct(Long.parseLong(idText.getText()), nameTxt.getText(), descriptionTxt.getText(), Float.parseFloat(pricePerUnitTxt.getText()), Integer.parseInt(pricePerUnitTxt.getText()));
                    }
                    bdl.AddProduct(product);
                    m.RefreshProductList();
                    JOptionPane.showMessageDialog(panel, "the product added successfully!");
                    System.out.println(bdl.getAllProducts());
                    System.out.println(product);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage());
                }
            }
        });
    }


    private boolean isInHardwareMode() {
        return cmbType.getSelectedItem().equals(Enum.HARDWARE);
    }
}
