package demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


public class BuyEntityPK implements Serializable {
    private String username;
    private String itemName;

    @Column(name = "username", nullable = false, length = 255)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "itemname", nullable = false, length = 255)
    @Id
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyEntityPK that = (BuyEntityPK) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (itemName != null ? !itemName.equals(that.itemName) : that.itemName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (itemName != null ? itemName.hashCode() : 0);
        return result;
    }
}
