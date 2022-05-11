package com.example.persistence;

public class Car {
    private long id;
    private String name;
    private String color;
    private int topSpeed;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public Car(long id, String name, String color, int topSpeed) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.topSpeed = topSpeed;
    }
}