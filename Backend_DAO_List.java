import model.Customer;
import model.HardwareProduct;
import model.Product;
import model.PurchaseOrder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Backend_DAO_List implements Backend {
//static Backend_DAO_List = new Backend_DAO_List();

    private Map<Long, Customer> _Customers;
    private Set<Product> _Products;
    private List<PurchaseOrder> _PurchaseOrders;

    //    private static Backend_DAO_List backend_dao_list= new Backend_DAO_List();
//    public static Backend_DAO_List getBackend_dao_list(){
//        return  backend_dao_list;
//    }
    private static Backend_DAO_List bdl_singleton;  // המופע היחיד

    public static Backend_DAO_List get_bdl_singleton() // הפונקציה שמחזירה את המופע היחיד
    {
        if (bdl_singleton == null)
            bdl_singleton = new Backend_DAO_List();
        return bdl_singleton;
    }

    //בונה private
    private Backend_DAO_List() {
        _Customers = new HashMap<>();
        _Products = new HashSet<>();
        _PurchaseOrders = new ArrayList<>();

        _Products.add(new HardwareProduct(1325, "122", "newExample", 45, 2));
    }
    // HashMap<Customer,Product>... I think the "Product" don't connect... need to change/ the question it us for what...
//    private Backend_DAO_List(HashMap<Customer,Product> hashMap, HashSet<Product> hashSet, ArrayList<PurchaseOrder> arrayList) {
//        _Customers = hashMap;
//        _Products = hashSet;
//        _PurchaseOrders = arrayList;
//    }


    @Override
    public void AddCustomer(Customer c) throws Exception {
        _Customers.put(c.getId(), c);
    }

    @Override
    public void AddProduct(Product p) throws Exception {
        _Products.add(p);
    }

    @Override
    public HashMap<Long, Customer> getAllCustomers() throws Exception {
        return (HashMap<Long, Customer>) _Customers;
    }

    @Override
    public HashSet<Product> getAllProducts() throws Exception {
        return (HashSet<Product>) _Products;
    }

    @Override
    public void PlaceOrder(PurchaseOrder po) throws Exception {
        _PurchaseOrders.add(po);
    }

    @Override
    public void RemoveProduct(Product p) throws Exception {
        _Products.remove(p);
    }

    @Override
    public ArrayList<Product> getCustomersOrders(Customer c) throws Exception {
        ArrayList ret = new ArrayList<>();
        for (PurchaseOrder o :
                _PurchaseOrders) {
            if (o.getOrderingCustomer().getId() == c.getId())
                ret.add(o.getProductsList());
        }
        return ret;
    }

    @Override
    public float CalcProductsTotalCost(Product[] products) throws Exception {
        float sum = 0;
        for (int i = 0; i < products.length; i++) {
            sum += products[i].getPrice() + products[i].getPricePerUnit();
        }
        return sum;
    }

    public void savaDataToFile() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data"));
        oos.writeObject(_Customers);
        oos.writeObject(_Products);
        oos.writeObject(_PurchaseOrders);
        oos.close();
    }

    public void loadDataFromFile() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data"));
        _Customers = (Map<Long, Customer>) ois.readObject();
        _Products = (Set<Product>) ois.readObject();
        _PurchaseOrders = (List<PurchaseOrder>) ois.readObject();
        ois.close();

    }
}
