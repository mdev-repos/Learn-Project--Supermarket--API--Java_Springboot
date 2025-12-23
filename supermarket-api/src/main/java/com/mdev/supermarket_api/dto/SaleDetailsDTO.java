package com.mdev.supermarket_api.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailsDTO {
    private Long id;
    private String productName;
    private Integer productQuantity;
    private BigDecimal productPrice;
    private BigDecimal subtotal;
}
