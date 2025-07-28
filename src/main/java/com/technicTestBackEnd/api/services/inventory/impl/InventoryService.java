package com.technicTestBackEnd.api.services.inventory.impl;

import com.technicTestBackEnd.api.events.InventoryEventPublisher;
import com.technicTestBackEnd.api.model.entities.inventory.Inventory;
import com.technicTestBackEnd.api.model.responses.inventory.InventoryResponse;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import com.technicTestBackEnd.api.repositories.inventory.impl.IInventoryRepositoryCustom;
import com.technicTestBackEnd.api.repositories.purchasehistory.iml.IPurchaseHistoryRepoCustom;
import com.technicTestBackEnd.api.services.inventory.IInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;


@Service
public class InventoryService implements IInventoryService {

    private final IInventoryRepositoryCustom inventoryRepositoryCustom;

    private final InventoryEventPublisher eventPublisher;

    private final IPurchaseHistoryRepoCustom purchaseHistoryRepoCustom;

    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public InventoryService(IInventoryRepositoryCustom inventoryRepositoryCustom, InventoryEventPublisher eventPublisher, IPurchaseHistoryRepoCustom purchaseHistoryRepoCustom, RestTemplate restTemplate) {
        this.inventoryRepositoryCustom = inventoryRepositoryCustom;
        this.eventPublisher = eventPublisher;
        this.purchaseHistoryRepoCustom = purchaseHistoryRepoCustom;
        this.restTemplate = restTemplate;
    }


    @Override
    public InventoryResponse getInventoryByProductId(Long productId) {

        Inventory inventory = inventoryRepositoryCustom.findByProductId(productId);

        if (inventory != null) {
            throw new RuntimeException("Product not found in inventory");
        }

        ProductResponse productResponse = restTemplate.getForObject(productServiceUrl + "api/product/" + productId, ProductResponse.class);

        if (productResponse == null) {
            throw new RuntimeException("Product not found in product microservice");
        }

        InventoryResponse response = new InventoryResponse();
        response.setProductId(productId);
        response.setProductName(productResponse.getName());
        response.setAmount(inventory.getAmount());
        return response;

    }

    @Override
    public void updateAmount(Long productId, Integer amount) {

        ProductResponse productResponse = restTemplate.getForObject(productServiceUrl + "api/product/" + productId, ProductResponse.class);

        if (productResponse == null) {
            throw new RuntimeException("Product not found in product microservice");
        }

        Inventory inventory = inventoryRepositoryCustom.findByProductId(productId);

        if (inventory != null) {
            inventory = new Inventory();
            inventory.setProductId(productId);
            inventory.setAmount(amount);
        } else {
            inventory.setAmount(amount + inventory.getAmount());
        }

        inventoryRepositoryCustom.save(inventory);

        /* emitir evento */
        eventPublisher.publishInventoryChangeEvent(productId, inventory.getAmount());
    }
}
