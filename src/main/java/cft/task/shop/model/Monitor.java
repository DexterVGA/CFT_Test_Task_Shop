package cft.task.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "monitors")
public class Monitor extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int diagonal;

    public Monitor(int serialNumber, String producer, int price, int numberProductUnitsInStock, Integer id, int diagonal) {
        super(serialNumber, producer, price, numberProductUnitsInStock);
        this.id = id;
        this.diagonal = diagonal;
    }

    public Monitor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }
}
