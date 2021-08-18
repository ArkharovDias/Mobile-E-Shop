package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Accessory;
import com.springshell.eshop.domain.enums.Color;
import com.springshell.eshop.domain.enums.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class AccessoryDto extends ProductDto{
    private Color color;
    private Size size;

    public static AccessoryDto from(Accessory accessory){
        return AccessoryDto.builder()
                .id(accessory.getId())
                .description(accessory.getDescription())
                .price(accessory.getPrice())
                .color(accessory.getColor())
                .size(accessory.getSize())
                .build();
    }

    public static List<AccessoryDto> convert(List<Accessory> accessories){
        return accessories.stream()
                .map(AccessoryDto::from)
                .collect(Collectors.toList());
    }
}
