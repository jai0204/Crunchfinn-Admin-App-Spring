package com.crunchfinn.admin.bank.repository;

import com.crunchfinn.admin.bank.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {

    // Find by bankCode (used in view/edit via public ID)
    Optional<BankDetails> findByBankCode(String bankCode);

    // Check duplicate name
    boolean existsByName(String name);

    // Check duplicate bankCode (safety)
    boolean existsByBankCode(String bankCode);

    void deleteByBankCode(String bankCode);
}