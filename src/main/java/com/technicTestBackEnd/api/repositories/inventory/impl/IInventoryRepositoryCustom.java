package com.technicTestBackEnd.api.repositories.inventory.impl;

import com.technicTestBackEnd.api.model.entities.inventory.Inventory;

public interface IInventoryRepositoryCustom {

    Inventory findByProductId(Long productId);

    void save(Inventory inventory);
}
