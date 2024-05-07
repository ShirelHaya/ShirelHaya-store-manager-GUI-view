package model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    long id;
    String name;
    String address;

    public Customer(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Customer() {
        this.id = 123456789;
        this.name = "name";
        this.address = "address";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "  id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
