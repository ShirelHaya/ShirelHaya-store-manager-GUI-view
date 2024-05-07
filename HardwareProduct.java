package model;

import java.io.Serializable;

public class HardwareProduct extends Product implements Serializable {

    private int WarrantyPeriod;// תקופת אחריות

    public HardwareProduct(long id, String name, String description, float pricePerUnit, int warrantyPeriod) {
        super(id, name, description, pricePerUnit);
        WarrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return WarrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        WarrantyPeriod = warrantyPeriod;
    }

    public HardwareProduct(int warrantyPeriod) {
        WarrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "model.HardwareProduct{" +
                "  WarrantyPeriod=" + WarrantyPeriod +
//                ", id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", pricePerUnit=" + pricePerUnit +
//                '}';
                super.toString();
    }

    @Override
    public float getPrice() {
        return pricePerUnit * WarrantyPeriod;
    }
}
