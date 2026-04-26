package com.crunchfinn.admin.common.service;

import com.crunchfinn.admin.common.model.City;
import com.crunchfinn.admin.common.model.StateInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StateCityService {

    private Map<String, StateInfo> stateMap;

    @PostConstruct
    public void loadData() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream is = getClass()
                    .getResourceAsStream("/data/stateCity.json");

            List<StateInfo> stateList =
                    mapper.readValue(is, new TypeReference<>() {});

            // Convert List → Map (state_code as key)
            stateMap = stateList.stream()
                    .collect(Collectors.toMap(
                            StateInfo::getState_code,
                            s -> s
                    ));

            stateMap = Collections.unmodifiableMap(stateMap);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load stateCity.json", e);
        }
    }

    // Get state name from code
    public String getStateName(String code) {
        return Optional.ofNullable(stateMap.get(code))
                .map(StateInfo::getName)
                .orElse(code);
    }

    // Get cities for a state
    public List<String> getCities(String code) {
        return Optional.ofNullable(stateMap.get(code))
                .map(StateInfo::getCities)
                .orElse(Collections.emptyList())
                .stream()
                .map(City::getName)
                .toList();
    }

    // Get all states (for dropdown)
    public List<StateInfo> getAllStates() {
        return new ArrayList<>(stateMap.values());
    }
}