package com.mdev.supermarket_api.repository;

import com.mdev.supermarket_api.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<Branch, Long> {
}
