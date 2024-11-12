package com.HatshbirSingh_COMP303_Assignment3.services;

import com.HatshbirSingh_COMP303_Assignment3.model.BloodStock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BloodStockService {
    private Map<Integer, BloodStock> bloodStocks = new HashMap<>();
    private int idCounter = 1;

    public List<BloodStock> getAllBloodStocks() {
        return bloodStocks.values().stream().collect(Collectors.toList());
    }

    public BloodStock getBloodStockById(Integer id) {
        return bloodStocks.get(id);
    }

    public BloodStock addBloodStock(BloodStock bloodStock) {
        bloodStock.setId(idCounter++);
        bloodStocks.put(bloodStock.getId(), bloodStock);
        return bloodStock;
    }

    public BloodStock updateBloodStock(Integer id, BloodStock bloodStock) {
        bloodStock.setId(id);
        bloodStocks.put(id, bloodStock);
        return bloodStock;
    }

    public void deleteBloodStock(Integer id) {
        bloodStocks.remove(id);
    }
}
