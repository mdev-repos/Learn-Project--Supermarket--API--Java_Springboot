package com.mdev.supermarket_api.mapper;

import com.mdev.supermarket_api.dto.BranchDTO;
import com.mdev.supermarket_api.dto.ProductDTO;
import com.mdev.supermarket_api.dto.SaleDTO;
import com.mdev.supermarket_api.dto.SaleDetailsDTO;
import com.mdev.supermarket_api.model.Branch;
import com.mdev.supermarket_api.model.Product;
import com.mdev.supermarket_api.model.Sale;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Mapper {
    // Branch -> BranchDTO
    public static BranchDTO toDTO(Branch b){
        if(b == null) return null;

        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();
    }

    // BranchDTO -> Branch
    public static Branch toEntity(BranchDTO dto){
        if(dto == null) return null;

        return Branch.builder()
                .id(dto.getId())
                .name(dto.getName())
                .address(dto.getAddress())
                .build();
    }

    // Product -> ProductDTO
    public static ProductDTO toDTO(Product p){
        if(p == null) return null;

        return ProductDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .build();
    }

    // ProductDTO -> Product
    public static Product toEntity(ProductDTO dto){
        if(dto == null) return null;

        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }

    // Sale -> SaleDTO
    public static SaleDTO toDTO(Sale s){
        if(s == null) return null;

        // Details -> DetailsDTO
        var details = s.getDetails().stream().map(det -> SaleDetailsDTO.builder()
                .id(det.getId())
                .productName(det.getProduct().getName())
                .productQuantity(det.getProductQuantity())
                .productPrice(det.getProductPrice())
                .subtotal(det.getProductPrice().multiply(BigDecimal.valueOf(det.getProductQuantity())))
                .build()
        ).collect(Collectors.toList());

        // Total Sale Amount
        var amount = details.stream()
                .map(SaleDetailsDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Build
        return SaleDTO.builder()
                .id(s.getId())
                .date(s.getDate())
                .status(s.getStatus())
                .branchId(s.getBranch().getId())
                .details(details)
                .amount(amount)
                .build();
    }
}
