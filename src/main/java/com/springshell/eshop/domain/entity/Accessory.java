package com.springshell.eshop.domain.entity;

import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.domain.enums.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "accessories")
public class Accessory extends Product{
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;
    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private Size size;
}
