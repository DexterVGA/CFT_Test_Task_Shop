package cft.task.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "laptops")
public class Laptop extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int sizeInInches;

    public Laptop(int serialNumber, String producer, int price, int numberProductUnitsInStock, Integer id, int sizeInInches) {
        super(serialNumber, producer, price, numberProductUnitsInStock);
        this.id = id;
        this.sizeInInches = sizeInInches;
    }

    public Laptop() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSizeInInches() {
        return sizeInInches;
    }

    public void setSizeInInches(int sizeInInches) {
        this.sizeInInches = sizeInInches;
    }
}
