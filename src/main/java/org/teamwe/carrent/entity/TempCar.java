package org.teamwe.carrent.entity;

public class TempCar {
    int page;
    int length;
    int type;
    String brand;
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPage() {
        return page;
    }

    public int getLength() {
        return length;
    }

    public int getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public TempCar() {
    }

    @Override
    public String toString() {
        return "TempCar{" +
                "page=" + page +
                ", length=" + length +
                ", type=" + type +
                ", brand='" + brand + '\'' +
                ", city='" + city + '\'' +
                '}';
    }


    public TempCar(int page, int length, int type, String brand, String city) {
        this.page = page;
        this.length = length;
        this.type = type;
        this.brand = brand;
        this.city = city;
    }
}
