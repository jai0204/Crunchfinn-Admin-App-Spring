package com.crunchfinn.admin.bank.service;

import com.crunchfinn.admin.bank.dto.CreateBankRequest;
import com.crunchfinn.admin.bank.dto.UpdateBankRequest;
import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.entity.BankDetails;
import com.crunchfinn.admin.bank.mapper.BankMapper;
import com.crunchfinn.admin.bank.repository.BankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BankDetailsRepository repository;
    private final BankMapper mapper;

    @Autowired
    public BankServiceImpl(BankDetailsRepository repository, BankMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    @Override
    @Transactional
    public BankResponse createBank(CreateBankRequest request) {

        // duplicate check
        if (repository.existsByName(request.getName())) {
            throw new RuntimeException("Bank already exists");
        }

        BankDetails entity = mapper.toEntity(request);

        // save first to generate auto-increment id
        repository.save(entity);

        // generate application id
        String bankCode = String.format("CF-BANK-%04d", entity.getId());
        entity.setBankCode(bankCode);

        return mapper.toResponse(entity);
    }

    // UPDATE
    @Override
    @Transactional
    public void updateBank(String bankCode, UpdateBankRequest request) {

        BankDetails entity = repository.findByBankCode(bankCode)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        mapper.updateEntity(request, entity);
    }

    // GET BY ID
    public BankResponse getById(Long id) {
        BankDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        return mapper.toResponse(entity);
    }

    // GET BY CODE
    public BankResponse getByBankCode(String bankCode) {
        BankDetails entity = repository.findByBankCode(bankCode)
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        return mapper.toResponse(entity);
    }

    // LIST ALL
    public List<BankResponse> getAllBanks() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Page<BankResponse> getAllBanks(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size
        );
        Page<BankDetails> bankPage = repository.findAll(pageable);

        return bankPage.map(mapper::toResponse);
    }

    // DELETE
    @Transactional
    public void deleteBank(String bankCode) {

        if (!repository.existsByBankCode(bankCode)) {
            throw new RuntimeException("Bank not found");
        }

        repository.deleteByBankCode(bankCode);
    }
}