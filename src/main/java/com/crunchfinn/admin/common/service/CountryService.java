package com.crunchfinn.admin.common.service;

import com.crunchfinn.admin.common.model.CountryInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private Map<String, String> countryMap;

    @PostConstruct
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = getClass()
                    .getResourceAsStream("/data/countries.json");

            List<CountryInfo> list =
                    mapper.readValue(is, new TypeReference<>() {});

            countryMap = list.stream()
                    .collect(Collectors.toMap(
                            CountryInfo::getCode,
                            CountryInfo::getName
                    ));

            countryMap = Collections.unmodifiableMap(countryMap);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load countries.json", e);
        }
    }

    public String getCountryName(String code) {
        return countryMap.getOrDefault(code, code);
    }
}
