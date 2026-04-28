package com.crunchfinn.admin.application.repository;

import com.crunchfinn.admin.application.entity.ApplicationBankPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationBankPartnerRepository extends JpaRepository<ApplicationBankPartner, Long> {

    List<ApplicationBankPartner> findAllByApplication_Id(Long applicationId);

    boolean existsByApplicationIdAndBankPartnerId(Long applicationId, Long bankPartnerId);
}
