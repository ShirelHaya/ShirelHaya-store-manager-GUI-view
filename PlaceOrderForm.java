import model.Customer;
import model.Product;
import model.PurchaseOrder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaceOrderForm {
    public JPanel panel;
    private JComboBox cmbcus;
    private JLabel costxtcmb;
    private JComboBox cmbprod;
    private JLabel prodtxtcmb;
    private JButton addToOrder;
    private JList ListCustomerOrder;
    private JButton btRemoveSelstedProduct;
    private JButton btSubmitOrder;
    private JButton btClculateTotalSum;
    private JLabel lablTotalSum;

    Backend_DAO_List bdl;

    public PlaceOrderForm() {
        bdl = Backend_DAO_List.get_bdl_singleton();
        DefaultListModel SelectedProductsListModel = new DefaultListModel();
        ListCustomerOrder.setModel(SelectedProductsListModel);
        try {
            DefaultComboBoxModel modelCustomer = new DefaultComboBoxModel();
            modelCustomer.addAll(bdl.getAllCustomers().values());
            cmbcus.setModel(modelCustomer);

            DefaultComboBoxModel modelProduct = new DefaultComboBoxModel();
            modelProduct.addAll(bdl.getAllProducts());
            cmbprod.setModel(modelProduct);

        } catch (Exception ex) {
            Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        addToOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbcus.getSelectedIndex() >= 0)
                    SelectedProductsListModel.addElement((Product) cmbprod.getSelectedItem());
                else
                    JOptionPane.showMessageDialog(panel, "you must choose customer!");

            }
        });
        btRemoveSelstedProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Product> selectedValuesList = ListCustomerOrder.getSelectedValuesList();
                for (Product p : selectedValuesList) {
                    SelectedProductsListModel.removeElement(p);
                }

            }
        });
        btClculateTotalSum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Product[] products = new Product[SelectedProductsListModel.size()];
                    SelectedProductsListModel.copyInto(products);
                    Float total = bdl.CalcProductsTotalCost(products);
                    lablTotalSum.setText(total.toString());
                } catch (Exception ex) {
                    Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btSubmitOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbprod.getSelectedIndex() >= 0) {
                    try {
                        PurchaseOrder newOrder = new PurchaseOrder();
                        newOrder.setListProd(new ArrayList(Arrays.asList(SelectedProductsListModel.toArray())));
                        newOrder.setOrderingCustomer((Customer) cmbcus.getSelectedItem());
                        bdl.PlaceOrder(newOrder);
                        JOptionPane.showMessageDialog(panel, "the order added successfully!");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error Placing order", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(PlaceOrderForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "you must choose product!");

                }
            }
        });
    }

}

