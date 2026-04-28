package com.crunchfinn.admin.application.repository;

import com.crunchfinn.admin.application.entity.ApplicationDetails;
import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.application.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationDetailsRepository extends JpaRepository<ApplicationDetails, Long> {

    Optional<ApplicationDetails> findByApplicationId(String applicationId);

    boolean existsByApplicationId(String applicationId);

    void deleteByApplicationId(String applicationId);

    @Query("""
        SELECT c FROM ApplicationDetails c
        WHERE (:name IS NULL
        OR LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
        OR LOWER(c.phoneNumber) LIKE LOWER(CONCAT('%', :name, '%'))
        OR LOWER(c.applicationId) LIKE LOWER(CONCAT('%', :name, '%'))
        )
        AND (:status IS NULL OR c.status = :status)
        AND (:source IS NULL OR c.source = :source)
    """)
    List<ApplicationDetails> searchApplications(
            @Param("name") String name,
            @Param("status") ApplicationStatus status,
            @Param("source") ApplicationSource source);

    @Query("""
        SELECT a FROM ApplicationDetails a
        LEFT JOIN FETCH a.assignedPartners ap
        LEFT JOIN FETCH ap.bankPartner bp
        LEFT JOIN FETCH bp.bank
        WHERE a.applicationId = :id
    """)
    Optional<ApplicationDetails> findByApplicationIdWithPartners(@Param("id") String applicationId);
}
