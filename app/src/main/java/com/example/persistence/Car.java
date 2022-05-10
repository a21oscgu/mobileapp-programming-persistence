package com.example.persistence;

public class Car {
    private long id;
    private String name;
    private int topSpeed;

    public Car(long id, String name, int topSpeed) {
        this.id = id;
        this.name = name;
        this.topSpeed = topSpeed;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTopSpeed() {
        return topSpeed;
    }
}