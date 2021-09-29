package cft.task.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "hardDiskDrives")
public class HardDiskDrive extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int memorySizeGB;

    public HardDiskDrive(int serialNumber, String producer, int price, int numberProductUnitsInStock, Integer id, int memorySizeGB) {
        super(serialNumber, producer, price, numberProductUnitsInStock);
        this.id = id;
        this.memorySizeGB = memorySizeGB;
    }

    public HardDiskDrive() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMemorySizeGB() {
        return memorySizeGB;
    }

    public void setMemorySizeGB(int memorySizeGB) {
        this.memorySizeGB = memorySizeGB;
    }
}
