package com.technicTestBackEnd.api.services.inventory;

import com.technicTestBackEnd.api.model.responses.inventory.InventoryResponse;

public interface IInventoryService {

    InventoryResponse getInventoryByProductId(Long productId);

    void updateAmount(Long productId, Integer quantity);
}
