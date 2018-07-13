package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/13.
 */
@Entity
@Table(name = "buy", schema = "kjb", catalog = "")
@IdClass(BuyEntityPK.class)
public class BuyEntity {
    private String username;
    private String itemname;
    private int amount;

    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "itemname", nullable = false, length = 255)
    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyEntity buyEntity = (BuyEntity) o;

        if (amount != buyEntity.amount) return false;
        if (username != null ? !username.equals(buyEntity.username) : buyEntity.username != null) return false;
        if (itemname != null ? !itemname.equals(buyEntity.itemname) : buyEntity.itemname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (itemname != null ? itemname.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }
}
