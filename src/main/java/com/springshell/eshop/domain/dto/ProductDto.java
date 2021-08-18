package com.springshell.eshop.domain.dto;

import com.springshell.eshop.domain.entity.Product;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductDto {
    private Long id;
    private String description;
    private Double price;

    public static ProductDto from(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductDto> from(List<Product> products){
        return products.stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }
}
