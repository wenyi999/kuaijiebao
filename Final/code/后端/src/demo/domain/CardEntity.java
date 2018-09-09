package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/13.
 */
@Entity
@Table(name = "card", schema = "kjb", catalog = "")
public class CardEntity {
    private String credictnumber;
    private String username;

    @Id
    @Column(name = "credictnumber", nullable = false, length = 255)
    public String getCredictnumber() {
        return credictnumber;
    }

    public void setCredictnumber(String credictnumber) {
        this.credictnumber = credictnumber;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardEntity that = (CardEntity) o;

        if (credictnumber != null ? !credictnumber.equals(that.credictnumber) : that.credictnumber != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = credictnumber != null ? credictnumber.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
