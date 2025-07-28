package com.technicTestBackEnd.api.repositories.inventory.impl;

import com.technicTestBackEnd.api.model.entities.inventory.Inventory;
import com.technicTestBackEnd.api.repositories.inventory.IInventoryRepository;
import org.springframework.stereotype.Component;

@Component
public class InventoryRepositoryImpl implements IInventoryRepositoryCustom {

    private final IInventoryRepository iInventoryRepository;

    public InventoryRepositoryImpl(IInventoryRepository iInventoryRepository) {
        this.iInventoryRepository = iInventoryRepository;
    }


    @Override
    public Inventory findByProductId(Long productId) {

        return iInventoryRepository.getReferenceById(productId);
    }

    @Override
    public void save(Inventory inventory) {
        iInventoryRepository.save(inventory);
    }
}
