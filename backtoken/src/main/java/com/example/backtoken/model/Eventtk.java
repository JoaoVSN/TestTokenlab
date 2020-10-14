package com.example.backtoken.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Eventtk {
    @NotNull
    @Column(name = "ev_desc")
    private String ev_desc;
    @NotNull
    @Column(name = "ev_dt_begin")
    private Date ev_begin;
    @NotNull
    @Column(name = "ev_dt_end")
    private Date ev_end;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ev_us_id", referencedColumnName = "us_id")
    private Usertk ev_us_id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ev_id")
    private long ev_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eventtk eventtk = (Eventtk) o;
        return ev_id == eventtk.ev_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ev_id);
    }

    public String getEv_desc() {
        return ev_desc;
    }

    public void setEv_desc(String ev_desc) {
        this.ev_desc = ev_desc;
    }

    public Date getEv_begin() {
        return ev_begin;
    }

    public void setEv_begin(Date ev_begin) {
        this.ev_begin = ev_begin;
    }

    public Date getEv_end() {
        return ev_end;
    }

    public void setEv_end(Date ev_end) {
        this.ev_end = ev_end;
    }

    public Usertk getEv_us_id() {
        return ev_us_id;
    }

    public void setEv_us_id(Usertk ev_us_id) {
        this.ev_us_id = ev_us_id;
    }

    public long getEv_id() {
        return ev_id;
    }

    public void setEv_id(long ev_id) {
        this.ev_id = ev_id;
    }
}
