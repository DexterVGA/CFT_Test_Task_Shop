package cft.task.shop.model;

public abstract class Product {

    private int serialNumber;
    private String producer;
    private int price;
    private int numberProductUnitsInStock;

    public Product(int serialNumber, String producer, int price, int numberProductUnitsInStock) {
        this.serialNumber = serialNumber;
        this.producer = producer;
        this.price = price;
        this.numberProductUnitsInStock = numberProductUnitsInStock;
    }

    public Product() {

    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberProductUnitsInStock() {
        return numberProductUnitsInStock;
    }

    public void setNumberProductUnitsInStock(int numberProductUnitsInStock) {
        this.numberProductUnitsInStock = numberProductUnitsInStock;
    }
}
