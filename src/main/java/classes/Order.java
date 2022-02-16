package classes;

import java.util.List;

public class Order {

    private String id;
    private double weight;
    private String date;
    private List<Product> products;
    private Receiver receiver;
    private Sender sender;
    private double totalValue = 0.0D;

    public Order(String id, double weight, List<Product> products, Receiver receiver, Sender sender, double totalValue) {
        this.id = id;
        this.weight = weight;
        this.products = products;
        this.receiver = receiver;
        this.sender = sender;
        this.totalValue = totalValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Order(Sender sender, Receiver receiver, String id) {
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }
}
