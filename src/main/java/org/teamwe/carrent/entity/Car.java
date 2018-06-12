package org.teamwe.carrent.entity;

public class Car {
    public  int  type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private  int card;

    private  String brand;
    private  String img;
    private  String  message;


    private   int  price;
    private   int  ischecked;
    private   int  available;
    private   int   status;

    public Car() {
    }

    public Car(int card, String brand, String img, String message, int price, int ischecked, int available, int status) {
        this.card = card;
        this.brand = brand;
        this.img = img;
        this.message = message;
        this.price = price;
        this.ischecked = ischecked;
        this.available = available;
        this.status = status;
    }

    public int getCard() {
        return card;
    }

    public String getBrand() {
        return brand;
    }

    public String getImg() {
        return img;
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

    public void setCard(int card) {
        this.card = card;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImg(String img) {
        this.img = img;
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


    @Override
    public String toString() {
        return "Car{" +
                "card=" + card +
                ", brand='" + brand + '\'' +
                ", img='" + img + '\'' +
                ", message='" + message + '\'' +
                ", price=" + price +
                ", ischecked=" + ischecked +
                ", available=" + available +
                ", status=" + status +
                '}';
    }
}
