package com.crunchfinn.admin.bankpartner.service;

import com.crunchfinn.admin.bank.entity.BankDetails;
import com.crunchfinn.admin.bank.repository.BankDetailsRepository;
import com.crunchfinn.admin.bankpartner.dto.*;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import com.crunchfinn.admin.bankpartner.mapper.BankPartnerMapper;
import com.crunchfinn.admin.bankpartner.repository.BankPartnerDetailsRepository;
import com.crunchfinn.admin.common.service.StateCityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankPartnerServiceImpl implements BankPartnerService {

    private final BankPartnerDetailsRepository repository;
    private final BankDetailsRepository bankRepository;
    private final BankPartnerMapper mapper;
    private final StateCityService stateCityService;

    public BankPartnerServiceImpl(BankPartnerDetailsRepository repository,
                                  BankDetailsRepository bankRepository,
                                  BankPartnerMapper mapper, StateCityService stateCityService) {
        this.repository = repository;
        this.bankRepository = bankRepository;
        this.mapper = mapper;
        this.stateCityService = stateCityService;
    }

    // CREATE
    @Override
    @Transactional
    public BankPartnerResponse createPartner(CreateBankPartnerRequest request) {

        BankDetails bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        BankPartnerDetails entity = mapper.toEntity(request, bank);

        // save first → get ID
        repository.save(entity);

        // generate partner ID using DB id
        entity.setBankPartnerId(generatePartnerId(entity.getId()));

        return mapper.toResponse(entity);
    }

    // UPDATE
    @Override
    @Transactional
    public void updatePartner(UpdateBankPartnerRequest request) {

        BankPartnerDetails entity = repository.findByBankPartnerId(request.getBankPartnerId())
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        BankDetails bank = bankRepository.findById(request.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        mapper.updateEntity(request, bank, entity);
    }

    // GET BY ID
    @Override
    public BankPartnerResponse getById(Long id) {

        BankPartnerDetails entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        return populateAdditionalFields(mapper.toResponse(entity));
    }

    // GET BY PARTNER ID
    @Override
    public BankPartnerResponse getByPartnerId(String partnerId) {

        BankPartnerDetails entity = repository.findByBankPartnerId(partnerId)
                .orElseThrow(() -> new RuntimeException("Partner not found"));

        return populateAdditionalFields(mapper.toResponse(entity));
    }

    // LIST ALL
    @Override
    public List<BankPartnerResponse> getAllPartners() {

        return repository.findAllWithBank()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // LIST BY BANK
    @Override
    public List<BankPartnerResponse> getPartnersByBank(Long bankId) {

        return repository.findByBank_Id(bankId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    // DELETE
    @Override
    @Transactional
    public void deletePartner(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Partner not found");
        }

        repository.deleteById(id);
    }

    public List<BankWithPartnersDTO> getBanksWithPartners() {

        List<BankPartnerDetails> partners = repository.findAllWithBank();

        Map<Long, BankWithPartnersDTO> map = new LinkedHashMap<>();

        for (BankPartnerDetails p : partners) {

            Long bankId = p.getBank().getId();

            map.putIfAbsent(bankId, new BankWithPartnersDTO());
            BankWithPartnersDTO dto = map.get(bankId);

            dto.setBankId(bankId);
            dto.setBankName(p.getBank().getName());

            if (dto.getPartners() == null) {
                dto.setPartners(new ArrayList<>());
            }

            BankPartnerDropdownResponse partnerDto = new BankPartnerDropdownResponse();
            partnerDto.setId(p.getId());
            partnerDto.setHandlerName(p.getHandlerName());
            partnerDto.setCity(p.getCity());
            partnerDto.setPhoneNumber(p.getHandlerPhoneNumber());

            dto.getPartners().add(partnerDto);
        }

        return new ArrayList<>(map.values());
    }

    // ID GENERATION (correct approach)
    private String generatePartnerId(Long id) {
        return String.format("CF-PARTNER-%04d", id);
    }

    private BankPartnerResponse populateAdditionalFields(BankPartnerResponse response) {
        response.setStateName(stateCityService.getStateName(response.getState()));
        return response;
    }
}