package com.mdev.supermarket_api.service.interfaces;

import com.mdev.supermarket_api.dto.SaleDTO;

import java.util.List;

public interface ISaleService {
    SaleDTO createSale(SaleDTO dto);
    List<SaleDTO> getAllSales();
    SaleDTO findSaleById(Long id);
    SaleDTO updateSale(Long id, SaleDTO dto);
    void deleteSaleById(Long id);
}
