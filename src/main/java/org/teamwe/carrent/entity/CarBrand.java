package org.teamwe.carrent.entity;

public class CarBrand {

    String brand;
    String img;

    public CarBrand(String brand, String img) {
        this.brand = brand;
        this.img = img;
    }

    public CarBrand() {
    }

    public String getBrand() {
        return brand;
    }

    public String getImg() {
        return img;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "brand='" + brand + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
