import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;

public class StoreManagerGUIViewForm {
    private JButton addCustomer;
    private JButton ordersView;
    private JButton order;
    private JButton products;
    private JPanel panel;


    public StoreManagerGUIViewForm() {
        this.panel.setPreferredSize(new Dimension(500, 400));

        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCustomerForm form = new AddNewCustomerForm();
                form.setVisible(true);
                try {
                    form.bdl.loadDataFromFile();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    form.bdl.savaDataToFile();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        products.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("ManageCatalogForm");
                frame.setContentPane(new ManageCatalogForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        ordersView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("PlaceOrderForm");
                frame.setContentPane(new PlaceOrderForm().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });

        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Orders");
                frame.setContentPane(new Orders().panel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("StoreManagerGUIViewForm");
        frame.setContentPane(new StoreManagerGUIViewForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
