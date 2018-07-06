package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/6.
 */
@Entity
@Table(name = "apply", schema = "kjb", catalog = "")
public class ApplyEntity {
    private int aId;
    private String username;
    private double amount;
    private double rate;
    private int repaytime;
    private String creditorName;
    private int status;

    @Id
    @Column(name = "a_id", nullable = false)
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 0)
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "repaytime", nullable = false)
    public int getRepaytime() {
        return repaytime;
    }

    public void setRepaytime(int repaytime) {
        this.repaytime = repaytime;
    }

    @Basic
    @Column(name = "creditor_name", nullable = false, length = 255)
    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplyEntity that = (ApplyEntity) o;

        if (aId != that.aId) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (repaytime != that.repaytime) return false;
        if (status != that.status) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (creditorName != null ? !creditorName.equals(that.creditorName) : that.creditorName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = aId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + repaytime;
        result = 31 * result + (creditorName != null ? creditorName.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
