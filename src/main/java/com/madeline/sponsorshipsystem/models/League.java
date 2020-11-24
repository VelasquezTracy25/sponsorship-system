package com.madeline.sponsorshipsystem.models;

import javax.persistence.*;

@Entity
@Table(name = "leagues")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String coords;

    @Column(nullable = false, length = 16)
    private int price;

    public League(){}

    public League(long id, String name, String coords, int price) {
        this.id = id;
        this.name = name;
        this.coords = coords;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
