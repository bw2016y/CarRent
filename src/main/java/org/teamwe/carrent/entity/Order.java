package org.teamwe.carrent.entity;

public class Order {

    private int  orderid;
    private String email;
    private String  card;
    private long  timebegin;
    private long  timeende;
    private long  timeendr;
    private String comment;
    private int    status;
    private int   type;
    private long  money;


    public int getType() {
        return type;
    }

    public long getMoney() {
        return money;
    }


    public void setType(int type) {
        this.type = type;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Order(int orderid, String email, String card, long timebegin, long timeende, long timeendr, String comment, int status, int type, long money) {
        this.orderid = orderid;
        this.email = email;
        this.card = card;
        this.timebegin = timebegin;
        this.timeende = timeende;
        this.timeendr = timeendr;
        this.comment = comment;
        this.status = status;
        this.type = type;
        this.money = money;
    }

    public Order() {
    }

    public Order(int orderid, String email, String card, long timebegin, long timeende, long timeendr, String comment, int status) {
        this.orderid = orderid;
        this.email = email;
        this.card = card;
        this.timebegin = timebegin;
        this.timeende = timeende;
        this.timeendr = timeendr;
        this.comment = comment;
        this.status = status;
    }

    public int getOrderid() {
        return orderid;
    }

    public String getEmail() {
        return email;
    }

    public String getCard() {
        return card;
    }

    public long getTimebegin() {
        return timebegin;
    }

    public long getTimeende() {
        return timeende;
    }

    public long getTimeendr() {
        return timeendr;
    }

    public String getComment() {
        return comment;
    }

    public int getStatus() {
        return status;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setTimebegin(long timebegin) {
        this.timebegin = timebegin;
    }

    public void setTimeende(long timeende) {
        this.timeende = timeende;
    }

    public void setTimeendr(long timeendr) {
        this.timeendr = timeendr;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order(String email, String card, long timebegin, long timeende, long timeendr, String comment, int status, int type, long money) {
        this.email = email;
        this.card = card;
        this.timebegin = timebegin;
        this.timeende = timeende;
        this.timeendr = timeendr;
        this.comment = comment;
        this.status = status;
        this.type = type;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", email='" + email + '\'' +
                ", card='" + card + '\'' +
                ", timebegin=" + timebegin +
                ", timeende=" + timeende +
                ", timeendr=" + timeendr +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", money=" + money +
                '}';
    }
}
