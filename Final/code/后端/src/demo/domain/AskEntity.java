package demo.domain;

import javax.persistence.*;

/**
 * Created by Boyi on 2018/7/19.
 */
@Entity
@Table(name = "ask", schema = "kjb", catalog = "")
public class AskEntity {
    private String username;
    private String edubg;
    private String job;
    private String income;
    private String ask;

    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "edubg", nullable = true, length = 255)
    public String getEdubg() {
        return edubg;
    }

    public void setEdubg(String edubg) {
        this.edubg = edubg;
    }

    @Basic
    @Column(name = "job", nullable = true, length = 255)
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Basic
    @Column(name = "income", nullable = true, length = 255)
    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @Basic
    @Column(name = "ask", nullable = true, length = 255)
    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AskEntity askEntity = (AskEntity) o;

        if (username != null ? !username.equals(askEntity.username) : askEntity.username != null) return false;
        if (edubg != null ? !edubg.equals(askEntity.edubg) : askEntity.edubg != null) return false;
        if (job != null ? !job.equals(askEntity.job) : askEntity.job != null) return false;
        if (income != null ? !income.equals(askEntity.income) : askEntity.income != null) return false;
        if (ask != null ? !ask.equals(askEntity.ask) : askEntity.ask != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (edubg != null ? edubg.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (ask != null ? ask.hashCode() : 0);
        return result;
    }
}
