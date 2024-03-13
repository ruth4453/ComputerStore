package Model;

import java.io.Serializable;

public class SoftWareProduct extends Product implements Serializable {
    private int numberOfUsers;

    public SoftWareProduct(long id, String name, String description, float pricePerUnit, int numberOfUsers) {
        super(id, name, description, pricePerUnit);
        this.numberOfUsers = numberOfUsers;
    }

    public SoftWareProduct(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }


    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public float getPrice() {
        return this.numberOfUsers+this.getPricePerUnit();
    }

    @Override
    public String toString() {
        return "SoftWareProduct{" +
                "numberOfUsers=" + numberOfUsers +
                '}'+
                super.toString();
    }
}
