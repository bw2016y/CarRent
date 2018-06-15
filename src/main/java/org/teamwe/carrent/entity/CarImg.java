package org.teamwe.carrent.entity;

public class CarImg {

    int imgid;
    String card;
    String img;

    public CarImg(int imgid, String card, String img) {
        this.imgid = imgid;
        this.card = card;
        this.img = img;
    }

    public CarImg() {
    }

    public int getImgid() {
        return imgid;
    }

    public String getCard() {
        return card;
    }

    public String getImg() {
        return img;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CarImg{" +
                "imgid=" + imgid +
                ", card='" + card + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
