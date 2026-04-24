package com.crunchfinn.admin.bank.service;

import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.dto.CreateBankRequest;
import com.crunchfinn.admin.bank.dto.UpdateBankRequest;

import java.util.List;

public interface BankService {
    BankResponse createBank(CreateBankRequest request);
    void updateBank(String bankCode, UpdateBankRequest request);
    BankResponse getById(Long id);
    BankResponse getByBankCode(String bankCode);
    List<BankResponse> getAllBanks();
    void deleteBank(String bankCode);
}
