package org.teamwe.carrent.entity;

public class Car {




    private  String  card;
    private  String brand;
    private  String  message;

    private  int price;
    private int ischecked;
    private int available;

    private int status;
    private int type;

    private String email;
    private String city;



    public Car(String card, String brand, String message, int price, int ischecked, int available, int status, int type) {
        this.card = card;
        this.brand = brand;
        this.message = message;
        this.price = price;
        this.ischecked = ischecked;
        this.available = available;
        this.status = status;
        this.type = type;
    }

    public Car(String card, String brand, String message, int price, int ischecked, int available, int status, int type, String email, String city) {
        this.card = card;
        this.brand = brand;
        this.message = message;
        this.price = price;
        this.ischecked = ischecked;
        this.available = available;
        this.status = status;
        this.type = type;
        this.email = email;
        this.city = city;
    }

    public Car(String card, String brand, int price, int ischecked, int available, int status, int type) {
        this.card = card;
        this.brand = brand;
        this.price = price;
        this.ischecked = ischecked;
        this.available = available;
        this.status = status;
        this.type = type;
    }

    public String getCard() {
        return card;
    }

    public String getBrand() {
        return brand;
    }

    public String getMessage() {
        return message;
    }

    public int getPrice() {
        return price;
    }

    public int getIschecked() {
        return ischecked;
    }

    public int getAvailable() {
        return available;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIschecked(int ischecked) {
        this.ischecked = ischecked;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Car() {
    }


    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Car{" +
                "card='" + card + '\'' +
                ", brand='" + brand + '\'' +
                ", message='" + message + '\'' +
                ", price=" + price +
                ", ischecked=" + ischecked +
                ", available=" + available +
                ", status=" + status +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
