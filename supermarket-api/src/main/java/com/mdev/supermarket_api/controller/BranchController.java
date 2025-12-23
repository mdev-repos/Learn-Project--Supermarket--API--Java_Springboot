package com.mdev.supermarket_api.controller;

import com.mdev.supermarket_api.dto.BranchDTO;
import com.mdev.supermarket_api.service.interfaces.IBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {
    private final IBranchService branchServ;
    public BranchController(IBranchService branchService){
        this.branchServ = branchService;
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches(){
        return  ResponseEntity.ok(branchServ.getAllBranches());
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO dto){
        BranchDTO branchDTO = branchServ.createBranch(dto);
        return ResponseEntity.created(URI.create("/api/branches" + branchDTO.getId())).body(branchDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @RequestBody BranchDTO dto){
        return ResponseEntity.ok(branchServ.updateBranch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranch(@PathVariable Long id){
        branchServ.deleteBranchById(id);
        return ResponseEntity.ok("Sucursal eliminada correctamente de la Base de Datos.");
    }
}
