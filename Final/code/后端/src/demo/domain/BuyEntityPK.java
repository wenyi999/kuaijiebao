package demo.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Boyi on 2018/7/13.
 */
public class BuyEntityPK implements Serializable {
    private String username;
    private String itemname;

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
    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyEntityPK that = (BuyEntityPK) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (itemname != null ? !itemname.equals(that.itemname) : that.itemname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (itemname != null ? itemname.hashCode() : 0);
        return result;
    }
}
