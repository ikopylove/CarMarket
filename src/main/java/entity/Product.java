package entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "mark")
    @Size(min = 3, message = "NOT!!!")
    @NotNull
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "generation")
    private String generation;

    @Column(name = "body")
    private String body;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "typeEngine")
    private String typeEngine;

    @Column(name = "volumeEngine")
    private double volumeEngine;

    @Column(name = "powerEngine")
    private int powerEngine;

    @Column(name = "since")
    private int since;

    @Column(name = "owners")
    private int owners;

    @Column(name = "mileage")
    private int mileage;

}
