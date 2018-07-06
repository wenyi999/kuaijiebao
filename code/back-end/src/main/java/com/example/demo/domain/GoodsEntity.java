package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/6.
 */
@Entity
@Table(name = "goods", schema = "kjb", catalog = "")
public class GoodsEntity {
    private String itemName;
    private double price;
    private double itemRate;

    @Id
    @Column(name = "item_name", nullable = false, length = 255)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "item_rate", nullable = false, precision = 0)
    public double getItemRate() {
        return itemRate;
    }

    public void setItemRate(double itemRate) {
        this.itemRate = itemRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.itemRate, itemRate) != 0) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemName != null ? itemName.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(itemRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
