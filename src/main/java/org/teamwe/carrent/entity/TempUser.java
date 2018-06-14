package org.teamwe.carrent.entity;

public class TempUser {

    private String email;
    private String hash;
    private long beginTime;
    private long endTime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public TempUser(String email, String hash, long beginTime, long endTime) {
        this.email = email;
        this.hash = hash;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
