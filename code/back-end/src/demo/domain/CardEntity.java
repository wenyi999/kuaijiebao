package demo.domain;

import javax.persistence.*;

@Entity
@Table(name = "card", schema = "kjb")
public class CardEntity {
    private String credictNumber;
    private String username;

    @Id
    @Column(name = "credictnumber", nullable = false)
    public String getCredictNumber() {
        return credictNumber;
    }

    public void setCredictNumber(String credictNumber) {
        this.credictNumber = credictNumber;
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

        if (credictNumber != that.credictNumber) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = credictNumber!=null?credictNumber.hashCode():0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
