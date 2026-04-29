package com.crunchfinn.admin.disbursement.repository;

import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.disbursement.dto.DisbursementListResponse;
import com.crunchfinn.admin.disbursement.entity.DisbursementDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DisbursementRepository extends JpaRepository<DisbursementDetails, Long> {

    boolean existsByApplication_Id(Long applicationId);

    Optional<DisbursementDetails> findByApplication_Id(Long applicationId);

    void deleteByApplication_Id(Long applicationId);

    @Query("""
        SELECT new com.crunchfinn.admin.disbursement.dto.DisbursementListResponse(
            a.id,
            a.applicationId,
            a.name,
            a.loanAmount,
            a.targetCountry,
            a.source,
            b.name,
            bp.state,
            bp.city,
            bp.handlerName,
            d.id,
            (
                SELECT COUNT(t2.id)
                FROM DisbursementTranche t2
                WHERE t2.disbursement = d
            )
        )
        FROM DisbursementDetails d
        JOIN d.application a
        JOIN d.bankPartner bp
        JOIN bp.bank b
        ORDER BY d.createdAt DESC
    """)
    List<DisbursementListResponse> findAllForListView();

    @Query("""
        SELECT DISTINCT new com.crunchfinn.admin.disbursement.dto.DisbursementListResponse(
            a.id,
            a.applicationId,
            a.name,
            a.loanAmount,
            a.targetCountry,
            a.source,
            b.name,
            bp.state,
            bp.city,
            bp.handlerName,
            d.id,
            (
                SELECT COUNT(t2.id)
                FROM DisbursementTranche t2
                WHERE t2.disbursement = d
            )
        )
        FROM DisbursementDetails d
        JOIN d.application a
        JOIN d.bankPartner bp
        JOIN bp.bank b
        LEFT JOIN d.tranches t
        WHERE
            (
                :search IS NULL OR
                LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR
                LOWER(a.applicationId) LIKE LOWER(CONCAT('%', :search, '%'))
            )
        AND (:source IS NULL OR a.source = :source)
        AND (
            :month IS NULL OR :year IS NULL OR
            (
                FUNCTION('MONTH', t.disbursementDate) = :month AND
                FUNCTION('YEAR', t.disbursementDate) = :year
            )
        )
    """)
    List<DisbursementListResponse> searchDisbursements(
            @Param("search") String search,
            @Param("source") ApplicationSource source,
            @Param("month") Integer month,
            @Param("year") Integer year
    );

    @Query("""
        SELECT d
        FROM DisbursementDetails d
        LEFT JOIN FETCH d.tranches
        WHERE d.id = :id
    """)
    Optional<DisbursementDetails> findWithTranches(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM DisbursementDetails d WHERE d.application.id = :applicationId")
    void deleteByApplicationId(@Param("applicationId") Long applicationId);
}