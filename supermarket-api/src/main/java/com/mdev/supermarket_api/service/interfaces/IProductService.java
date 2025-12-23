package com.mdev.supermarket_api.service.interfaces;

import com.mdev.supermarket_api.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductDTO dto);
    List<ProductDTO> getAllProducts();
    ProductDTO findProductById(Long id);
    ProductDTO updateProduct(Long id, ProductDTO dto);
    void deleteProductById(Long id);
}
