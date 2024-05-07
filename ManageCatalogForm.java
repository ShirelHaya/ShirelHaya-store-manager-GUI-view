import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageCatalogForm {

    JPanel panel;
    private JButton AddProduct;
    private JButton DeleteProduct;
    private JList ProductList;

    DefaultListModel AllProductsListModel = new DefaultListModel<>();
    Backend_DAO_List bdl = Backend_DAO_List.get_bdl_singleton();

    public ManageCatalogForm() {
        this.panel.setPreferredSize(new Dimension(600, 800));
        ProductList.setModel(AllProductsListModel);
        AddProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("AddNewProductForm");
                frame.setContentPane(new AddNewProductForm(ManageCatalogForm.this).panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        DeleteProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List selectedValuesList = ProductList.getSelectedValuesList();
                for (Object p : selectedValuesList) {
                    AllProductsListModel.removeElement(p);
                    try {
                        Backend_DAO_List.get_bdl_singleton().RemoveProduct((Product) p);
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
            AllProductsListModel.addAll(bdl.getAllProducts());
        } catch (Exception ex) {

        }
    }

    private void p() {
        JFrame frame = new JFrame("AddNewProductForm");
        frame.setContentPane(new AddNewProductForm(this).panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManageCatalogForm");
        frame.setContentPane(new ManageCatalogForm().panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
