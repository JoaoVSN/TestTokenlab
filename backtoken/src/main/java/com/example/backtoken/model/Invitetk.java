package com.example.backtoken.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Invitetk {
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_event", referencedColumnName = "ev_id")
    private Eventtk id_event;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "us_id")
    private Usertk id_user;
    @Column(name = "in_status")
    private boolean in_status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "in_id")
    private long in_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invitetk invitetk = (Invitetk) o;
        return in_id == invitetk.in_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(in_id);
    }

    public Eventtk getId_event() {
        return id_event;
    }

    public void setId_event(Eventtk id_event) {
        this.id_event = id_event;
    }

    public Usertk getId_user() {
        return id_user;
    }

    public void setId_user(Usertk id_user) {
        this.id_user = id_user;
    }

    public boolean isIn_status() {
        return in_status;
    }

    public void setIn_status(boolean in_status) {
        this.in_status = in_status;
    }
}
