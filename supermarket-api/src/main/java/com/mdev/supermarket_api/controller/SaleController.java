package com.mdev.supermarket_api.controller;

import com.mdev.supermarket_api.dto.SaleDTO;
import com.mdev.supermarket_api.service.interfaces.ISaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    private final ISaleService saleServ;
    public SaleController(ISaleService saleService){
        this.saleServ = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDTO>> getAllSales(){
        return ResponseEntity.ok(saleServ.getAllSales());
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO dto){
        SaleDTO sale = saleServ.createSale(dto);
        return ResponseEntity.created(URI.create("/api/sales" + sale.getId())).body(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDTO> updateSale(@PathVariable Long id, @RequestBody SaleDTO dto){
        return ResponseEntity.ok(saleServ.updateSale(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id){
        saleServ.deleteSaleById(id);
        return ResponseEntity.ok("Venta eliminada correctamente de la Base de Datos");
    }
}
