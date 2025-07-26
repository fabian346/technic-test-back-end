package com.technicTestBackEnd.api.services.inventory.impl;

import com.technicTestBackEnd.api.model.responses.inventory.InventoryResponse;
import com.technicTestBackEnd.api.services.inventory.IInventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements IInventoryService {


    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {
        return null;
    }

    @Override
    public void updateQuantity(Long productId, Integer quantity) {

    }
}
