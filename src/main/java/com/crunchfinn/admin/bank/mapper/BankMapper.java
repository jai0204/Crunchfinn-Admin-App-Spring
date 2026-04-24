package com.crunchfinn.admin.bank.mapper;

import com.crunchfinn.admin.bank.dto.CreateBankRequest;
import com.crunchfinn.admin.bank.dto.UpdateBankRequest;
import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.entity.BankDetails;
import org.springframework.stereotype.Component;

@Component
public class BankMapper {

    // Create → Entity
    public BankDetails toEntity(CreateBankRequest req) {

        BankDetails e = new BankDetails();

        e.setName(req.getName());
        e.setOfficialName(req.getOfficialName());
        e.setPartnershipDate(req.getPartnershipDate());

        e.setPartnerCode(req.getPartnerCode());
        e.setContractStartDate(req.getContractStartDate());
        e.setContractEndDate(req.getContractEndDate());
        e.setCommission(req.getCommission());

        e.setRoiMin(req.getRoiMin());
        e.setRoiMax(req.getRoiMax());

        return e;
    }

    // Update → Entity
    public void updateEntity(UpdateBankRequest req, BankDetails e) {

        e.setName(req.getName());
        e.setOfficialName(req.getOfficialName());
        e.setPartnershipDate(req.getPartnershipDate());

        e.setPartnerCode(req.getPartnerCode());
        e.setContractStartDate(req.getContractStartDate());
        e.setContractEndDate(req.getContractEndDate());
        e.setCommission(req.getCommission());

        e.setRoiMin(req.getRoiMin());
        e.setRoiMax(req.getRoiMax());
    }

    // Entity → Response
    public BankResponse toResponse(BankDetails e) {

        BankResponse res = new BankResponse();
        res.setId(e.getId());
        res.setBankCode(e.getBankCode());

        res.setName(e.getName());
        res.setOfficialName(e.getOfficialName());
        res.setPartnershipDate(e.getPartnershipDate());

        res.setPartnerCode(e.getPartnerCode());
        res.setContractStartDate(e.getContractStartDate());
        res.setContractEndDate(e.getContractEndDate());
        res.setCommission(e.getCommission());

        res.setRoiMin(e.getRoiMin());
        res.setRoiMax(e.getRoiMax());

        res.setCreatedAt(e.getCreatedAt());
        res.setUpdatedAt(e.getUpdatedAt());

        return res;
    }
}