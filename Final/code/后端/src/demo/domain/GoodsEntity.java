package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/13.
 */
@Entity
@Table(name = "goods", schema = "kjb", catalog = "")
public class GoodsEntity {
    private String itemname;
    private double price;
    private double itemrate;

    @Id
    @Column(name = "itemname", nullable = false, length = 255)
    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
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
    @Column(name = "itemrate", nullable = false, precision = 0)
    public double getItemrate() {
        return itemrate;
    }

    public void setItemrate(double itemrate) {
        this.itemrate = itemrate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.itemrate, itemrate) != 0) return false;
        if (itemname != null ? !itemname.equals(that.itemname) : that.itemname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = itemname != null ? itemname.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(itemrate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
