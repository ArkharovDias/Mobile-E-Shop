package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Mobile;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class MobileDto extends ProductDto{
    private String brand;
    private String model;

    public static MobileDto from(Mobile mobile){
        return MobileDto.builder()
                .id(mobile.getId())
                .description(mobile.getDescription())
                .price(mobile.getPrice())
                .brand(mobile.getBrand())
                .model(mobile.getModel())
                .build();
    }

    public static List<MobileDto> convert(List<Mobile> mobiles){
        return mobiles.stream()
                .map(MobileDto::from)
                .collect(Collectors.toList());
    }
}
