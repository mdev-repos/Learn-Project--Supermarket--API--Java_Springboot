package com.mdev.supermarket_api.dto;

import com.mdev.supermarket_api.model.enums.SaleStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long id;
    private LocalDate date;
    private SaleStatus status;
    private Long branchId;
    private List<SaleDetailsDTO> details;
    private BigDecimal amount;
}
