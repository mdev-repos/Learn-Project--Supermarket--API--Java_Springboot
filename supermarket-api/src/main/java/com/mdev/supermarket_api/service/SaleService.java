package com.mdev.supermarket_api.service;

import com.mdev.supermarket_api.dto.SaleDTO;
import com.mdev.supermarket_api.dto.SaleDetailsDTO;
import com.mdev.supermarket_api.exception.NotFoundException;
import com.mdev.supermarket_api.mapper.Mapper;
import com.mdev.supermarket_api.model.Branch;
import com.mdev.supermarket_api.model.Sale;
import com.mdev.supermarket_api.model.SaleDetails;
import com.mdev.supermarket_api.repository.IBranchRepository;
import com.mdev.supermarket_api.repository.IProductRepository;
import com.mdev.supermarket_api.repository.ISaleRepository;
import com.mdev.supermarket_api.service.interfaces.ISaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {
    private final ISaleRepository saleRepo;
    private final IBranchRepository branchRepo;
    private final IProductRepository productRepo;
    public SaleService(ISaleRepository saleRepository,
                       IBranchRepository branchRepository,
                       IProductRepository productRepository){
        this.saleRepo = saleRepository;
        this.branchRepo = branchRepository;
        this.productRepo = productRepository;
    }

    @Override
    public SaleDTO createSale(SaleDTO dto) {
        if(dto == null) throw new RuntimeException("No se ha recibido la informacion necesaria para registrar una nueva venta.");
        if(dto.getBranchId() == null) throw new RuntimeException("Se debe asignar la sucursal correspondiente para registrar una nueva venta.");
        if(dto.getDetails() == null || dto.getDetails().isEmpty()) throw new RuntimeException("Se debe agregar por lo menos un producto para registrar una nueva venta.");

        // Get Branch by ID
        Branch branch = branchRepo.findById(dto.getBranchId()).orElseThrow(() -> new NotFoundException("No se encontro la sucursal indicada."));

        // Sale
        Sale sale = new Sale();

        sale.setId(dto.getId());
        sale.setDate(dto.getDate());
        sale.setBranch(branch);
        sale.setStatus(dto.getStatus());
        sale.setAmount(dto.getAmount());

        List<SaleDetails> details = getSaleDetailsFromDetailsDTO(dto.getDetails(), sale);
        sale.setDetails(details);

        return Mapper.toDTO(saleRepo.save(sale));
    }

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SaleDTO findSaleById(Long id) {
        Sale sale = saleRepo.findById(id).orElseThrow(()-> new NotFoundException("Venta no encontrada."));
        return Mapper.toDTO(sale);
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO dto) {
        Sale sale = saleRepo.findById(id).orElseThrow(()-> new NotFoundException("Venta no encontrada."));

        if(dto.getDate() != null){
            sale.setDate(dto.getDate());
        }

        if(dto.getBranchId() != null){
            Branch branch = branchRepo.findById(dto.getBranchId()).orElseThrow(()-> new NotFoundException("Sucursal no encontrada."));
            sale.setBranch(branch);
        }

        if(dto.getStatus() != null){
            sale.setStatus(dto.getStatus());
        }

        if(dto.getDetails() != null){
            List<SaleDetails> details = getSaleDetailsFromDetailsDTO(dto.getDetails(), sale);
            sale.setDetails(details);
        }

        if(dto.getAmount() != null){
            sale.setAmount(dto.getAmount());
        }

        return Mapper.toDTO(saleRepo.save(sale));
    }

    @Override
    public void deleteSaleById(Long id) {
        if(!saleRepo.existsById(id)) throw new NotFoundException("Venta no encontrada.");

        saleRepo.deleteById(id);
    }


    private List<SaleDetails> getSaleDetailsFromDetailsDTO(List<SaleDetailsDTO> detailsDTO, Sale sale){
        return detailsDTO.stream().map(det -> {
        SaleDetails sd = new SaleDetails();
        sd.setId(det.getId());
        sd.setSale(sale);
        sd.setProduct(productRepo.findProductByName(det.getProductName()).orElseThrow(() -> new NotFoundException("Producto no encontrado")));
        sd.setProductQuantity(det.getProductQuantity());
        sd.setProductPrice(det.getProductPrice());
        return sd;
        }).collect(Collectors.toList());
    }
}
