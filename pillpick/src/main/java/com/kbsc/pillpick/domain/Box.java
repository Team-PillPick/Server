package com.kbsc.pillpick.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Box {

    @Id
    @GeneratedValue
    @Column(name = "box_id")
    private Long id;

    @Column(name = "location_name")
    private String name;

    private String address;

    @Column(name = "coordinate_x")
    private float coordinateX;

    @Column(name = "coordinate_y")
    private float coordinateY;

    public Box() {

    }

    public Box(String name, String address, Float coordinateX, Float coordinateY) {
        this.name = name;
        this.address = address;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }


}
