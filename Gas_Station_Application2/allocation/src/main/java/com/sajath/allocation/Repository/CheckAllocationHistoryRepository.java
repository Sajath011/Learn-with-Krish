package com.sajath.allocation.Repository;

import com.sajath.allocation.Entity.CheckAllocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

// Allocation Repo Which Connects Spring JPA
// Here We get the Allocation Class as a <>
public interface CheckAllocationHistoryRepository
        extends JpaRepository<CheckAllocationHistory,Long> {
}
