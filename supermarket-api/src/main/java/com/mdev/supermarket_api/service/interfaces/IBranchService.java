package com.mdev.supermarket_api.service.interfaces;

import com.mdev.supermarket_api.dto.BranchDTO;

import java.util.List;

public interface IBranchService {
    BranchDTO createBranch(BranchDTO dto);
    List<BranchDTO> getAllBranches();
    BranchDTO findBranchById(Long id);
    BranchDTO updateBranch(Long id, BranchDTO dto);
    void deleteBranchById(Long id);
}
