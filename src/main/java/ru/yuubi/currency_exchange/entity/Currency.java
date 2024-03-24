package ru.yuubi.currency_exchange.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private int id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "Code")
    private String code;


    @Column(name = "Sign")
    private String sign;

    public Currency() {
    }

    public Currency(String name, String code, String sign) {
        this.name = name;
        this.code = code;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

