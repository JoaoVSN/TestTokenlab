package com.example.backtoken.model;

import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Usertk {
    @NotNull
    @UniqueElements
    @Column(name = "us_login", unique = true)
    private String us_login;
    @NotNull
    @Column(name = "us_senha")
    private String us_senha;
    @NotNull
    @Column(name = "us_nome")
    private String us_nome;
    @Column(name = "us_cpf")
    private String us_cpf;
    @Column(name = "us_idade")
    private int us_idade;
    @Column(name = "us_email")
    private String us_email;

    public String getUs_email() {
        return us_email;
    }

    public void setUs_email(String us_email) {
        this.us_email = us_email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "us_id")
    private long us_id;

    public String getUs_login() {
        return us_login;
    }

    public void setUs_login(String us_login) {
        this.us_login = us_login;
    }

    public String getUs_senha() {
        return us_senha;
    }

    public void setUs_senha(String us_senha) {
        this.us_senha = us_senha;
    }

    public String getUs_nome() {
        return us_nome;
    }

    public void setUs_nome(String us_nome) {
        this.us_nome = us_nome;
    }

    public String getUs_cpf() {
        return us_cpf;
    }

    public void setUs_cpf(String us_cpf) {
        this.us_cpf = us_cpf;
    }

    public int getUs_idade() {
        return us_idade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usertk usertk = (Usertk) o;
        return us_id == usertk.us_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(us_id);
    }

    public void setUs_idade(int us_idade) {
        this.us_idade = us_idade;
    }

    public long getUs_id() {
        return us_id;
    }

    public void setUs_id(int us_id) {
        this.us_id = us_id;
    }
}
