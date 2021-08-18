package com.springshell.eshop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "mobiles")
public class Mobile extends Product {

    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
}
