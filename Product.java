package model;

import java.io.Serializable;

public abstract class Product implements Serializable {
    long id;
    String name;
    String description; //Description
    float pricePerUnit;

    public Product(long id, String name, String description, float pricePerUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pricePerUnit = pricePerUnit;
    }

    public Product() {
        this.id = 123456789;
        this.name = "name";
        this.description = "description";
        this.pricePerUnit = 0.5F;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        //return super.toString();
        String s;
        s = "id: " + id + "  name: " + name + "  description: " + description + "  pricePerUnit: " + pricePerUnit;
        return s;
    }

    public abstract float getPrice();
}
