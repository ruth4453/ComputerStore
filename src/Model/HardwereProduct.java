package Model;

import java.io.Serializable;

public class HardwereProduct extends Product implements Serializable {
    private int werrantyPeriod;

    public HardwereProduct(long id, String name, String description, float pricePerUnit, int werrantyPeriod) {
        super(id, name, description, pricePerUnit);
        this.werrantyPeriod = werrantyPeriod;
    }

    public HardwereProduct(int werrantyPeriod) {

        this.werrantyPeriod = werrantyPeriod;
    }

    public int getWerrantyPeriod() {
        return werrantyPeriod;
    }

    public void setWerrantyPeriod(int werrantyPeriod) {
        this.werrantyPeriod = werrantyPeriod;
    }

    @Override
    public float getPrice() {
        return this.werrantyPeriod+super.getPricePerUnit();
    }

    @Override
    public String toString() {
        return "HardwereProduct{" +
                "werrantyPeriod=" + werrantyPeriod +
                '}'+
                super.toString();
    }
}
