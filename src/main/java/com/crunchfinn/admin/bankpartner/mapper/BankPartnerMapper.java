package com.crunchfinn.admin.bankpartner.mapper;

import com.crunchfinn.admin.bank.entity.BankDetails;
import com.crunchfinn.admin.bankpartner.dto.CreateBankPartnerRequest;
import com.crunchfinn.admin.bankpartner.dto.UpdateBankPartnerRequest;
import com.crunchfinn.admin.bankpartner.dto.BankPartnerResponse;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;

import org.springframework.stereotype.Component;

@Component
public class BankPartnerMapper {

    // CREATE → ENTITY
    public BankPartnerDetails toEntity(CreateBankPartnerRequest req, BankDetails bank) {

        BankPartnerDetails e = new BankPartnerDetails();

        e.setBank(bank);

        e.setBankPartnerId(req.getBankPartnerId()); // can remove later if auto-generated

        // location
        e.setAddressLine1(req.getAddressLine1());
        e.setAddressLine2(req.getAddressLine2());
        e.setCity(req.getCity());
        e.setState(req.getState());
        e.setStateCode(req.getStateCode());
        e.setPincode(req.getPincode());
        e.setGstin(req.getGstin());

        // handler
        e.setHandlerName(req.getHandlerName());
        e.setHandlerGender(req.getHandlerGender());
        e.setHandlerPhoneNumber(req.getHandlerPhoneNumber());

        // email
        e.setEmailTo(req.getEmailTo());
        e.setEmailCc(req.getEmailCc());

        return e;
    }

    // UPDATE → ENTITY
    public void updateEntity(UpdateBankPartnerRequest req, BankDetails bank, BankPartnerDetails e) {

        e.setBank(bank);

        // (optional) do NOT update partnerId if immutable
        // e.setBankPartnerId(req.getBankPartnerId());

        // location
        e.setAddressLine1(req.getAddressLine1());
        e.setAddressLine2(req.getAddressLine2());
        e.setCity(req.getCity());
        e.setState(req.getState());
        e.setStateCode(req.getStateCode());
        e.setPincode(req.getPincode());
        e.setGstin(req.getGstin());

        // handler
        e.setHandlerName(req.getHandlerName());
        e.setHandlerGender(req.getHandlerGender());
        e.setHandlerPhoneNumber(req.getHandlerPhoneNumber());

        // email
        e.setEmailTo(req.getEmailTo());
        e.setEmailCc(req.getEmailCc());
    }

    // ENTITY → RESPONSE
    public BankPartnerResponse toResponse(BankPartnerDetails e) {

        BankPartnerResponse res = new BankPartnerResponse();

        res.setId(e.getId());

        // relation
        res.setBankId(e.getBank().getId());
        res.setBankName(e.getBank().getName());

        // identifiers
        res.setBankPartnerId(e.getBankPartnerId());

        // location
        res.setAddressLine1(e.getAddressLine1());
        res.setAddressLine2(e.getAddressLine2());
        res.setCity(e.getCity());
        res.setState(e.getState());
        res.setStateCode(e.getStateCode());
        res.setPincode(e.getPincode());
        res.setGstin(e.getGstin());

        // handler
        res.setHandlerName(e.getHandlerName());
        res.setHandlerGender(e.getHandlerGender());
        res.setHandlerPhoneNumber(e.getHandlerPhoneNumber());

        // email
        res.setEmailTo(e.getEmailTo());
        res.setEmailCc(e.getEmailCc());

        res.setCreatedAt(e.getCreatedAt());
        res.setUpdatedAt(e.getUpdatedAt());

        return res;
    }
}