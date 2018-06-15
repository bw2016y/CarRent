package org.teamwe.carrent.entity;

public class CarType {
    int type;
    String description;

    public CarType() {
    }

    public CarType(int type, String des) {
        this.type = type;
        this.description = des;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "type=" + type +
                ", des='" + description + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public String getDes() {
        return description;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDes(String des) {
        this.description = des;
    }
}
