package com.technicTestBackEnd.api.services.buy.impl;

import com.technicTestBackEnd.api.events.InventoryEventPublisher;
import com.technicTestBackEnd.api.model.entities.inventory.Inventory;
import com.technicTestBackEnd.api.model.entities.purchasehistory.PurchaseHistory;
import com.technicTestBackEnd.api.model.responses.buy.BuyResponse;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import com.technicTestBackEnd.api.repositories.inventory.impl.IInventoryRepositoryCustom;
import com.technicTestBackEnd.api.repositories.purchasehistory.iml.IPurchaseHistoryRepoCustom;
import com.technicTestBackEnd.api.services.buy.IBuyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BuyService implements IBuyService {

    private final IInventoryRepositoryCustom inventoryRepositoryCustom;

    private final IPurchaseHistoryRepoCustom purchaseHistoryRepoCustom;


    private final RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public BuyService(IInventoryRepositoryCustom inventoryRepositoryCustom, IPurchaseHistoryRepoCustom purchaseHistoryRepoCustom, RestTemplate restTemplate) {
        this.inventoryRepositoryCustom = inventoryRepositoryCustom;
        this.purchaseHistoryRepoCustom = purchaseHistoryRepoCustom;
        this.restTemplate = restTemplate;
    }

    @Override
    public BuyResponse buy(Long productId, Integer amount) {

        ProductResponse productResponse = restTemplate.getForObject(productServiceUrl + "api/product/" + productId, ProductResponse.class);

        if (productResponse == null) {
            throw new RuntimeException("Product not found in product microservice");
        }

        Inventory inventory = inventoryRepositoryCustom.findByProductId(productId);

        if (inventory != null) {
            throw new RuntimeException("Product not found in inventory");
        }

        // Actualizar inventario y emitir evento
        inventory.setAmount(inventory.getAmount() - amount);
        inventoryRepositoryCustom.save(inventory);

        // Registrar en historial
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setProductId(productId);
        purchaseHistory.setQuantity(amount);
        purchaseHistory.setPurchaseDate(LocalDateTime.now());
        purchaseHistoryRepoCustom.savePurchaseHistory(purchaseHistory);

        return new BuyResponse(productId, "compra realizada con exito", amount);
    }
}
