package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/13.
 */
@Entity
@Table(name = "apply", schema = "kjb", catalog = "")
public class ApplyEntity {
    private int aid;
    private String username;
    private double amount;
    private double rate;
    private int repaytime;
    private String creditorname;
    private int status;

    @Id
    @Column(name = "aid", nullable = false)
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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
    @Column(name = "amount", nullable = false, precision = 2)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 2)
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
    @Column(name = "creditorname", nullable = true, length = 255)
    public String getCreditorname() {
        return creditorname;
    }

    public void setCreditorname(String creditorname) {
        this.creditorname = creditorname;
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

        if (aid != that.aid) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        if (repaytime != that.repaytime) return false;
        if (status != that.status) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (creditorname != null ? !creditorname.equals(that.creditorname) : that.creditorname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = aid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + repaytime;
        result = 31 * result + (creditorname != null ? creditorname.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }
}
