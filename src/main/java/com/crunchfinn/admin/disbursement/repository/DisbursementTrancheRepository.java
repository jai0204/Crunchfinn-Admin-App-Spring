package com.crunchfinn.admin.disbursement.repository;

import com.crunchfinn.admin.disbursement.entity.DisbursementTranche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementTrancheRepository extends JpaRepository<DisbursementTranche, Long> {
}