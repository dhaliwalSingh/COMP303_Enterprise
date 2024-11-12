package com.HatshbirSingh_COMP303_Assignment3.services;

import com.HatshbirSingh_COMP303_Assignment3.model.BloodBank;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BloodBankService {
    private Map<Integer, BloodBank> bloodBanks = new HashMap<>();
    private int idCounter = 1;

    public List<BloodBank> getAllBloodBanks() {
        return bloodBanks.values().stream().collect(Collectors.toList());
    }

    public BloodBank getBloodBankById(Integer id) {
        return bloodBanks.get(id);
    }

    public BloodBank addBloodBank(BloodBank bloodBank) {
        bloodBank.setId(idCounter++);
        bloodBanks.put(bloodBank.getId(), bloodBank);
        return bloodBank;
    }

    public BloodBank updateBloodBank(Integer id, BloodBank bloodBank) {
        bloodBank.setId(id);
        bloodBanks.put(id, bloodBank);
        return bloodBank;
    }

    public void deleteBloodBank(Integer id) {
        bloodBanks.remove(id);
    }
}
