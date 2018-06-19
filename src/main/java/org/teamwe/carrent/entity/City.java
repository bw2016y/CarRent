package org.teamwe.carrent.entity;

 
public class City {
    private String city;
    private String site;

    public City(String city, String site) {
        this.city = city;
        this.site = site;
    }

    public String getCity() {
        return city;
    }

    public String getSite() {
        return site;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "City{" +
                "city='" + city + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
