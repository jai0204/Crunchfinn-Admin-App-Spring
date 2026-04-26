package com.crunchfinn.admin.bankpartner.repository;

import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankPartnerDetailsRepository extends JpaRepository<BankPartnerDetails, Long> {

    // Find by partner ID (public identifier)
    Optional<BankPartnerDetails> findByBankPartnerId(String bankPartnerId);

    // Check duplicate partner ID
    boolean existsByBankPartnerId(String bankPartnerId);

    // Get all partners for a bank
    List<BankPartnerDetails> findByBank_Id(Long bankId);

    @Query("""
        SELECT p FROM BankPartnerDetails p
        JOIN FETCH p.bank
    """)
    List<BankPartnerDetails> findAllWithBank();
}