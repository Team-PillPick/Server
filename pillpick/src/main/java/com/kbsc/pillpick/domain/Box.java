package com.kbsc.pillpick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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




}
