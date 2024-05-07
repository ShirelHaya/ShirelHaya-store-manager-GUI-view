package model;

import java.io.Serializable;

public class SoftwareProduct extends Product implements Serializable {

    private int NumberOfUsers;// מספר משתמשים

    public SoftwareProduct(long id, String name, String description, float pricePerUnit, int numberOfUsers) {
        super(id, name, description, pricePerUnit);
        NumberOfUsers = numberOfUsers;
    }

    public int getNumberOfUsers() {
        return NumberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        NumberOfUsers = numberOfUsers;
    }


    @Override
    public float getPrice() {
        return pricePerUnit * NumberOfUsers;
    }
}
