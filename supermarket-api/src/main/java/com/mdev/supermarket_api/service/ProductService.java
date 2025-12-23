package com.mdev.supermarket_api.service;

import com.mdev.supermarket_api.dto.ProductDTO;
import com.mdev.supermarket_api.exception.NotFoundException;
import com.mdev.supermarket_api.mapper.Mapper;
import com.mdev.supermarket_api.model.Product;
import com.mdev.supermarket_api.repository.IProductRepository;
import com.mdev.supermarket_api.service.interfaces.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductRepository prodRepo;
    public ProductService(IProductRepository productRepository){
        this.prodRepo = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Product product = Mapper.toEntity(dto);
        return Mapper.toDTO(prodRepo.save(product));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return prodRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductDTO findProductById(Long id) {
        Product product =prodRepo.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        return Mapper.toDTO(product);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product =prodRepo.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));

        if(dto.getName() != null){
            product.setName(dto.getName());
        }

        if(dto.getCategory() != null){
            product.setCategory(dto.getCategory());
        }

        if(dto.getQuantity() != null){
            product.setQuantity(dto.getQuantity());
        }

        if(dto.getPrice() != null){
            product.setPrice(dto.getPrice());
        }

        return Mapper.toDTO(prodRepo.save(product));
    }

    @Override
    public void deleteProductById(Long id) {
        if(!prodRepo.existsById(id)){
            throw new NotFoundException("Producto no encontrado");
        }
        prodRepo.deleteById(id);
    }
}
