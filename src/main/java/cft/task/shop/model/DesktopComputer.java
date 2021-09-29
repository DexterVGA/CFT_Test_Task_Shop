package cft.task.shop.model;

import javax.persistence.*;

@Entity(name = "desktopComputers")
public class DesktopComputer extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String formFactor;

    public DesktopComputer(int serialNumber, String producer, int price, int numberProductUnitsInStock, Integer id, String formFactor) {
        super(serialNumber, producer, price, numberProductUnitsInStock);
        this.id = id;
        this.formFactor = formFactor;
    }

    public DesktopComputer() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

}
