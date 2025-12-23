package com.mdev.supermarket_api.service;

import com.mdev.supermarket_api.dto.BranchDTO;
import com.mdev.supermarket_api.exception.NotFoundException;
import com.mdev.supermarket_api.mapper.Mapper;
import com.mdev.supermarket_api.model.Branch;
import com.mdev.supermarket_api.repository.IBranchRepository;
import com.mdev.supermarket_api.service.interfaces.IBranchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {
    private final IBranchRepository branchRepo;
    public BranchService(IBranchRepository branchRepository){
        this.branchRepo = branchRepository;
    }

    @Override
    public BranchDTO createBranch(BranchDTO dto) {
        Branch branch = Mapper.toEntity(dto);
        return Mapper.toDTO(branchRepo.save(branch));
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        return branchRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BranchDTO findBranchById(Long id) {
        Branch branch = branchRepo.findById(id).orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
        return Mapper.toDTO(branch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchDTO dto) {
        Branch branch = branchRepo.findById(id).orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        if(dto.getName() != null){
            branch.setName(dto.getName());
        }

        if(dto.getAddress() != null){
            branch.setAddress(dto.getAddress());
        }

        return Mapper.toDTO(branchRepo.save(branch));
    }

    @Override
    public void deleteBranchById(Long id) {
        if(!branchRepo.existsById(id)){
            throw new NotFoundException("Sucursal no encontrada");
        }
        branchRepo.deleteById(id);
    }
}
