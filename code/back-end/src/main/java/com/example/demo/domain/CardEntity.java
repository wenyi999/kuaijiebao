package com.example.demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/6.
 */
@Entity
@Table(name = "card", schema = "kjb", catalog = "")
public class CardEntity {
    private int credictNumber;
    private String username;

    @Id
    @Column(name = "credict_number", nullable = false)
    public int getCredictNumber() {
        return credictNumber;
    }

    public void setCredictNumber(int credictNumber) {
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
        int result = credictNumber;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
