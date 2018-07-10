package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/9.
 */
@Entity
@Table(name = "buy", schema = "kjb")
@IdClass(BuyEntityPK.class)
public class BuyEntity {
    private String username;
    private String itemName;
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
    @Column(name = "item_name", nullable = false, length = 255)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
        if (itemName != null ? !itemName.equals(buyEntity.itemName) : buyEntity.itemName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }
}
