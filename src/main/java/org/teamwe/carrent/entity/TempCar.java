package org.teamwe.carrent.entity;

public class TempCar {
    int page;
    int length;
    int type;
    String brand;

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

    public TempCar(int page, int length, int type, String brand) {
        this.page = page;
        this.length = length;
        this.type = type;
        this.brand = brand;
    }

    public TempCar(int length, int type, String brand) {
        this.length = length;
        this.type = type;
        this.brand = brand;
    }
}
